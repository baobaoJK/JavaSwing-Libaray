package com.ksamar.library.views.message;

import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.tools.view.View;

import javax.swing.*;
import java.awt.*;

/**
 * 信息窗口
 * @author KSaMar
 * @version 1.0
 */
public class Message extends JFrame{
    /**
     * 属性
     */
    public String titleText = "信息";

    /**
     * 标签
     */
    public JLabel messageTitleLabel = new JLabel();
    public JLabel messageIconLabel = new JLabel();
    public JLabel messageTextLabel = new JLabel();

    /**
     * 按钮
     */
    public JButton closeButton = new JButton();
    public JButton confirmButton = new JButton();

    /**
     * 面板
     */
    public JPanel topPanel = new JPanel();
    public JPanel messagePanel = new JPanel();

    /**
     * 信息窗口
     */
    public static final Message INSTANCE = new Message();

    /**
     * 信息窗口
     */
    public Message() {

// 信息面板标题
        messageTitleLabel.setText(titleText);
        messageTitleLabel.setBounds(8, 0, 392, 32);
        messageTitleLabel.setFont(Fonts.subTitle);

        // 关闭按钮
        closeButton.setIcon(Images.closeButtonIcon);
        closeButton.setRolloverIcon(Images.closeButtonHoverIcon);
        closeButton.setBounds(374, 6, 20, 20);
        closeButton.setOpaque(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(null);
        closeButton.addActionListener(e -> dispose());

        // 标题栏
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 400, 32);
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(Borders.topPanelBorder);
        topPanel.add(messageTitleLabel);
        topPanel.add(closeButton);

        // 图片
        messageIconLabel.setBounds(16, 42, 64, 64);

        // 信息
        messageTextLabel.setBounds(80, 34, 300, 80);
        messageTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageTextLabel.setFont(Fonts.message);

        // 按钮
        confirmButton.setText("确认");
        confirmButton.setBounds(300, 116, 80, 24);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(Fonts.button);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(null);
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.addActionListener(e -> dispose());

        // 信息面板
        messagePanel.setLayout(null);
        messagePanel.setBounds(0, 32, 400, 148);
        messagePanel.setBackground(Color.WHITE);
        messagePanel.add(messageIconLabel);
        messagePanel.add(messageTextLabel);
        messagePanel.add(confirmButton);

        // 添加组件
        add(topPanel);
        add(messagePanel);

        // 窗体设置
        setTitle(titleText);
        setSize(400, 180);
        setResizable(false);
        setUndecorated(true);
        setLayout(null);
        setFocusable(true);
        setVisible(true);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setIconImage(Images.systemImage);
        View.setMoveFrame(this);
        dispose();
    }

    /**
     * 显示信息
     * @param str 信息文本
     */
    public static void showMessage(String str, String title, int type) {

        switch (type) {
            case 0:
                INSTANCE.messageIconLabel.setIcon(Images.errorIcon);
                INSTANCE.confirmButton.setBackground(Colour.CE54D52);
                break;
            case 1:
                INSTANCE.messageIconLabel.setIcon(Images.successIcon);
                INSTANCE.confirmButton.setBackground(Colour.C67C23A);
                break;
            default:
                break;
        }

        INSTANCE.titleText = title;
        INSTANCE.messageTitleLabel.setText(title);
        INSTANCE.setVisible(true);
        INSTANCE.setLocationRelativeTo(null);
        INSTANCE.messageTextLabel.setText(str);
    }
}
