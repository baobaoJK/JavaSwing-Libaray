package com.ksamar.library.views.user;

import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.tools.view.View;

import javax.swing.*;
import java.awt.*;

/**
 * 用户表单窗口
 * @author KSaMar
 * @version 1.0
 */
public class UserFormView extends JFrame {

    /**
     * 属性
     */
    public String userFormText = "用户表单";
    public String groupsText = "所属组";
    public String usernameText = "用户名";
    public String passwordText = "密码";
    public String nameText = "姓名";
    public String genderText = "性别";
    public String idCardText = "借阅卡号";
    public String phoneText = "手机号";
    public String identityText = "身份";

    /**
     * 标签
     */
    public JLabel userFormTextLabel = new JLabel();
    public JLabel groupsTextLabel = new JLabel();
    public JLabel usernameTextLabel = new JLabel();
    public JLabel passwordTextLabel = new JLabel();
    public JLabel nameTextLabel = new JLabel();
    public JLabel genderTextLabel = new JLabel();
    public JLabel idCardTextLabel = new JLabel();
    public JLabel phoneTextLabel = new JLabel();
    public JLabel identityTextLabel = new JLabel();
    public JLabel errorLabel = new JLabel();

    /**
     * 按钮
     */
    public JButton closeButton = new JButton();
    public JButton cancelButton = new JButton();
    public JButton userFormButton = new JButton();

    /**
     * 面板
     */
    public JPanel userFormPanel = new JPanel();

    /**
     * 下拉框
     */
    public JComboBox<String> groupsComboBox = new JComboBox<>();
    public JComboBox<String> genderComboBox = new JComboBox<>();
    public JComboBox<String> identityComboBox = new JComboBox<>();

    /**
     * 文本框
     */
    public JTextField usernameField = new JTextField();
    public JPasswordField passwordField = new JPasswordField();
    public JTextField nameField = new JTextField();
    public JTextField idCardField = new JTextField();
    public JTextField phoneField = new JTextField();

