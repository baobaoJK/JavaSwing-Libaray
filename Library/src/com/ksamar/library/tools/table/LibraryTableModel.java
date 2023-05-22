package com.ksamar.library.tools.table;

import com.ksamar.library.entity.Book;
import com.ksamar.library.entity.Borrow;
import com.ksamar.library.entity.Log;
import com.ksamar.library.entity.User;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 图书表格模型
 * @author KSaMar
 * @version 1.0
 */
public class LibraryTableModel extends DefaultTableModel {

    /**
     * 时间转换
     */
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置表格能否编辑
     * @param row 行
     * @param column 列
     * @return boolean
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * 图书表格模型
     * @param data 表数据
     * @param columns 表头
     */
    public LibraryTableModel(Object[][] data,Object[] columns){
        super(data, columns);
    }

    /**
     * 添加日志信息
     * @param logArrayList 日志信息列表
     */
    public void addLogRow(List<Log> logArrayList) {
        for (Log log : logArrayList) {
            Object[] objects = new Object[3];
            objects[0] = log.getTime();
            objects[1] = log.getName();
            objects[2] = log.getInfo();
            addRow(objects);
        }
    }

    /**
     * 添加图书数据
     * @param bookArrayList 图书信息列表
     */
    public void addBookRow(List<Book> bookArrayList) {
        for (Book book : bookArrayList) {
            Object[] objects = new Object[8];
            objects[0] = book.getId();
            objects[1] = book.getGroups();
            objects[2] = book.getName();
            objects[3] = book.getAuthor();
            objects[4] = book.getPress();
            objects[5] = book.getPrice();
            objects[6] = book.getQuantity();
            objects[7] = book.getIsbn();
            addRow(objects);
        }
    }

    /**
     * 添加借阅数据
     * @param borrowArrayList 借阅信息列表
     */
    public void addBorrowRow(List<Borrow> borrowArrayList) {
        for (Borrow borrow : borrowArrayList) {
            Object[] objects = new Object[8];
            objects[0] = borrow.getId();
            objects[1] = borrow.getBookName();
            objects[2] = borrow.getIsbn();
            objects[3] = borrow.getUsername();
            objects[4] = borrow.getIdCard();
            objects[5] = borrow.getPhone();
            objects[6] = formatter.format(borrow.getBorrowTime());
            objects[7] = formatter.format(borrow.getReturnTime());
            addRow(objects);
        }
    }

    /**
     * 添加用户数据
     * @param userArrayList 用户信息列表
     */
    public void addUserRow(List<User> userArrayList) {
        for (User user : userArrayList) {
            Object[] objects = new Object[11];
            objects[0] = user.getId();
            objects[1] = getGroups(String.valueOf(user.getGroups()));
            objects[2] = user.getName();
            objects[3] = user.getUsername();
            objects[4] = user.getPassword();
            objects[5] = user.getGender();
            objects[6] = user.getIdCard();
            objects[7] = user.getPhone();
            objects[8] = user.getIdentity();
            objects[9] = user.getBookCount();
            objects[10] = getState(String.valueOf(user.getState()));
            addRow(objects);
        }
    }

    /**
     * 获取用户组别
     * @param groups 组别
     * @return String
     */
    public String getGroups(String groups) {
        switch (groups) {
            case "user":
                return "用户";
            case "admin":
                return "管理员";
            default:
                return null;
        }
    }

    /**
     * 获取用户状态
     * @param state 状态
     * @return String
     */
    public String getState(String state) {
        switch (state) {
            case "0":
                return "冻结";
            case "1":
                return "正常";
            default:
                return null;
        }
    }
}