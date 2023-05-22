package com.ksamar.library.views.user;

import com.ksamar.library.controller.user.UserContoller;
import com.ksamar.library.entity.User;
import com.ksamar.library.tools.border.Borders;
import com.ksamar.library.tools.font.Fonts;
import com.ksamar.library.tools.form.CheckFormTool;
import com.ksamar.library.views.message.Message;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * 编辑用户窗口
 * @author KSaMar
 * @version 1.0
 */
public class EditUserView extends UserFormView {

    /**
     * 属性
     */
    private String bookCountText = "借阅数量";
    private String stateText = "状态";

    /**
     * 标签
     */
    private JLabel bookCountTextLabel = new JLabel();
    private JLabel stateTextLabel = new JLabel();

    /**
     * 下拉框
     */
    private JComboBox<String> stateComboBox = new JComboBox<>();

    /**
     * 文本框
     */
    private JTextField bookCountField = new JTextField();

    /**
     * 编辑用户窗口
     */
    private static final EditUserView INSTANCE = new EditUserView();

    /**
     * 编辑用户窗口
     */
    private EditUserView() {

        // 用户数量文本
        bookCountTextLabel.setText(bookCountText);
        bookCountTextLabel.setBounds(50, 448, 90, 32);
        bookCountTextLabel.setFont(Fonts.subTitle);
        bookCountTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 用户数量输入框
        bookCountField.setBounds(150, 448, 300, 32);
        bookCountField.setBorder(Borders.textFiledBorder);
        bookCountField.setFont(Fonts.textField);

        // 状态文本
        stateTextLabel.setText(stateText);
        stateTextLabel.setBounds(50, 496, 90, 32);
        stateTextLabel.setFont(Fonts.subTitle);
        stateTextLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // 状态选项框
        stateComboBox.setBounds(150, 496, 300, 32);
        stateComboBox.addItem("请选择状态");
        stateComboBox.addItem("正常");
        stateComboBox.addItem("冻结");
        stateComboBox.setBackground(Color.WHITE);
        stateComboBox.setFont(Fonts.comboBox);


        // 设置表单标题
        userFormText = "编辑用户";
        userFormTextLabel.setText(userFormText);

        // 编辑用户按钮
        userFormButton.setText("编辑");
        userFormButton.addActionListener(e -> editUser());

        // 编辑用户面板
        userFormPanel.setBounds(0, 0, 500, 650);
        userFormPanel.add(bookCountTextLabel);
        userFormPanel.add(bookCountField);
        userFormPanel.add(stateTextLabel);
        userFormPanel.add(stateComboBox);

        // 取消按钮
        cancelButton.setBounds(300, 600, 80, 32);

        // 编辑按钮
        userFormButton.setBounds(400, 600, 80, 32);

        // 设置标题
        setTitle(userFormText);
        setSize(500,650);
    }

    /**
     * 设置信息
     */
    public void setValue(Vector vector) {
        userFormButton.setName(vector.get(0).toString());
        groupsComboBox.setSelectedItem(vector.get(1));
        nameField.setText(vector.get(2).toString());
        usernameField.setText(vector.get(3).toString());
        passwordField.setText(vector.get(4).toString());
        genderComboBox.setSelectedItem(vector.get(5).toString());
        idCardField.setText(vector.get(6).toString());
        phoneField.setText(vector.get(7).toString());
        identityComboBox.setSelectedItem(vector.get(8).toString());
        bookCountField.setText(vector.get(9).toString());
        stateComboBox.setSelectedItem(vector.get(10).toString());
        setVisible(true);
    }

    /**
     * 检查表单
     */
    private boolean checkUserForm() {
        if (CheckFormTool.checkUserForm(getInstance())) {
            if ("".equals(bookCountField.getText())) {
                Message.showMessage("请输入可借阅图书数量", "错误", 0);
            }
            else if ("".equals(stateComboBox.getSelectedItem()) || "请选择状态".equals(stateComboBox.getSelectedItem())) {
                Message.showMessage("请选择状态", "错误", 0);
            }
            else if (!CheckFormTool.checkPhone(phoneField.getText())) {
                Message.showMessage("请输入正确的手机号", "错误", 0);
            }
            else {
                return true;
            }
        }

        return false;
    }

    /**
     * 编辑用户
     */
    private void editUser() {
        if (checkUserForm()) {
            User user = new User();
            user.setId(Integer.valueOf(userFormButton.getName()));
            user.setGroups(getGroups(String.valueOf(groupsComboBox.getSelectedItem())));
            user.setName(nameField.getText());
            user.setUsername(usernameField.getText());
            user.setPassword(String.valueOf(passwordField.getPassword()));
            user.setGender(String.valueOf(genderComboBox.getSelectedItem()));
            user.setIdCard(idCardField.getText());
            user.setPhone(phoneField.getText());
            user.setIdentity(String.valueOf(identityComboBox.getSelectedItem()));
            user.setBookCount(Integer.valueOf(bookCountField.getText()));
            user.setState(getState(String.valueOf(stateComboBox.getSelectedItem())));

            int result = UserContoller.editUser(user);

            if (result == 0) {
                Message.showMessage("编辑失败", "错误", 0);
            }
            else if (result == 1) {
                dispose();
                Message.showMessage("编辑成功", "信息", 1);
            }
            else if (result == 2) {
                Message.showMessage("用户名已存在", "错误", 0);
            }
            else if (result == 3) {
                Message.showMessage("借书卡号已存在", "错误", 0);
            }
            else if (result == 4) {
                Message.showMessage("手机号已存在", "错误", 0);
            }
        }
    }

    /**
     * 获取用户状态
     * @param state 状态
     * @return String
     */
    public int getState(String state) {
        switch (state) {
            case "冻结":
                return 0;
            case "正常":
                return 1;
            default:
                return -1;
        }
    }

    /**
     * 获取编辑用户窗口
     * @return 编辑用户窗口
     */
    public static EditUserView getInstance() {
        return INSTANCE;
    }
}
