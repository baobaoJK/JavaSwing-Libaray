package com.ksamar.library.controller.log;

import com.ksamar.library.entity.Log;
import com.ksamar.library.tools.sql.SqlConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志控制器
 * @author KSaMar
 * @version 1.0
 */
public class LogController {

    /**
     * 获取日志信息
     * @return 日志信息
     */
    public static List<Log> getLogMessage() {
        List<Log> logMessage = new ArrayList<>();

        // 获取日志信息语句
        String getLogMessageSql = "select * from operationlist ORDER BY id DESC limit 0,50";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(getLogMessageSql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Log log = new Log();
                log.setTime(resultSet.getDate("time"));
                log.setName(resultSet.getString("name"));
                log.setInfo(resultSet.getString("info"));
                logMessage.add(log);
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

        return logMessage;
    }

    /**
     * 添加日志信息
     * @param log 日志信息
     * @return 添加状态
     */
    public static int addLog(Log log) {
        int result = 0;

        Timestamp logTime = new Timestamp(log.getTime().getTime());
        // 添加日志语句
        String addLogSql = "insert into operationlist(time, name, book_name, info) VALUE (?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = SqlConnect.getConnection().prepareStatement(addLogSql);
            preparedStatement.setTimestamp(1, logTime);
            preparedStatement.setString(2, log.getName());
            preparedStatement.setString(3, log.getBookName());
            preparedStatement.setString(4, log.getInfo());
            result = preparedStatement.executeUpdate();
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
        }

        return result;
    }
}
