package com.ksamar.library.views;

import com.ksamar.library.controller.LoginController;
import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.tools.placeholder.Placeholder;
import com.ksamar.library.tools.view.View;

import javax.swing.*;
import java.awt.*;

/**
 * 登录窗体
 * @author KSamer
 * @version 1.0
 */
public class LoginView extends JFrame {

    /**
     * 标题信息
     */
    private String subTitle = "图书管理系统-登录";

    /**
     * 组件信息
     */
    private String loginButtonText = "登录";

    /**
     * 提示文本信息
     */
    private String usernamePlaceholder = "用户名";
    private String passwordPlaceholder = "密码";
    private String emptyUsernameText = "请输入用户名";
    private String emptyPasswordText = "请输入密码";
    private String errorText = "用户名或密码错误";

    /**
     * 标签
     */
    private JLabel titleLabel = new JLabel();
    private JLabel loginTitleLabel = new JLabel();
    private JLabel loginMessageLabel = new JLabel();

    /**
     * 按钮
     */
    private JButton closeButton = new JButton();
    private JButton loginButton = new JButton();

    /**
     * 输入框
     */
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();

    /**
     * 面板
     */
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();


    /**
     * 登录窗体
     * @param title 登录窗体标题
     */
    public LoginView(String title) {

        // 关闭按钮
        closeButton.setIcon(Images.closeButtonIcon);
        closeButton.setRolloverIcon(Images.closeButtonHoverIcon);
        closeButton.setBounds(660, 20, 20, 20);
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(null);
        closeButton.addActionListener(e -> System.exit(0));

        // 左面板标题
        titleLabel.setText(title);
        titleLabel.setBounds(0, 220, 350, 30);
        titleLabel.setFont(Fonts.title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 左面板
        leftPanel.setLayout(null);
        leftPanel.setBounds(0, 0, 350, 500);
        leftPanel.setBackground(Colour.C3C8CE7);
        leftPanel.add(titleLabel);

        // 右面板标题
        loginTitleLabel.setText(subTitle);
        loginTitleLabel.setBounds(0, 120, 350, 30);
        loginTitleLabel.setFont(Fonts.title);
        loginTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 用户名输入框
        usernameField.setBounds(55, 200, 240, 36);
        usernameField.setBorder(Borders.textFiledBorder);
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);
        usernameField.setFont(Fonts.textField);
        usernameField.addFocusListener(Placeholder.focusListener(usernameField, usernamePlaceholder));
        Placeholder.setPlaceholder(usernameField, usernamePlaceholder, Color.LIGHT_GRAY);

        // 密码输入框
        Placeholder.setPlaceholder(passwordField, passwordPlaceholder, Color.LIGHT_GRAY);
        passwordField.setEchoChar('\0');
        passwordField.setBounds(55, 260, 240, 36);
        passwordField.setBorder(Borders.textFiledBorder);
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.setFont((Fonts.textField));
        passwordField.addFocusListener(Placeholder.focusListener(passwordField, passwordPlaceholder));

        // 登录按钮
        loginButton.setText(loginButtonText);
        loginButton.setBounds(55, 320, 240, 36);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(Fonts.button);
        loginButton.setBackground(Colour.C3C8CE7);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(null);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> login());

        // 登录信息
        loginMessageLabel.setBounds(55, 380, 240, 24);
        loginMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginMessageLabel.setForeground(Color.RED);
        loginMessageLabel.setFont(Fonts.loginMessage);

        // 右面板
        rightPanel.setLayout(null);
        rightPanel.setBounds(350, 0, 350, 500);
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(loginTitleLabel);
        rightPanel.add(usernameField);
        rightPanel.add(passwordField);
        rightPanel.add(loginButton);
        rightPanel.add(loginMessageLabel);

        // 添加组件
        add(closeButton);
        add(leftPanel);
        add(rightPanel);

        // 设置标题
        setTitle(title);
        // 设置大小
        setSize(700, 500);
        // 设置窗体默认关闭方式
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置窗体不可编辑
        setResizable(false);
        // 设置窗体无边框
        setUndecorated(true);
        // 设置窗体布局
        setLayout(null);
        // 设置窗口焦点
        setFocusable(true);
        // 设置窗体是否可见
        setVisible(true);
        // 设置窗体居中
        setLocationRelativeTo(null);
        // 设置窗体图标
        setIconImage(Images.systemImage);
        // 设置窗体无边框移动
        View.setMoveFrame(this);
    }

    /**
     * 登录事件
     */
    public void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if ("".equals(username) || usernamePlaceholder.equals(username)) {
            loginMessageLabel.setText(emptyUsernameText);
        }
        else if ("".equals(password) || passwordPlaceholder.equals(password)) {
            loginMessageLabel.setText(emptyPasswordText);
        }
        else {
            int result = new LoginController().login(username, password);

            if (result == 1) {
                new AdminView();
                dispose();
            }
            else {
                loginMessageLabel.setText(errorText);
            }
        }
    }
}
