package com.ksamar.library.views;

import com.ksamar.library.controller.book.BookController;
import com.ksamar.library.entity.Book;
import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.pagination.Pagination;
import com.ksamar.library.tools.placeholder.Placeholder;
import com.ksamar.library.tools.table.LibraryTableModel;
import com.ksamar.library.views.book.AddBookView;
import com.ksamar.library.views.book.DeleteBookView;
import com.ksamar.library.views.book.EditBookView;
import com.ksamar.library.views.message.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

/**
 * 图书管理面板
 * @author KSaMar
 * @version 1.0
 */
public class BookView extends JPanel {

    /**
     * 属性
     */
    private String searchPlaceholder = "请输入搜索内容";
    private String searchType = "书名";
    private int tableRow = -1;
    private int page = 1;
    private int total = 1;
    private int size = 10;

    /**
     * 下拉框
     */
    private JComboBox<String> pageComboBox = new JComboBox<>();
    private JComboBox<String> searchComboBox = new JComboBox<>();

    /**
     * 输入框
     */
    private JTextField searchFiled = new JTextField();

    /**
     * 按钮
     */
    private JButton searchButton = new JButton();
    private JButton addBookViewButton = new JButton();
    private JButton editBookViewButton = new JButton();
    private JButton deleteBookViewButton = new JButton();

    /**
     * 表格
     */
    private JTable bookTable;

    /**
     * 面板
     */
    private JPanel operationPanel = new JPanel();
    private JPanel bookOperationsPanel = new JPanel();
    private JScrollPane bookScrollPane = new JScrollPane();
    private Pagination pagination = new Pagination(1, 1);

    /**
     * 表格模型
     */
    private String[] name = {"Id", "组别", "书名", "作者", "出版社", "价格(人民币)", "数量(本)", "ISBN号码"};
    private Object[][] tableDate = new Object[0][0];
    private LibraryTableModel tableModel = new LibraryTableModel(tableDate, name);

