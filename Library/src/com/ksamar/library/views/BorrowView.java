package com.ksamar.library.views;

import com.ksamar.library.controller.book.BookController;
import com.ksamar.library.entity.Book;
import com.ksamar.library.entity.Borrow;
import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.placeholder.Placeholder;
import com.ksamar.library.views.message.Message;

import javax.swing.*;
import java.awt.*;

/**
 * 借阅图书面板
 * @author KSaMar
 * @version 1.0
 */
public class BorrowView extends JPanel {

    /**
     * 属性
     */
    private String borrowText = "借阅图书";
    private String bookText = "图书信息";
    private String bookNameText = "图书名称：";
    private String bookAuthorText = "图书作者：";
    private String bookPressText = "出版社名称：";
    private String bookIsbnText = "ISBN号码：";
    private String bookCountText = "库存（本）：";
    private String userText = "用户信息";
    private String searchPlaceholder = "请输入要搜索的图书 ISBN 号码";
    private String usernamePlaceholder = "借阅人名字";
    private String userPhonePlaceholder = "借阅人手机";
    private String userIdCardPlaceholder = "借阅人卡号";
    private Book book;

    /**
     * 标签
     */
    private JLabel borrowTextLabel = new JLabel();
    private JLabel bookTextLabel = new JLabel();
    private JLabel bookNameLabel = new JLabel();
    private JLabel bookAuthorLabel = new JLabel();
    private JLabel bookPressLabel = new JLabel();
    private JLabel bookIsbnLabel = new JLabel();
    private JLabel bookCountLabel = new JLabel();
    private JLabel userTextLabel = new JLabel();

    /**
     * 文本框
     */
    private JTextField searchBookField = new JTextField();
    private JTextField usernameFiled = new JTextField();
    private JTextField userPhoneFiled = new JTextField();
    private JTextField userIdCardFiled = new JTextField();

    /**
     * 按钮
     */
    private JButton searchBookButton = new JButton();
    private JButton borrowBookButton = new JButton();

    /**
     * 面板
     */
    private JPanel borrowPanel = new JPanel();
    private JPanel searchPanel = new JPanel();
    private JPanel bookPanel = new JPanel();
    private JPanel userPanel = new JPanel();

