package com.ksamar.library.views;

import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.views.system.AddBookTypeView;
import com.ksamar.library.views.system.ChangePasswordView;
import com.ksamar.library.views.system.DeleteBookTypeView;

import javax.swing.*;
import java.awt.*;

/**
 * 系统管理面板
 * @author KSaMar
 * @version 1.0
 */
public class SystemView extends JPanel {

    /**
     * 标签
     */
    private JLabel adminTextLabel = new JLabel();
    private JLabel bookTypeLabel = new JLabel();

    /**
     * 按钮
     */
    private JButton changePasswordButton = new JButton();
    private JButton addBookTypeButton = new JButton();
    private JButton deleteBookTypeButton = new JButton();

    /**
     * 面板
     */
    private JPanel adminPanel = new JPanel();
    private JPanel adminTopPanel = new JPanel();
    private JPanel bookTypePanel = new JPanel();
    private JPanel bookTypeTopPanel = new JPanel();

    /**
     * 系统管理面板
     */
    public SystemView() {

        // 管理员面板标题
        adminTextLabel.setText("管理员信息设置");
        adminTextLabel.setBounds(8, 0, 900, 32);
        adminTextLabel.setFont(Fonts.subTitle);

        // 管理员面板标题面板
        adminTopPanel.setLayout(null);
        adminTopPanel.setBounds(0, 0, 928, 32);
        adminTopPanel.setBackground(Color.WHITE);
        adminTopPanel.setBorder(Borders.viewBorder);
        adminTopPanel.add(adminTextLabel);

        // 管理员修改密码按钮
        changePasswordButton.setText("修改管理员密码");
        changePasswordButton.setBounds(8, 40, 160, 32);
        changePasswordButton.setHorizontalAlignment(SwingConstants.CENTER);
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFont(Fonts.button);
        changePasswordButton.setBackground(Colour.C3C8CE7);
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.setBorder(null);
        changePasswordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        changePasswordButton.addActionListener(e -> ChangePasswordView.getInstance().setVisible(true));

        // 管理员设置面板
        adminPanel.setLayout(null);
        adminPanel.setBounds(16, 16, 928, 80);
        adminPanel.setBackground(Color.WHITE);
        adminPanel.setBorder(Borders.viewBorder);
        adminPanel.add(adminTopPanel);
        adminPanel.add(changePasswordButton);

        // 图书种类面板标题
        bookTypeLabel.setText("图书种类设置");
        bookTypeLabel.setBounds(8, 0, 900, 32);
        bookTypeLabel.setFont(Fonts.subTitle);

        // 图书种类面板标题面板
        bookTypeTopPanel.setLayout(null);
        bookTypeTopPanel.setBounds(0, 0, 928, 32);
        bookTypeTopPanel.setBackground(Color.WHITE);
        bookTypeTopPanel.setBorder(Borders.viewBorder);
        bookTypeTopPanel.add(bookTypeLabel);

        // 添加图书种类按钮
        addBookTypeButton.setText("添加图书总类");
        addBookTypeButton.setBounds(8, 40, 160, 32);
        addBookTypeButton.setHorizontalAlignment(SwingConstants.CENTER);
        addBookTypeButton.setForeground(Color.WHITE);
        addBookTypeButton.setFont(Fonts.button);
        addBookTypeButton.setBackground(Colour.C3C8CE7);
        addBookTypeButton.setFocusPainted(false);
        addBookTypeButton.setBorder(null);
        addBookTypeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addBookTypeButton.addActionListener(e -> AddBookTypeView.getInstance().setVisible(true));

        // 删除图书种类按钮
        deleteBookTypeButton.setText("删除图书种类");
        deleteBookTypeButton.setBounds(176, 40, 160, 32);
        deleteBookTypeButton.setHorizontalAlignment(SwingConstants.CENTER);
        deleteBookTypeButton.setForeground(Color.WHITE);
        deleteBookTypeButton.setFont(Fonts.button);
        deleteBookTypeButton.setBackground(Colour.C3C8CE7);
        deleteBookTypeButton.setFocusPainted(false);
        deleteBookTypeButton.setBorder(null);
        deleteBookTypeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteBookTypeButton.addActionListener(e -> DeleteBookTypeView.getInstance().setVisible(true));

        // 图书种类设置面板
        bookTypePanel.setLayout(null);
        bookTypePanel.setBounds(16, 112, 928, 80);
        bookTypePanel.setBackground(Color.WHITE);
        bookTypePanel.setBorder(Borders.viewBorder);
        bookTypePanel.add(bookTypeTopPanel);
        bookTypePanel.add(addBookTypeButton);
        bookTypePanel.add(deleteBookTypeButton);

        // 添加组件
        add(adminPanel);
        add(bookTypePanel);

        // 窗体设置
        setSize(960, 768);
        setLayout(null);
        setBackground(Color.WHITE);
        setVisible(true);
    }
}
