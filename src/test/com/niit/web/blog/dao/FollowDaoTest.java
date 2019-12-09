package com.niit.web.blog.dao;

import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.DaoFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class FollowDaoTest {
    private FollowDao followDao = DaoFactory.getFollowDaoInstance();
    @Test
    public void getUserFollows() throws SQLException {
        List<User> userList = followDao.getUserFollows(2);
        userList.forEach(System.out::println);
    }

    @Test
    public void getUserFans() throws SQLException {
        List<User> userList = followDao.getUserFans(2);
        userList.forEach(System.out::println);
    }

    @Test
    public void getTopicFollows() throws SQLException {
        List<User> userList = followDao.getTopicFollows(2);
        userList.forEach(System.out::println);
    }
}