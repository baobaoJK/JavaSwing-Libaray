package com.ksamar.library.tools.sql;

/**
 * 数据库配置
 * @author KSaMar
 * @version 1.0
 */
public class SqlConfig {
    public static final String SQL_URL = "jdbc:mysql://localhost:3306/library?" +
            "serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&" +
            "autoReconnect=true";
    public static final String SQL_USERNAME = "root";
    public static final String SQL_PASSWORD = "123456";
}
