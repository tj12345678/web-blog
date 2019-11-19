package com.niit.web.blog.dao;

import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.JSoupDemo;
import com.niit.web.blog.util.JSoupSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import java.sql.SQLException;


public class UserDaoTest {

    private UserDao userDao = DaoFactory.getUserDaoInstance();
    private Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
    @Test
    public void batchInsert() throws SQLException {
        int[] i = userDao.batchInsert(JSoupSpider.getUsers());
        if(i.length!=0){
            System.out.println(i.length);
            logger.info("数据添加成功");
        }else {
            logger.error("用户数据添加失败");
        }
    }
}