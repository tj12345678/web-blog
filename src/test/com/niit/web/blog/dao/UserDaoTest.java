package com.niit.web.blog.dao;

import com.niit.web.blog.domain.Vo.UserVo;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {
    private UserDao userDao = DaoFactory.getUserDaoInstance();

    @Test
    public void insert() throws SQLException {
        User user = new User("139545485341","b3b6d33a8992b9b91e3bf8451d7d7875");
        userDao.insert(user);
    }

    @Test
    public void batchInsert() throws SQLException {
        userDao.batchInsert(JSoupSpider.getUsers());
    }

    @Test
    public void findUserByMobile() throws SQLException {
        User user = userDao.findUserByMobile("13988200975");
        System.out.println(user);
    }

    @Test
    public void selectHotUsers() throws SQLException {
        List<User> userList = userDao.selectHotUsers();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByKeywords() throws SQLException{
        List<User> userList = userDao.selectByKeywords("王");
        userList.forEach(System.out::println);
    }

    @Test
    public void deleteUserById() throws SQLException {
        userDao.deleteUserById((long) 23);
    }

    @Test
    public void changeUser() throws SQLException {
        UserVo user=userDao.getUser(23);
        user.getUser().setAddress("123212312");
        userDao.changeUser(user.getUser());
    }

    @Test
    public void getUser() throws SQLException {
        UserVo user=userDao.getUser(23);
        System.out.println(user.getUser());
    }
}