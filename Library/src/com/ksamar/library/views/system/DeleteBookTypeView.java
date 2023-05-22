package com.ksamar.library.views.system;

import com.ksamar.library.controller.book.BookTypeController;
import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.tools.view.View;

import javax.swing.*;
import java.awt.*;

/**
 * 删除图书种类窗口
 * @author KSaMar
 * @version 1.0
 */
public class DeleteBookTypeView extends JFrame{

    /**
     * 属性
     */
    private String titleText = "删除图书种类";

    /**
     * 标签
     */
    private JLabel deleteBookTypeTitleLabel = new JLabel();
    private JLabel deleteBookTypeTextLabel = new JLabel();
    private JLabel errorMessageLabel = new JLabel();

    /**
     * 输入框
     */
    private JTextField deleteBookTypeField = new JTextField();

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
    private JPanel deleteBookTypePanel = new JPanel();

    /**
     * 删除图书种类窗口
     */
    private static final DeleteBookTypeView INSTANCE = new DeleteBookTypeView();

    /**
     * 删除图书种类窗口
     */
    private DeleteBookTypeView() {

        // 删除图书面板标题
        deleteBookTypeTitleLabel.setText(titleText);
        deleteBookTypeTitleLabel.setBounds(8, 0, 392, 32);
        deleteBookTypeTitleLabel.setFont(Fonts.subTitle);

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
        topPanel.add(deleteBookTypeTitleLabel);
        topPanel.add(closeButton);

        // 删除图书种类标签
        deleteBookTypeTextLabel.setText("图书种类");
        deleteBookTypeTextLabel.setBounds(16, 42, 100, 32);
        deleteBookTypeTextLabel.setFont(Fonts.subTitle);
        deleteBookTypeTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 删除图书种类输入框
        deleteBookTypeField.setBounds(120, 42, 200, 32);
        deleteBookTypeField.setBorder(Borders.textFiledBorder);

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
        confirmButton.setText("删除");
        confirmButton.setBounds(300, 116, 80, 24);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(Fonts.button);
        confirmButton.setBackground(Colour.C3C8CE7);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(null);
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.addActionListener(e -> deleteBookType());

        // 错误信息面板
        deleteBookTypePanel.setLayout(null);
        deleteBookTypePanel.setBounds(0, 32, 400, 148);
        deleteBookTypePanel.setBackground(Color.WHITE);
        deleteBookTypePanel.setBorder(Borders.viewBorder);
        deleteBookTypePanel.add(deleteBookTypeTextLabel);
        deleteBookTypePanel.add(deleteBookTypeField);
        deleteBookTypePanel.add(errorMessageLabel);
        deleteBookTypePanel.add(cancelButton);
        deleteBookTypePanel.add(confirmButton);

        // 删除组件
        add(topPanel);
        add(deleteBookTypePanel);

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
        deleteBookTypeField.setText("");
        errorMessageLabel.setText("");
    }

    /**
     * 删除图书种类
     */
    private void deleteBookType() {
        String text = deleteBookTypeField.getText();
        if (!"".equals(text)) {

            int result = BookTypeController.deleteType(text);

            if (result == 0) {
                errorMessageLabel.setText("删除失败");
            }
            else if (result == 1) {
                resetForm();
                errorMessageLabel.setText("删除成功");
            }
        }
        else {
            errorMessageLabel.setText("请输入图书种类");
        }
    }

    /**
     * 获取删除图书种类窗口
     * @return 删除图书种类窗口
     */
    public static DeleteBookTypeView getInstance() {
        return INSTANCE;
    }
}