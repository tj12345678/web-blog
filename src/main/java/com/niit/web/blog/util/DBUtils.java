package com.niit.web.blog.util;


import ch.qos.logback.core.db.dialect.DBUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * 数据库操作工具类
 */
public class DBUtils {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(DBUtil.class);


    /**
     * 读取resources目录下的db-config.properties文件
     */
    private static ResourceBundle rb = ResourceBundle.getBundle("db-config");

    /**
     * 私有的构造方法，单例模式，禁止外部创建对象
     */
    private DBUtils() {
    }

    //使用静态块加载驱动程序，只执行一次
    static {
        URL = rb.getString("jdbc.url");
        USERNAME = rb.getString("jdbc.username");
        PASSWORD = rb.getString("jdbc.password");
        String driver = rb.getString("jdbc.driver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义一个获取数据库连接的方法
     *
     * @return Connection
     */
    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                logger.error(LocalDateTime.now() + "数据库连接失败");
            }
        }
        return conn;
    }

    /**
     * 关闭connection
     *
     * @param connection 连接池对象
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Statement
     *
     * @param statement
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭ResultSet
     *
     * @param resultSet
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭Connection 以及Statement
     *
     * @param connection
     * @param statement
     */
    public static void close(Connection connection, Statement statement) {
        close(connection);
        close(statement);
    }

    /**
     * 关闭Connection，Statement以及ResultSet
     *
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        close(connection, statement);
        close(resultSet);
    }

    public static void main(String[] args) {
        Connection connection = null;
        for (int i = 0; i < 3; i++) {
            connection = DBUtils.getConnection();
        }
    }
}