    /**
     * 用户表单窗体
     */
    public UserFormView() {

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

        // 用户表单文本
        userFormTextLabel.setText(userFormText);
        userFormTextLabel.setBounds(16, 16, 400, 16);
        userFormTextLabel.setFont(Fonts.subTitle);

        // 用户组别文本
        groupsTextLabel.setText(groupsText);
        groupsTextLabel.setBounds(50, 64, 90, 32);
        groupsTextLabel.setFont(Fonts.subTitle);
        groupsTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 用户组别选择框
        groupsComboBox.setBounds(150, 64, 300, 32);
        groupsComboBox.addItem("请选择组别");
        groupsComboBox.addItem("用户");
        groupsComboBox.addItem("管理员");
        groupsComboBox.setBackground(Color.WHITE);
        groupsComboBox.setFont(Fonts.comboBox);

        // 用户名文本
        usernameTextLabel.setText(usernameText);
        usernameTextLabel.setBounds(50, 112, 90, 32);
        usernameTextLabel.setFont(Fonts.subTitle);
        usernameTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 用户名输入框
        usernameField.setBounds(150, 112, 300, 32);
        usernameField.setBorder(Borders.textFiledBorder);
        usernameField.setFont(Fonts.textField);

        // 密码文本
        passwordTextLabel.setText(passwordText);
        passwordTextLabel.setBounds(50, 160, 90, 32);
        passwordTextLabel.setFont(Fonts.subTitle);
        passwordTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 密码输入框
        passwordField.setBounds(150, 160, 300, 32);
        passwordField.setBorder(Borders.textFiledBorder);
        passwordField.setFont(Fonts.textField);

        // 姓名文本
        nameTextLabel.setText(nameText);
        nameTextLabel.setBounds(50, 208, 90, 32);
        nameTextLabel.setFont(Fonts.subTitle);
        nameTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 姓名输入框
        nameField.setBounds(150, 208, 300, 32);
        nameField.setBorder(Borders.textFiledBorder);
        nameField.setFont(Fonts.textField);

        // 性别文本
        genderTextLabel.setText(genderText);
        genderTextLabel.setBounds(50, 256, 90, 32);
        genderTextLabel.setFont(Fonts.subTitle);
        genderTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 性别选择框
        genderComboBox.setBounds(150, 256, 300, 32);
        genderComboBox.addItem("请选择性别");
        genderComboBox.addItem("男");
        genderComboBox.addItem("女");
        genderComboBox.setBackground(Color.WHITE);
        genderComboBox.setFont(Fonts.comboBox);

        // 借阅卡文本
        idCardTextLabel.setText(idCardText);
        idCardTextLabel.setBounds(50, 304, 90, 32);
        idCardTextLabel.setFont(Fonts.subTitle);
        idCardTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 借阅卡输入框
        idCardField.setBounds(150, 304, 300, 32);
        idCardField.setBorder(Borders.textFiledBorder);
        idCardField.setFont(Fonts.textField);

        // 手机号文本
        phoneTextLabel.setText(phoneText);
        phoneTextLabel.setBounds(50, 352, 90, 32);
        phoneTextLabel.setFont(Fonts.subTitle);
        phoneTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 手机号输入框
        phoneField.setBounds(150, 352, 300, 32);
        phoneField.setBorder(Borders.textFiledBorder);
        phoneField.setFont(Fonts.textField);

        // 身份文本
        identityTextLabel.setText(identityText);
        identityTextLabel.setBounds(50, 400, 90, 32);
        identityTextLabel.setFont(Fonts.subTitle);
        identityTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 身份选项框
        identityComboBox.setBounds(150, 400, 300, 32);
        identityComboBox.addItem("请选择身份");
        identityComboBox.addItem("学生");
        identityComboBox.addItem("老师");
        identityComboBox.addItem("管理员");
        identityComboBox.setBackground(Color.WHITE);
        identityComboBox.setFont(Fonts.comboBox);

        // 错误标签
        errorLabel.setBounds(150, 448, 300, 32);
        errorLabel.setFont(Fonts.errorMessage);
        errorLabel.setForeground(Color.RED);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 取消按钮
        cancelButton.setText("取消");
        cancelButton.setBounds(300, 500, 80, 32);
        cancelButton.setFont(Fonts.button);
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(Borders.buttonBorder);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(e -> {
            setVisible(false);
            resetForm();
        });

        // 用户表单按钮
        userFormButton.setBounds(400, 500, 80, 32);
        userFormButton.setForeground(Color.WHITE);
        userFormButton.setFont(Fonts.button);
        userFormButton.setBackground(Colour.C3C8CE7);
        userFormButton.setFocusPainted(false);
        userFormButton.setBorder(null);
        userFormButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // 用户表单面板
        userFormPanel.setLayout(null);
        userFormPanel.setBounds(0, 0, 500, 550);
        userFormPanel.setBackground(Color.WHITE);
        userFormPanel.setBorder(Borders.viewBorder);
        userFormPanel.add(userFormTextLabel);
        userFormPanel.add(closeButton);
        userFormPanel.add(groupsTextLabel);
        userFormPanel.add(groupsComboBox);
        userFormPanel.add(usernameTextLabel);
        userFormPanel.add(usernameField);
        userFormPanel.add(passwordTextLabel);
        userFormPanel.add(passwordField);
        userFormPanel.add(nameTextLabel);
        userFormPanel.add(nameField);
        userFormPanel.add(genderTextLabel);
        userFormPanel.add(genderComboBox);
        userFormPanel.add(idCardField);
        userFormPanel.add(idCardTextLabel);
        userFormPanel.add(phoneField);
        userFormPanel.add(phoneTextLabel);
        userFormPanel.add(identityTextLabel);
        userFormPanel.add(identityComboBox);
        userFormPanel.add(errorLabel);
        userFormPanel.add(cancelButton);
        userFormPanel.add(userFormButton);

        // 添加组件
        add(userFormPanel);

        // 添加用户表单窗口
        setSize(500,550);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);
        setFocusable(true);
        setVisible(false);
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
        usernameField.setText("");
        passwordField.setText("");
        nameField.setText("");
        genderComboBox.setSelectedItem("请选择性别");
        idCardField.setText("");
        phoneField.setText("");
        identityComboBox.setSelectedItem("请选择身份");
    }

    /**
     * 获取组别
     * @param groups 组别
     * @return String
     */
    public String getGroups(String groups) {
        switch (groups) {
            case "用户":
                return "user";
            case "管理员":
                return "admin";
            default:
                return null;
        }
    }
}
