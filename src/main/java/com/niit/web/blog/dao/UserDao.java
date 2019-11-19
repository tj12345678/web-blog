package com.niit.web.blog.dao;

import com.niit.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author tj
 * @ClassName UserDao
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public interface UserDao {
    /**
     * 批量插入
     * @param userList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<User> userList) throws SQLException;

    /**
     * 通过手机号查询
     * @param mobile
     * @return
     * @throws SQLException
     */
    User findUserByMobile(String mobile) throws SQLException;
}