    /**
     * 借阅图书面板
     */
    public BorrowView() {

        // 搜索文本标签
        borrowTextLabel.setText(borrowText);
        borrowTextLabel.setBounds(0,0, 600, 48);
        borrowTextLabel.setFont(Fonts.title);
        borrowTextLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 搜索文本框
        searchBookField.setBounds(50, 56, 400, 32);
        searchBookField.setBorder(Borders.searchFiledBorder);
        searchBookField.setFont(Fonts.textField);
        searchBookField.setHorizontalAlignment(SwingConstants.CENTER);
        searchBookField.addFocusListener(Placeholder.focusListener(searchBookField, searchPlaceholder));
        Placeholder.setPlaceholder(searchBookField, searchPlaceholder, Color.LIGHT_GRAY);

        // 搜索按钮
        searchBookButton.setText("搜索");
        searchBookButton.setBounds(450, 56, 100, 32);
        searchBookButton.setHorizontalAlignment(SwingConstants.CENTER);
        searchBookButton.setForeground(Color.WHITE);
        searchBookButton.setFont(Fonts.button);
        searchBookButton.setBackground(Colour.C3C8CE7);
        searchBookButton.setFocusPainted(false);
        searchBookButton.setBorder(null);
        searchBookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchBookButton.addActionListener(e -> searchBookButtonEvent());

        // 搜索面板
        searchPanel.setLayout(null);
        searchPanel.setBounds(0,0, 600, 90);
        searchPanel.setBackground(Color.WHITE);
        searchPanel.add(borrowTextLabel);
        searchPanel.add(searchBookField);
        searchPanel.add(searchBookButton);

        // 图书信息文本
        bookTextLabel.setText(bookText);
        bookTextLabel.setBounds(50,0, 300, 48);
        bookTextLabel.setFont(Fonts.title);

        // 图书名称文本
        bookNameLabel.setText(bookNameText);
        bookNameLabel.setBounds(50,56, 300, 32);
        bookNameLabel.setFont(Fonts.subTitle);

        // 图书名称文本
        bookAuthorLabel.setText(bookAuthorText);
        bookAuthorLabel.setBounds(50,96, 300, 32);
        bookAuthorLabel.setFont(Fonts.subTitle);

        // 图书名称文本
        bookPressLabel.setText(bookPressText);
        bookPressLabel.setBounds(50,136, 300, 32);
        bookPressLabel.setFont(Fonts.subTitle);

        // 图书名称文本
        bookIsbnLabel.setText(bookIsbnText);
        bookIsbnLabel.setBounds(50,176, 300, 32);
        bookIsbnLabel.setFont(Fonts.subTitle);

        // 图书名称文本
        bookCountLabel.setText(bookCountText);
        bookCountLabel.setBounds(50,216, 300, 32);
        bookCountLabel.setFont(Fonts.subTitle);

        // 图书信息面板
        bookPanel.setLayout(null);
        bookPanel.setBounds(0, 100, 300, 400);
        bookPanel.setBackground(Color.WHITE);
        bookPanel.add(bookTextLabel);
        bookPanel.add(bookNameLabel);
        bookPanel.add(bookAuthorLabel);
        bookPanel.add(bookPressLabel);
        bookPanel.add(bookIsbnLabel);
        bookPanel.add(bookCountLabel);

        // 用户信息文本
        userTextLabel.setText(userText);
        userTextLabel.setBounds(0, 0, 250, 48);
        userTextLabel.setFont(Fonts.title);

        // 用户名称输入框
        usernameFiled.setBounds(0, 64, 250, 32);
        usernameFiled.setBorder(Borders.searchFiledBorder);
        usernameFiled.setFont(Fonts.textField);
        usernameFiled.setHorizontalAlignment(SwingConstants.CENTER);
        usernameFiled.addFocusListener(Placeholder.focusListener(usernameFiled, usernamePlaceholder));
        Placeholder.setPlaceholder(usernameFiled, usernamePlaceholder, Color.LIGHT_GRAY);

       // 用户手机输入框
        userPhoneFiled.setBounds(0, 112, 250, 32);
        userPhoneFiled.setBorder(Borders.searchFiledBorder);
        userPhoneFiled.setFont(Fonts.textField);
        userPhoneFiled.setHorizontalAlignment(SwingConstants.CENTER);
        userPhoneFiled.addFocusListener(Placeholder.focusListener(userPhoneFiled, userPhonePlaceholder));
        Placeholder.setPlaceholder(userPhoneFiled, userPhonePlaceholder, Color.LIGHT_GRAY);

       // 用户借阅卡输入框
        userIdCardFiled.setBounds(0, 160, 250, 32);
        userIdCardFiled.setBorder(Borders.searchFiledBorder);
        userIdCardFiled.setFont(Fonts.textField);
        userIdCardFiled.setHorizontalAlignment(SwingConstants.CENTER);
        userIdCardFiled.addFocusListener(Placeholder.focusListener(userIdCardFiled, userIdCardPlaceholder));
        Placeholder.setPlaceholder(userIdCardFiled, userIdCardPlaceholder, Color.LIGHT_GRAY);

        // 搜索按钮
        borrowBookButton.setText("借阅图书");
        borrowBookButton.setBounds(0, 208, 250, 32);
        borrowBookButton.setHorizontalAlignment(SwingConstants.CENTER);
        borrowBookButton.setForeground(Color.WHITE);
        borrowBookButton.setFont(Fonts.button);
        borrowBookButton.setBackground(Colour.C3C8CE7);
        borrowBookButton.setFocusPainted(false);
        borrowBookButton.setBorder(null);
        borrowBookButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        borrowBookButton.addActionListener(e -> borrowBookEvent());

        // 用户信息面板
        userPanel.setLayout(null);
        userPanel.setBounds(300, 100, 300, 400);
        userPanel.setBackground(Color.WHITE);
        userPanel.add(userTextLabel);
        userPanel.add(usernameFiled);
        userPanel.add(userPhoneFiled);
        userPanel.add(userIdCardFiled);
        userPanel.add(borrowBookButton);

        // 借阅面板
        borrowPanel.setLayout(null);
        borrowPanel.setBounds(180,188,600,400);
        borrowPanel.setBackground(Color.WHITE);
        borrowPanel.add(searchPanel);
        borrowPanel.add(bookPanel);
        borrowPanel.add(userPanel);

        // 添加组件
        add(borrowPanel);

        // 面板设置
        setSize(960, 768);
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(true);
    }

