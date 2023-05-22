package com.ksamar.library.views.book;

import com.ksamar.library.controller.book.BookTypeController;
import com.ksamar.library.entity.BookType;
import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.tools.view.View;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 图书表单窗口
 * @author KSaMar
 * @version 1.0
 */
public class BookFormView extends JFrame{
    
    /**
     * 属性
     */
    public String bookFormText = "图书表单";
    public String groupsText = "所属组";
    public String bookNameText = "书名";
    public String bookAuthorText = "作者";
    public String bookPressText = "出版社";
    public String bookPriceText = "价格(人民币)";
    public String bookCountText = "数量(本)";
    public String bookIsbnText = "ISBN号码";

    /**
     * 标签
     */
    public JLabel bookFormTextLabel = new JLabel();
    public JLabel groupsTextLabel = new JLabel();
    public JLabel bookNameTextLabel = new JLabel();
    public JLabel bookAuthorTextLabel = new JLabel();
    public JLabel bookPressTextLabel = new JLabel();
    public JLabel bookPriceTextLabel = new JLabel();
    public JLabel bookCountTextLabel = new JLabel();
    public JLabel bookIsbnTextLabel = new JLabel();
    public JLabel errorLabel = new JLabel();

    /**
     * 按钮
     */
    public JButton closeButton = new JButton();
    public JButton cancelButton = new JButton();
    public JButton bookFormButton = new JButton();

    /**
     * 面板
     */
    public JPanel bookFormPanel = new JPanel();

    /**
     * 下拉框
     */
    public JComboBox<String> groupsComboBox = new JComboBox<>();

    /**
     * 文本框
     */
    public JTextField bookNameField = new JTextField();
    public JTextField bookAuthorField = new JTextField();
    public JTextField bookPressField = new JTextField();
    public JTextField bookPriceField = new JTextField();
    public JTextField bookCountField = new JTextField();
    public JTextField bookIsbnField = new JTextField();

