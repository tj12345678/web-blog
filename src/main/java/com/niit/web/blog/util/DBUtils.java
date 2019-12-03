package com.niit.web.blog.util;


import ch.qos.logback.core.db.dialect.DBUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 数据库操作工具类
 */
public class DBUtils {
    private static final Logger logger = LoggerFactory.getLogger(DBUtil.class);
    private static Properties properties;


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
        properties = new Properties();
        try {
            InputStream ins = DBUtils.class.getClassLoader().getResourceAsStream("db-config.properties");
            assert ins != null;
            properties.load(ins);
            Class.forName(properties.getProperty("jdbc.driverClassName"));
        } catch (FileNotFoundException e) {
            logger.error("数据库配置文件未找到");
        } catch (IOException e) {
            logger.error("数据库配置文件读写错误");
        } catch (ClassNotFoundException e) {
            logger.error("数据库驱动未找到");
        }
    }

    /**
     * 定义一个获取数据库连接的方法
     *
     * @return Connection
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("jdbc.url"),
                    properties.getProperty("jdbc.username"),
                    properties.getProperty("jdbc.password"));
        } catch (SQLException e) {
            logger.error("数据库连接失败");
        }
        return connection;
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
        DBUtils.getConnection();
    }
}