package com.ksamar.library.views.user;

import com.ksamar.library.controller.user.UserContoller;
import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.tools.view.View;
import com.ksamar.library.views.message.Message;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * 删除用户窗口
 * @author KSaMar
 * @version 1.0
 */
public class DeleteUserView extends JFrame {

    /**
     * 属性
     */
    private String titleText = "信息";

    /**
     * 标签
     */
    private JLabel deleteTitleLabel = new JLabel();
    private JLabel deleteIconLabel = new JLabel();
    private JLabel deleteTextLabel = new JLabel();

    /**
     * 按钮
     */
    private JButton closeButton = new JButton();
    private JButton deleteUserButton = new JButton();

    /**
     * 面板
     */
    private JPanel topPanel = new JPanel();
    private JPanel deletePanel = new JPanel();

    /**
     * 删除用户窗口
     */
    private static final DeleteUserView INSTANCE = new DeleteUserView();

    /**
     * 锁雾信息窗口
     */
    private DeleteUserView() {

        // 错误信息面板标题
        deleteTitleLabel.setText(titleText);
        deleteTitleLabel.setBounds(8, 0, 392, 32);
        deleteTitleLabel.setFont(Fonts.subTitle);

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
        topPanel.add(deleteTitleLabel);
        topPanel.add(closeButton);

        // 错误图片
        deleteIconLabel.setIcon(Images.errorIcon);
        deleteIconLabel.setBounds(16, 42, 64, 64);

        // 错误信息
        deleteTextLabel.setBounds(80, 34, 220, 80);
        deleteTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
        deleteTextLabel.setFont(Fonts.errorMessage);

        // 按钮
        deleteUserButton.setText("确认");
        deleteUserButton.setBounds(300, 116, 80, 24);
        deleteUserButton.setForeground(Color.WHITE);
        deleteUserButton.setFont(Fonts.button);
        deleteUserButton.setBackground(Colour.CE54D52);
        deleteUserButton.setFocusPainted(false);
        deleteUserButton.setBorder(null);
        deleteUserButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteUserButton.addActionListener(e -> deleteUser());

        // 错误信息面板
        deletePanel.setLayout(null);
        deletePanel.setBounds(0, 32, 400, 148);
        deletePanel.setBackground(Color.WHITE);
        deletePanel.add(deleteIconLabel);
        deletePanel.add(deleteTextLabel);
        deletePanel.add(deleteUserButton);

        // 添加组件
        add(topPanel);
        add(deletePanel);

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
     * 设置信息
     */
    public void setValue(Vector vector) {
        setVisible(true);
        setLocationRelativeTo(null);
        deleteTextLabel.setText("确认删除 " + vector.get(2).toString() + " 用户吗？");
        deleteUserButton.setName(vector.get(0).toString());
    }

    /**
     * 删除用户
     */
    public void deleteUser() {
        int id = Integer.parseInt(deleteUserButton.getName());
        int result = UserContoller.deleteUser(id);
        dispose();
        if (result == 0) {
            Message.showMessage("删除失败", "错误", 0);
        }
        else if (result == 1){
            Message.showMessage("删除成功", "信息", 1);
        }
    }

    /**
     * 获取删除用户窗口
     * @return 删除用户窗口
     */
    public static DeleteUserView getInstance() {
        return INSTANCE;
    }
}
