package com.ksamar.library.views;

import com.ksamar.library.controller.user.UserContoller;
import com.ksamar.library.entity.User;
import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.pagination.Pagination;
import com.ksamar.library.tools.placeholder.Placeholder;
import com.ksamar.library.tools.table.LibraryTableModel;
import com.ksamar.library.views.message.Message;
import com.ksamar.library.views.user.AddUserView;
import com.ksamar.library.views.user.DeleteUserView;
import com.ksamar.library.views.user.EditUserView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

/**
 * 用户管理面板
 * @author KSaMar
 * @version 1.0
 */
public class UserView extends JPanel {

    /**
     * 属性
     */
    private String searchPlaceholder = "请输入搜索内容";
    private String searchType = "用户名";
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
    private JButton addUserViewButton = new JButton();
    private JButton editUserViewButton = new JButton();
    private JButton deleteUserViewButton = new JButton();

    /**
     * 表格
     */
    private JTable userTable;

    /**
     * 面板
     */
    private JPanel operationPanel = new JPanel();
    private JPanel userOperationsPanel = new JPanel();
    private Pagination pagination = new Pagination(1, 1);
    private JScrollPane userScrollPane = new JScrollPane();

    /**
     * 表格模型
     */
    String[] name = {"Id", "组名", "姓名", "用户名", "密码", "性别", "借书卡号", "手机号", "身份", "可借书数量", "状态"};
    Object[][] tableDate = new Object[0][0];
    private LibraryTableModel tableModel = new LibraryTableModel(tableDate, name);

