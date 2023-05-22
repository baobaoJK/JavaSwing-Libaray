package com.ksamar.library.views.user;

import com.ksamar.library.controller.user.UserContoller;
import com.ksamar.library.entity.User;
import com.ksamar.library.tools.form.CheckFormTool;
import com.ksamar.library.views.message.Message;

/**
 * 添加用户窗口
 * @author KSaMar
 * @version 1.0
 */
public class AddUserView extends UserFormView {

    /**
     * 添加图书窗口
     */
    private static final AddUserView INSTANCE = new AddUserView();

    /**
     * 添加图书窗口
     */
    private AddUserView() {
        // 设置表单标题
        userFormText = "添加用户";
        userFormTextLabel.setText(userFormText);

        // 添加事件
        userFormButton.setText("添加");
        userFormButton.addActionListener(e -> addUser());

        // 设置标题
        setTitle(userFormText);
    }

    /**
     * 添加用户
     */
    private void addUser() {
        if (CheckFormTool.checkUserForm(getInstance())) {
            User user = new User();
            user.setGroups(getGroups(String.valueOf(groupsComboBox.getSelectedItem())));
            user.setName(nameField.getText());
            user.setUsername(usernameField.getText());
            user.setPassword(String.valueOf(passwordField.getPassword()));
            user.setGender(String.valueOf(genderComboBox.getSelectedItem()));
            user.setIdCard(idCardField.getText());
            user.setPhone(phoneField.getText());
            user.setIdentity(String.valueOf(identityComboBox.getSelectedItem()));
            user.setBookCount(3);
            user.setState(1);

            int result = UserContoller.addUser(user);

            if (result == 0) {
                Message.showMessage("添加失败", "错误", 0);
            }
            else if (result == 1) {
                dispose();
                resetForm();
                Message.showMessage("添加成功", "信息", 1);
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
     * 获取添加用户窗体
     * @return 添加用户窗体
     */
    public static AddUserView getInstance() {
        return INSTANCE;
    }
}
