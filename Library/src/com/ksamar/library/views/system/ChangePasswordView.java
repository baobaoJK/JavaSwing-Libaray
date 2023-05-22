package com.ksamar.library.views.system;

import com.ksamar.library.controller.user.UserContoller;
import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.tools.view.View;

import javax.swing.*;
import java.awt.*;

/**
 * 修改密码窗口
 * @author KSaMar
 * @version 1.0
 */
public class ChangePasswordView extends JFrame{

    /**
     * 属性
     */
    private String titleText = "修改管理员密码";

    /**
     * 标签
     */
    private JLabel changePasswordTitleLabel = new JLabel();
    private JLabel changePasswordTextLabel = new JLabel();
    private JLabel errorMessageLabel = new JLabel();

    /**
     * 输入框
     */
    private JTextField changePasswordField = new JTextField();

    /**
     * 按钮
     */
    private JButton closeButton = new JButton();
    private JButton cancelButton = new JButton();
    private JButton confirmButton = new JButton();

    /**
     * 面板
     */
    private JPanel topPanel = new JPanel();
    private JPanel changePasswordPanel = new JPanel();

    /**
     * 修改管理员密码窗口
     */
    private static final ChangePasswordView INSTANCE = new ChangePasswordView();

    /**
     * 修改管理员密码窗口
     */
    private ChangePasswordView() {

        // 修改管理员密码面板标题
        changePasswordTitleLabel.setText(titleText);
        changePasswordTitleLabel.setBounds(8, 0, 392, 32);
        changePasswordTitleLabel.setFont(Fonts.subTitle);

        // 关闭按钮
        closeButton.setIcon(Images.closeButtonIcon);
        closeButton.setRolloverIcon(Images.closeButtonHoverIcon);
        closeButton.setBounds(374, 6, 20, 20);
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(null);
        closeButton.addActionListener(e -> {
            resetForm();
            dispose();
        });

        // 标题栏
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 400, 32);
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(Borders.viewBorder);
        topPanel.add(changePasswordTitleLabel);
        topPanel.add(closeButton);

        // 修改管理员密码标签
        changePasswordTextLabel.setText("修改密码");
        changePasswordTextLabel.setBounds(16, 42, 100, 32);
        changePasswordTextLabel.setFont(Fonts.subTitle);
        changePasswordTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 修改管理员密码输入框
        changePasswordField.setBounds(120, 42, 200, 32);
        changePasswordField.setBorder(Borders.textFiledBorder);

        // 错误信息标签
        errorMessageLabel.setBounds(120, 80, 200, 32);
        errorMessageLabel.setFont(Fonts.errorMessage);
        errorMessageLabel.setForeground(Color.RED);

        // 取消按钮
        cancelButton.setText("取消");
        cancelButton.setBounds(204, 116, 80, 24);
        cancelButton.setFont(Fonts.button);
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(Borders.buttonBorder);
        cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(e -> {
            resetForm();
            dispose();
        });

        // 修改按钮
        confirmButton.setText("修改");
        confirmButton.setBounds(300, 116, 80, 24);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(Fonts.button);
        confirmButton.setBackground(Colour.CE54D52);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(null);
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.addActionListener(e -> changePassword());

        // 错误信息面板
        changePasswordPanel.setLayout(null);
        changePasswordPanel.setBounds(0, 32, 400, 148);
        changePasswordPanel.setBackground(Color.WHITE);
        changePasswordPanel.setBorder(Borders.viewBorder);
        changePasswordPanel.add(changePasswordTextLabel);
        changePasswordPanel.add(changePasswordField);
        changePasswordPanel.add(errorMessageLabel);
        changePasswordPanel.add(cancelButton);
        changePasswordPanel.add(confirmButton);

        // 添加组件
        add(topPanel);
        add(changePasswordPanel);

        // 窗体设置
        setTitle(titleText);
        setSize(400, 180);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);
        setFocusable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        setIconImage(Images.systemImage);
        View.setMoveFrame(this);
        dispose();
    }

    /**
     * 重置表单
     */
    private void resetForm() {
        changePasswordField.setText("");
        errorMessageLabel.setText("");
    }

    /**
     * 修改密码
     */
    private void changePassword() {
        String text = changePasswordField.getText();
        if (!"".equals(text)) {
            int result = UserContoller.updateAdminPassword(text);

            if (result == 0) {
                errorMessageLabel.setText("修改失败");
            }
            else if (result == 1) {
                resetForm();
                errorMessageLabel.setText("修改成功");
            }
        }
        else {
            errorMessageLabel.setText("请输入新的密码");
        }
    }

    /**
     * 获取修改管理员密码窗口
     * @return 修改管理员密码窗口
     */
    public static ChangePasswordView getInstance() {
        return INSTANCE;
    }
}