    /**
     * 用户管理面板
     */
    public UserView() {

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
        searchComboBox.addItem("用户名");
        searchComboBox.addItem("借书卡号");
        searchComboBox.addItem("手机号");
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
        searchButton.addActionListener(e -> searchUserButtonEvent());

        // 添加用户按钮
        addUserViewButton.setText("添加用户");
        addUserViewButton.setBounds(778, 0, 150, 32);
        addUserViewButton.setHorizontalAlignment(SwingConstants.CENTER);
        addUserViewButton.setForeground(Color.WHITE);
        addUserViewButton.setFont(Fonts.button);
        addUserViewButton.setBackground(Colour.C67C23A);
        addUserViewButton.setFocusPainted(false);
        addUserViewButton.setBorder(null);
        addUserViewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addUserViewButton.addActionListener(e -> AddUserView.getInstance().setVisible(true));

        // 操作面板
        operationPanel.setLayout(null);
        operationPanel.setBounds(16, 16, 928, 32);
        operationPanel.setBackground(Color.WHITE);
        operationPanel.add(pageComboBox);
        operationPanel.add(searchComboBox);
        operationPanel.add(searchFiled);
        operationPanel.add(searchButton);
        operationPanel.add(addUserViewButton);

        // 用户信息表格
        userTable = new JTable(tableModel);
        userTable.setRowHeight(32);
        userTable.getTableHeader().setReorderingAllowed(false);
        userTable.getTableHeader().setResizingAllowed(false);
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tableRow = userTable.getSelectedRow();
            }
        });

        // 用户信息面版
        userScrollPane.setBounds(16,84, 928, 550);
        userScrollPane.setViewportView(userTable);

        // 编辑用户按钮
        editUserViewButton.setText("编辑用户");
        editUserViewButton.setBounds(0, 0, 100, 32);
        editUserViewButton.setHorizontalAlignment(SwingConstants.CENTER);
        editUserViewButton.setForeground(Color.WHITE);
        editUserViewButton.setFont(Fonts.button);
        editUserViewButton.setBackground(Colour.C3C8CE7);
        editUserViewButton.setFocusPainted(false);
        editUserViewButton.setBorder(null);
        editUserViewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editUserViewButton.addActionListener(e -> showEditUserView());

        // 删除用户按钮
        deleteUserViewButton.setText("删除用户");
        deleteUserViewButton.setBounds(116, 0, 100, 32);
        deleteUserViewButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteUserViewButton.setForeground(Color.WHITE);
        deleteUserViewButton.setFont(Fonts.button);
        deleteUserViewButton.setBackground(Colour.CE54D52);
        deleteUserViewButton.setFocusPainted(false);
        deleteUserViewButton.setBorder(null);
        deleteUserViewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteUserViewButton.addActionListener(e -> showDeleteUserView());

        // 用户操作面板
        userOperationsPanel.setLayout(null);
        userOperationsPanel.setBounds(16, 650, 928, 32);
        userOperationsPanel.setBackground(Color.WHITE);
        userOperationsPanel.add(editUserViewButton);
        userOperationsPanel.add(deleteUserViewButton);

        // 分页
        pagination.setBounds(16, 716, 928, 32);
        pagination.prePageButton.addActionListener(prePage());
        pagination.nextPageButton.addActionListener(nextPage());

        // 添加组件
        add(operationPanel);
        add(userScrollPane);
        add(userOperationsPanel);
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
        getUser();
    }

    /**
     * 获取用户
     */
    private void getUser() {
        // 添加数据
        tableModel.setDataVector(tableDate, name);
        List<User> userArrayList = UserContoller.pageUser(page, size);
        tableModel.addUserRow(userArrayList);
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
            refreshUserTable();
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
                    searchType = "用户名";
                    break;
                case 1:
                    searchType = "借书卡号";
                    break;
                case 2:
                    searchType = "手机号";
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
                refreshUserTable();
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
                refreshUserTable();
            }
        };
    }


    /**
     * 搜索用户按钮事件
     */
    private void searchUserButtonEvent() {
        // 判断输入框信息
        String text = searchFiled.getText();
        if ("".equals(text) && searchPlaceholder.equals(text)) {
            Message.showMessage("请输入要搜索的信息", "错误", 0);
        }

        // 刷新图书信息表格
        page = 1;
        refreshUserTable();
    }

    /**
     * 搜索用户
     */
    private void searchUser() {
        // 设置表格数据
        tableModel.setDataVector(tableDate, name);
        String text = searchFiled.getText();
        List<User> userArrayList = UserContoller.searchUserLike(searchType, text, page, size);
        tableModel.addUserRow(userArrayList);
        // 设置分页信息
        setPagination();
    }

    /**
     * 获取用户数量
     */
    private int getPageTotal() {
        int userSize;
        String text = searchFiled.getText();
        if (!"".equals(text) && !searchPlaceholder.equals(text)) {
            userSize = UserContoller.searchUserLikeCount(searchType, text);
        }
        else {
            userSize = UserContoller.getCount();
        }
        return userSize / size + (userSize % size > 0 ? 1 : 0);
    }

    /**
     * 刷新表格数据
     */
    private void refreshUserTable() {
        String text = searchFiled.getText();
        if (!"".equals(text) && !searchPlaceholder.equals(text)) {
            searchUser();
        }
        else {
            getUser();
        }
    }

    /**
     * 显示编辑删除窗口
     */
    private void showEditUserView() {
        if (tableRow >= 0) {
            // 获取行数据
            Vector<Object> vector = (Vector) tableModel.getDataVector().elementAt(tableRow);
            EditUserView.getInstance().setValue(vector);
            tableRow = -1;
        }
        else {
            Message.showMessage("请选择需要编辑的用户", "错误", 0);
        }
    }

    /**
     * 显示删除用户窗口
     */
    private void showDeleteUserView() {
        if (tableRow >= 0) {
            // 获取行数据
            Vector<Object> vector = (Vector) tableModel.getDataVector().elementAt(tableRow);
            DeleteUserView.getInstance().setValue(vector);
            tableRow = -1;
        }
        else {
            Message.showMessage("请选择需要删除的用户", "错误", 0);
        }
    }
}