    /**
     * 图书管理面板
     */
    public BookView() {

        // 初始化
        init();

        // 分页选项栏
        pageComboBox.setBounds(0, 0, 150, 32);
        pageComboBox.addItem("10 条/页");
        pageComboBox.addItem("50 条/页");
        pageComboBox.addItem("100 条/页");
        pageComboBox.setBackground(Color.WHITE);
        pageComboBox.setFont(Fonts.comboBox);
        pageComboBox.addActionListener(pageChange());

        // 搜索选项栏
        searchComboBox.setBounds(190, 0, 150, 32);
        searchComboBox.addItem("书名");
        searchComboBox.addItem("作者");
        searchComboBox.addItem("出版社");
        searchComboBox.addItem("ISBN 号码");
        searchComboBox.setBackground(Color.WHITE);
        searchComboBox.setFont(Fonts.comboBox);
        searchComboBox.addActionListener(searchChange());

        // 搜索栏
        searchFiled.setBounds(340, 0, 300, 32);
        searchFiled.setBorder(Borders.searchFiledBorder);
        searchFiled.setFont(Fonts.textField);
        searchFiled.addFocusListener(Placeholder.focusListener(searchFiled, searchPlaceholder));
        Placeholder.setPlaceholder(searchFiled, searchPlaceholder, Color.LIGHT_GRAY);

        // 搜索按钮
        searchButton.setText("搜索");
        searchButton.setBounds(640, 0, 100, 32);
        searchButton.setHorizontalAlignment(SwingConstants.CENTER);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(Fonts.button);
        searchButton.setBackground(Colour.C3C8CE7);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(null);
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.addActionListener(e -> searchBookButtonEvent());

        // 添加图书按钮
        addBookViewButton.setText("添加图书");
        addBookViewButton.setBounds(778, 0, 150, 32);
        addBookViewButton.setHorizontalAlignment(SwingConstants.CENTER);
        addBookViewButton.setForeground(Color.WHITE);
        addBookViewButton.setFont(Fonts.button);
        addBookViewButton.setBackground(Colour.C67C23A);
        addBookViewButton.setFocusPainted(false);
        addBookViewButton.setBorder(null);
        addBookViewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addBookViewButton.addActionListener(e -> AddBookView.getInstance().setVisible(true));

        // 操作面板
        operationPanel.setLayout(null);
        operationPanel.setBounds(16, 16, 928, 32);
        operationPanel.setBackground(Color.WHITE);
        operationPanel.add(pageComboBox);
        operationPanel.add(searchComboBox);
        operationPanel.add(searchFiled);
        operationPanel.add(searchButton);
        operationPanel.add(addBookViewButton);

        // 图书表格
        bookTable = new JTable(tableModel);
        bookTable.setRowHeight(32);
        bookTable.getTableHeader().setReorderingAllowed(false);
        bookTable.getTableHeader().setResizingAllowed(false);
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableRow = bookTable.getSelectedRow();
            }
        });

        // 图书信息面版
        bookScrollPane.setBounds(16,84, 928, 550);
        bookScrollPane.setViewportView(bookTable);

        // 编辑图书按钮
        editBookViewButton.setText("编辑图书");
        editBookViewButton.setBounds(0, 0, 100, 32);
        editBookViewButton.setHorizontalAlignment(SwingConstants.CENTER);
        editBookViewButton.setForeground(Color.WHITE);
        editBookViewButton.setFont(Fonts.button);
        editBookViewButton.setBackground(Colour.C3C8CE7);
        editBookViewButton.setFocusPainted(false);
        editBookViewButton.setBorder(null);
        editBookViewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editBookViewButton.addActionListener(e -> showEditBookView());

        // 删除图书按钮
        deleteBookViewButton.setText("删除图书");
        deleteBookViewButton.setBounds(116, 0, 100, 32);
        deleteBookViewButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteBookViewButton.setForeground(Color.WHITE);
        deleteBookViewButton.setFont(Fonts.button);
        deleteBookViewButton.setBackground(Colour.CE54D52);
        deleteBookViewButton.setFocusPainted(false);
        deleteBookViewButton.setBorder(null);
        deleteBookViewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteBookViewButton.addActionListener(e -> showDeleteBookView());

        // 图书操作面板
        bookOperationsPanel.setLayout(null);
        bookOperationsPanel.setBounds(16, 650, 928, 32);
        bookOperationsPanel.setBackground(Color.WHITE);
        bookOperationsPanel.add(editBookViewButton);
        bookOperationsPanel.add(deleteBookViewButton);

        // 分页
        pagination.setBounds(16, 716, 928, 32);
        pagination.prePageButton.addActionListener(prePage());
        pagination.nextPageButton.addActionListener(nextPage());

        // 添加组件
        add(operationPanel);
        add(bookScrollPane);
        add(bookOperationsPanel);
        add(pagination);

        // 窗体设置
        setSize(960, 768);
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(true);
    }

    /**
     * 初始化
     */
    public void init() {
        getBook();
    }

    /**
     * 获取图书
     */
    public void getBook() {
        // 添加数据
        tableModel.setDataVector(tableDate, name);
        List<Book> bookArrayList = BookController.pageBook(page, size);
        tableModel.addBookRow(bookArrayList);
        // 设置分页
        setPagination();
    }

    /**
     * 更改页数
     * @return ActionListener
     */
    private ActionListener pageChange() {
        return e -> {
            // 获取组合框下标
            int index = pageComboBox.getSelectedIndex();
            switch (index) {
                case 0:
                    size = 10;
                    break;
                case 1:
                    size = 50;
                    break;
                case 2:
                    size = 100;
                    break;
                default:
                    break;
            }

            // 刷新表格
            page = 1;
            refreshBookTable();
        };
    }

    /**
     * 更改搜索类型
     * @return ActionListener
     */
    private ActionListener searchChange() {
        return e -> {
            // 获取组合框下标
            int index = searchComboBox.getSelectedIndex();
            switch (index) {
                case 0:
                    searchType = "书名";
                    break;
                case 1:
                    searchType = "作者";
                    break;
                case 2:
                    searchType = "出版社";
                    break;
                case 3:
                    searchType = "ISBN 号码";
                    break;
                default:
                    break;
            }
        };
    }

    /**
     * 设置分页
     */
    private void setPagination() {
        total = getPageTotal();
        pagination.setPagination(page, total);
    }

    /**
     * 上一页
     * @return ActionListener
     */
    private ActionListener prePage() {
        return e -> {
            if (page > 1) {
                page -= 1;
                refreshBookTable();
            }
        };
    }

    /**
     * 下一页
     * @return ActionListener
     */
    private ActionListener nextPage() {
        return e -> {
            total = getPageTotal();
            if (page <= total - 1) {
                page += 1;
                refreshBookTable();
            }
        };
    }

    /**
     * 搜索图书按钮事件
     */
    private void searchBookButtonEvent() {
        // 判断输入框信息
        String text = searchFiled.getText();
        if ("".equals(text) && searchPlaceholder.equals(text)) {
            Message.showMessage("请输入要搜索的信息", "错误", 0);
        }

        // 刷新图书信息表格
        page = 1;
        refreshBookTable();
    }

    /**
     * 搜索图书
     */
    private void searchBook() {
        // 设置表格数据
        tableModel.setDataVector(tableDate, name);
        String text = searchFiled.getText();
        List<Book> bookArrayList = BookController.searchBookLike(searchType, text, page, size);
        tableModel.addBookRow(bookArrayList);
        setPagination();
    }

    /**
     * 获取图书数量
     */
    private int getPageTotal() {
        int bookSize;
        String text = searchFiled.getText();
        if (!"".equals(text) && !searchPlaceholder.equals(text)) {
            bookSize = BookController.searchBookLikeCount(searchType, text);
        }
        else {
            bookSize = BookController.getCount();
        }
        return bookSize / size + (bookSize % size > 0 ? 1 : 0);
    }

    /**
     * 刷新表格数据
     */
    private void refreshBookTable() {
        String text = searchFiled.getText();
        if (!"".equals(text) && !searchPlaceholder.equals(text)) {
            searchBook();
        }
        else {
            getBook();
        }
    }

    /**
     * 显示编辑删除窗口
     */
    private void showEditBookView() {
        if (tableRow >= 0) {
            // 获取行数据
            Vector<Object> vector = (Vector) tableModel.getDataVector().elementAt(tableRow);
            EditBookView.getInstance().setValue(vector);
            tableRow = -1;
        }
        else {
            Message.showMessage("请选择需要编辑的图书", "错误", 0);
        }
    }

    /**
     * 显示删除图书窗口
     */
    private void showDeleteBookView() {
        if (tableRow >= 0) {
            // 获取行数据
            Vector<Object> vector = (Vector) tableModel.getDataVector().elementAt(tableRow);
            DeleteBookView.getInstance().setValue(vector);
            tableRow = -1;
        }
        else {
            Message.showMessage("请选择需要删除的图书", "错误", 0);
        }
    }
}
