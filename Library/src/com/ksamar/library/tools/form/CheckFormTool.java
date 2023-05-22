package com.ksamar.library.tools.form;

import com.ksamar.library.views.book.BookFormView;
import com.ksamar.library.views.message.Message;
import com.ksamar.library.views.user.UserFormView;

/**
 * 表单检查工具类
 * @author KSaMar
 * @version 1.0
 */
public class CheckFormTool {

    /**
     * 检查图书表单
     */
    public static boolean checkBookForm(BookFormView bookFormView) {
        if ("".equals(bookFormView.groupsComboBox.getSelectedItem()) || "请选择组别".equals(bookFormView.groupsComboBox.getSelectedItem())) {
            Message.showMessage("请选择组别", "错误", 0);
        }
        else if ("".equals(bookFormView.bookNameField.getText())) {
            Message.showMessage("请输入书名", "错误", 0);
        }
        else if ("".equals(bookFormView.bookAuthorField.getText())) {
            Message.showMessage("请输入作者", "错误", 0);
        }
        else if ("".equals(bookFormView.bookPressField.getText())) {
            Message.showMessage("请输入出版社", "错误", 0);
        }
        else if ("".equals(bookFormView.bookPriceField.getText())) {
            Message.showMessage("请输入价格", "错误", 0);
        }
        else if ("".equals(bookFormView.bookCountField.getText())) {
            Message.showMessage("请输入数量", "错误", 0);
        }
        else if ("".equals(bookFormView.bookIsbnField.getText())) {
            Message.showMessage("请输入ISBN号码", "错误", 0);
        }
        else if (!CheckFormTool.checkPrice(bookFormView.bookPriceField.getText())) {
            Message.showMessage("请输入正确的价格", "错误", 0);
        }
        else if (!CheckFormTool.checkCount(bookFormView.bookCountField.getText())) {
            Message.showMessage("请输入正确的数量", "错误", 0);
        }
        else if (!CheckFormTool.checkIsbn(bookFormView.bookIsbnField.getText())) {
            Message.showMessage("请输入正确的ISBN号码", "错误", 0);
        }
        else {
            return true;
        }

        return false;
    }

    /**
     * 检查用户表单
     */
    public static boolean checkUserForm(UserFormView userFormView) {
        if ("".equals(userFormView.groupsComboBox.getSelectedItem()) || "请选择组别".equals(userFormView.groupsComboBox.getSelectedItem())) {
            Message.showMessage("请选择组别", "错误", 0);
        }
        else if ("".equals(userFormView.usernameField.getText())) {
            Message.showMessage("请输入用户名", "错误", 0);
        }
        else if (userFormView.passwordField.getPassword().length == 0) {
            Message.showMessage("请输入密码", "错误", 0);
        }
        else if ("".equals(userFormView.nameField.getText())) {
            Message.showMessage("请输入姓名", "错误", 0);
        }
        else if ("".equals(userFormView.genderComboBox.getSelectedItem()) || "请选择性别".equals(userFormView.genderComboBox.getSelectedItem())) {
            Message.showMessage("请选择性别", "错误", 0);
        }
        else if ("".equals(userFormView.idCardField.getText())) {
            Message.showMessage("请输入借阅卡号", "错误", 0);
        }
        else if ("".equals(userFormView.phoneField.getText())) {
            Message.showMessage("请输入手机号", "错误", 0);
        }
        else if ("".equals(userFormView.identityComboBox.getSelectedItem()) || "请选择身份".equals(userFormView.identityComboBox.getSelectedItem())) {
            Message.showMessage("请选择身份", "错误", 0);
        }
        else if (!checkPhone(userFormView.phoneField.getText())) {
            Message.showMessage("请输入正确的手机号", "错误", 0);
        }
        else {
            return true;
        }

        return false;
    }

    /**
     * 判断价格是否输入正确
     * @param price 价格
     * @return boolean
     */
    public static boolean checkPrice(String price) {
        return price.matches("\\d{0,9}") || price.matches("\\d{0,9}\\.\\d{1,2}");
    }

    /**
     * 判断数量是否输入正确
     * @param count 数量
     * @return boolean
     */
    public static boolean checkCount(String count) {
        return count.matches("\\d{0,9}");
    }

    /**
     * 判断 ISBN 号码是否正确
     * @param isbn ISBN 号码
     * @return boolean
     */
    public static boolean checkIsbn(String isbn) {
        return isbn.matches("[1-9]\\d{12}");
    }

    /**
     * 判断手机号是否输入正确
     * @param phone 手机号
     * @return boolean
     */
    public static boolean checkPhone(String phone) {
        return phone.matches("^[1]([3][0-9]{1}|59|58|8r|89)[0-9]{8}$");
    }

}