    /**
     * 图书表单窗体
     */
    public BookFormView() {

        // 关闭按钮
        closeButton.setIcon(Images.closeButtonIcon);
        closeButton.setRolloverIcon(Images.closeButtonHoverIcon);
        closeButton.setBounds(464, 12, 20, 20);
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(null);
        closeButton.addActionListener(e -> {
            setVisible(false);
            resetForm();
        });

        // 图书表单文本
        bookFormTextLabel.setText(bookFormText);
        bookFormTextLabel.setBounds(16, 16, 400, 16);
        bookFormTextLabel.setFont(Fonts.subTitle);

        // 图书组别文本
        groupsTextLabel.setText(groupsText);
        groupsTextLabel.setBounds(50, 64, 90, 32);
        groupsTextLabel.setFont(Fonts.subTitle);
        groupsTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 图书组别选择框
        groupsComboBox.setBounds(150, 64, 300, 32);
        groupsComboBox.addItem("请选择组别");
        groupsComboBox.setBackground(Color.WHITE);
        groupsComboBox.setFont(Fonts.comboBox);
        setGroupsComboBox();

        // 图书名文本
        bookNameTextLabel.setText(bookNameText);
        bookNameTextLabel.setBounds(50, 112, 90, 32);
        bookNameTextLabel.setFont(Fonts.subTitle);
        bookNameTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 图书名输入框
        bookNameField.setBounds(150, 112, 300, 32);
        bookNameField.setBorder(Borders.textFiledBorder);
        bookNameField.setFont(Fonts.textField);

        // 作者名称文本
        bookAuthorTextLabel.setText(bookAuthorText);
        bookAuthorTextLabel.setBounds(50, 160, 90, 32);
        bookAuthorTextLabel.setFont(Fonts.subTitle);
        bookAuthorTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 作者名称输入框
        bookAuthorField.setBounds(150, 160, 300, 32);
        bookAuthorField.setBorder(Borders.textFiledBorder);
        bookAuthorField.setFont(Fonts.textField);

        // 出版社名称文本
        bookPressTextLabel.setText(bookPressText);
        bookPressTextLabel.setBounds(50, 208, 90, 32);
        bookPressTextLabel.setFont(Fonts.subTitle);
        bookPressTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 出版社名称输入框
        bookPressField.setBounds(150, 208, 300, 32);
        bookPressField.setBorder(Borders.textFiledBorder);
        bookPressField.setFont(Fonts.textField);

        // 价格信息文本
        bookPriceTextLabel.setText(bookPriceText);
        bookPriceTextLabel.setBounds(50, 256, 90, 32);
        bookPriceTextLabel.setFont(Fonts.subTitle);
        bookPriceTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 价格输入框
        bookPriceField.setBounds(150, 256, 300, 32);
        bookPriceField.setBorder(Borders.textFiledBorder);
        bookPriceField.setFont(Fonts.textField);

        // 图书数量信息文本
        bookCountTextLabel.setText(bookCountText);
        bookCountTextLabel.setBounds(50, 304, 90, 32);
        bookCountTextLabel.setFont(Fonts.subTitle);
        bookCountTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 图书数量输入框
        bookCountField.setBounds(150, 304, 300, 32);
        bookCountField.setBorder(Borders.textFiledBorder);
        bookCountField.setFont(Fonts.textField);

        // 图书 ISBN 信息文本
        bookIsbnTextLabel.setText(bookIsbnText);
        bookIsbnTextLabel.setBounds(50, 352, 90, 32);
        bookIsbnTextLabel.setFont(Fonts.subTitle);
        bookIsbnTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 图书 ISBN 输入框
        bookIsbnField.setBounds(150, 352, 300, 32);
        bookIsbnField.setBorder(Borders.textFiledBorder);
        bookIsbnField.setFont(Fonts.textField);

        // 错误标签
        errorLabel.setBounds(150, 400, 300, 32);
        errorLabel.setFont(Fonts.errorMessage);
        errorLabel.setForeground(Color.RED);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 取消按钮
        cancelButton.setText("取消");
        cancelButton.setBounds(300, 450, 80, 32);
        cancelButton.setFont(Fonts.button);
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(Borders.buttonBorder);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(e -> {
            setVisible(false);
            resetForm();
        });

        // 事件按钮
        bookFormButton.setBounds(400, 450, 80, 32);
        bookFormButton.setForeground(Color.WHITE);
        bookFormButton.setFont(Fonts.button);
        bookFormButton.setBackground(Colour.C3C8CE7);
        bookFormButton.setFocusPainted(false);
        bookFormButton.setBorder(null);
        bookFormButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // 图书表单面板
        bookFormPanel.setLayout(null);
        bookFormPanel.setBounds(0, 0, 500, 500);
        bookFormPanel.setBackground(Color.WHITE);
        bookFormPanel.setBorder(Borders.viewBorder);
        bookFormPanel.add(bookFormTextLabel);
        bookFormPanel.add(closeButton);
        bookFormPanel.add(groupsTextLabel);
        bookFormPanel.add(groupsComboBox);
        bookFormPanel.add(bookNameTextLabel);
        bookFormPanel.add(bookNameField);
        bookFormPanel.add(bookAuthorTextLabel);
        bookFormPanel.add(bookAuthorField);
        bookFormPanel.add(bookPressTextLabel);
        bookFormPanel.add(bookPressField);
        bookFormPanel.add(bookPriceTextLabel);
        bookFormPanel.add(bookPriceField);
        bookFormPanel.add(bookCountTextLabel);
        bookFormPanel.add(bookCountField);
        bookFormPanel.add(bookIsbnTextLabel);
        bookFormPanel.add(bookIsbnField);
        bookFormPanel.add(errorLabel);
        bookFormPanel.add(cancelButton);
        bookFormPanel.add(bookFormButton);

        // 添加组件
        add(bookFormPanel);

        // 图书表单窗口设置
        setTitle("图书表单");
        setSize(500,500);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);
        setFocusable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setIconImage(Images.systemImage);
        View.setMoveFrame(this);
    }

    /**
     * 重置表单
     */
    public void resetForm() {
        groupsComboBox.setSelectedItem("请选择组别");
        bookNameField.setText("");
        bookAuthorField.setText("");
        bookPressField.setText("");
        bookPriceField.setText("");
        bookCountField.setText("");
        bookIsbnField.setText("");
    }

    /**
     * 组别选项框
     */
    public void setGroupsComboBox() {
        List<BookType> bookTypes = BookTypeController.getType();
        for (BookType bookType : bookTypes) {
            groupsComboBox.addItem(bookType.getTypeName());
        }
    }
}
