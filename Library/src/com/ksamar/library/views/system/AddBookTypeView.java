package com.ksamar.library.views.system;

import com.ksamar.library.controller.book.BookTypeController;
import com.ksamar.library.entity.BookType;
import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.color.Colour;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.image.Images;
import com.ksamar.library.tools.view.View;

import javax.swing.*;
import java.awt.*;

/**
 * 添加图书种类窗口
 * @author KSaMar
 * @version 1.0
 */
public class AddBookTypeView extends JFrame{

    /**
     * 属性
     */
    private String titleText = "添加图书种类";

    /**
     * 标签
     */
    private JLabel addBookTypeTitleLabel = new JLabel();
    private JLabel addBookTypeTextLabel = new JLabel();
    private JLabel errorMessageLabel = new JLabel();

    /**
     * 输入框
     */
    private JTextField addBookTypeField = new JTextField();

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
    private JPanel addBookTypePanel = new JPanel();

    /**
     * 添加图书种类窗口
     */
    private static final AddBookTypeView INSTANCE = new AddBookTypeView();
    /**
     * 添加图书种类窗口
     */
    private AddBookTypeView() {

        // 添加图书面板标题
        addBookTypeTitleLabel.setText(titleText);
        addBookTypeTitleLabel.setBounds(8, 0, 392, 32);
        addBookTypeTitleLabel.setFont(Fonts.subTitle);

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
        topPanel.add(addBookTypeTitleLabel);
        topPanel.add(closeButton);

        // 添加图书种类标签
        addBookTypeTextLabel.setText("图书种类");
        addBookTypeTextLabel.setBounds(16, 42, 100, 32);
        addBookTypeTextLabel.setFont(Fonts.subTitle);
        addBookTypeTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 添加图书种类输入框
        addBookTypeField.setBounds(120, 42, 200, 32);
        addBookTypeField.setBorder(Borders.textFiledBorder);

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
        confirmButton.setText("添加");
        confirmButton.setBounds(300, 116, 80, 24);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(Fonts.button);
        confirmButton.setBackground(Colour.C3C8CE7);
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(null);
        confirmButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        confirmButton.addActionListener(e -> addBookType());

        // 错误信息面板
        addBookTypePanel.setLayout(null);
        addBookTypePanel.setBounds(0, 32, 400, 148);
        addBookTypePanel.setBackground(Color.WHITE);
        addBookTypePanel.setBorder(Borders.viewBorder);
        addBookTypePanel.add(addBookTypeTextLabel);
        addBookTypePanel.add(addBookTypeField);
        addBookTypePanel.add(errorMessageLabel);
        addBookTypePanel.add(cancelButton);
        addBookTypePanel.add(confirmButton);

        // 添加组件
        add(topPanel);
        add(addBookTypePanel);

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
    }

    /**
     * 重置表单
     */
    private void resetForm() {
        addBookTypeField.setText("");
        errorMessageLabel.setText("");
    }

    /**
     * 添加图书种类
     */
    private void addBookType() {
        String text = addBookTypeField.getText();
        if (!"".equals(text)) {

            // 设置图书种类信息
            BookType bookType = new BookType();
            bookType.setTypeName(text);
            String typeId = String.valueOf(10001 + BookTypeController.getTypeCount());
            bookType.setTypeId(typeId);

            int result = BookTypeController.addType(bookType);

            if (result == 0) {
                errorMessageLabel.setText("添加失败");
            }
            else if (result == 1) {
                resetForm();
                errorMessageLabel.setText("添加成功");
            }
            else if (result == 2) {
                errorMessageLabel.setText("种类存在");
            }
        }
        else {
            errorMessageLabel.setText("请输入图书种类");
        }
    }

    /**
     * 获取添加图书种类窗口
     * @return 添加图书种类窗口
     */
    public static AddBookTypeView getInstance() {
        return INSTANCE;
    }
}