    /**
     * 搜索图书事件
     */
    private void searchBookButtonEvent() {
        if (!"".equals(searchBookField.getText()) && !searchPlaceholder.equals(searchBookField.getText())) {
            searchBook();
        }
        else {
            Message.showMessage("请输入要搜索的信息", "错误", 0);
        }
    }

    /**
     * 搜索图书
     */
    private void searchBook() {
        String isbn = searchBookField.getText();
        book = BookController.searchBookByIsbn(isbn);
        if (book != null) {
            bookNameLabel.setText(bookNameText + book.getName());
            bookAuthorLabel.setText(bookAuthorText + book.getAuthor());
            bookPressLabel.setText(bookPressText + book.getPress());
            bookIsbnLabel.setText(bookIsbnText + book.getIsbn());
            bookCountLabel.setText(bookCountText + book.getQuantity());
        }
   }

    /**
     * 检擦表单
     */
    private boolean checkForm() {
        if ("".equals(searchBookField.getText()) || searchPlaceholder.equals(searchBookField.getText())) {
            Message.showMessage("请输入要借阅的图书 ISBN 号码信息", "错误", 0);
        }
        else if ("".equals(usernameFiled.getText()) || usernamePlaceholder.equals(usernameFiled.getText())) {
            Message.showMessage("请输入要借阅人的名字", "错误", 0);
        }
        else if ("".equals(userPhoneFiled.getText()) || userPhonePlaceholder.equals(userPhoneFiled.getText())) {
            Message.showMessage("请输入要借阅人的手机号", "错误", 0);
        }
        else if ("".equals(userIdCardFiled.getText()) || userIdCardPlaceholder.equals(userIdCardFiled.getText())) {
            Message.showMessage("请输入要借阅人的卡号", "错误", 0);
        }
        else {
            return true;
        }
        return false;
    }

    /**
     * 借阅图书事件
     */
    private void borrowBookEvent() {
        if (checkForm()) {
            borrowBook();
        }
    }

    /**
     * 借阅图书
     */
    private void borrowBook() {
        if (checkForm()) {
            Borrow borrow = new Borrow();
            borrow.setBookName(book.getName());
            borrow.setIsbn(book.getIsbn());
            borrow.setUsername(usernameFiled.getText());
            borrow.setIdCard(userIdCardFiled.getText());
            borrow.setPhone(userPhoneFiled.getText());

            int result = BookController.borrowBook(borrow);

            if (result == 0) {
                Message.showMessage("借阅失败", "错误", 0);
            }
            else if (result == 1){
                Message.showMessage("借阅成功", "信息", 1);
            }
            else if (result == 2) {
                Message.showMessage("借阅失败，图书无库存", "错误", 0);
            }
            else if (result == 3){
                Message.showMessage("借阅失败，借阅人信息有误", "错误", 0);
            }
            else  if (result == 4) {
                Message.showMessage("可借阅数量为0", "错误", 0);
            }
        }
    }
}
