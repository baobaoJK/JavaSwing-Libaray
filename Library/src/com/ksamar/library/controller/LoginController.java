package com.ksamar.library.controller;

import com.ksamar.library.tools.sql.SqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 登录控制器
 * @author KSaMar
 * @version 1.0
 */
public class LoginController {

    /**
     * 登录控制器
     * @param username 用户名
     * @param password 密码
     * @return 登录状态
     */
    public int login(String username, String password) {
        int result = 0;

        // 判断用户名或密码是否为空
        if (!"".equals(username) && !"".equals(password)) {
            // 搜索用户语句
            String selectUserSql = "select groups,username,password from userlist where username = ? and password = ?";
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                // 执行语句
                preparedStatement = SqlConnect.getConnection().prepareStatement(selectUserSql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                String groups = "admin";
                String tempGroups = "";
                String tempUsername = "";
                String tempPassword = "";

                while (resultSet.next()) {
                    tempGroups = resultSet.getString("groups");
                    tempUsername = resultSet.getString("username");
                    tempPassword = resultSet.getString("password");
                }

                if (tempGroups.equals(groups) && tempUsername.equals(username) && tempPassword.equals(password)) {
                    result = 1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return result;
    }
}
