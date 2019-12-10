package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.FollowDao;
import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.domain.Vo.UserVo;
import com.niit.web.blog.entity.TopicFollow;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.entity.UserFollow;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tj
 * @ClassName FollowDaoImpl
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
public class FollowDaoImpl implements FollowDao {
    private UserDao userDao = DaoFactory.getUserDaoInstance();
    @Override
    public List<User> getUserFollows(long userId) throws SQLException {
        Connection connection = DBUtils.getConnection();

        String sql2="SELECT * FROM t_user_follow WHERE from_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql2);
        pst.setLong(1, userId);
        ResultSet rs = pst.executeQuery();
//        List<User> userList = BeanHandler.convertUser(rs);
//        DBUtils.close(connection, pst, rs);
//        return userList;
        List<UserFollow> userList = new ArrayList<>();
        try {
            while (rs.next()) {
                UserFollow user = new UserFollow();
                user.setId(rs.getLong("id"));
                user.setFromId(rs.getLong("from_id"));
                user.setToId(rs.getLong("to_id"));

                userList.add(user);
            }
        } catch (SQLException e) {
        }
        int i=0;
        int x=userList.size();
        List<User> userList1 = new ArrayList<>();
        while (i<x){
            UserVo userVo = new UserVo();
            userVo=userDao.getUser(userList.get(i++).getToId());
            userList1.add(userVo.getUser());
        }


        return userList1;
    }

    @Override
    public List<User> getUserFans(long userId) throws SQLException {
        Connection connection = DBUtils.getConnection();

        String sql2="SELECT * FROM t_user_follow WHERE to_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql2);
        pst.setLong(1, userId);
        ResultSet rs = pst.executeQuery();
//        List<User> userList = BeanHandler.convertUser(rs);
//        DBUtils.close(connection, pst, rs);
//        return userList;
        List<UserFollow> userList = new ArrayList<>();
        try {
            while (rs.next()) {
                UserFollow user = new UserFollow();
                user.setId(rs.getLong("id"));
                user.setFromId(rs.getLong("from_id"));
                user.setToId(rs.getLong("to_id"));

                userList.add(user);
            }
        } catch (SQLException e) {
        }
        int i=0;
        int x=userList.size();
        List<User> userList1 = new ArrayList<>();
        while (i<x) {
            UserVo userVo = new UserVo();
            userVo = userDao.getUser(userList.get(i).getFromId());
            userList1.add(userVo.getUser());
            i++;
        }


        return userList1;
    }

    @Override
    public List<User> getTopicFollows(long topicId) throws SQLException {
        Connection connection = DBUtils.getConnection();

        String sql2="SELECT * FROM t_topic_follow WHERE topic_id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql2);
        pst.setLong(1, topicId);
        ResultSet rs = pst.executeQuery();
//        List<User> userList = BeanHandler.convertUser(rs);
//        DBUtils.close(connection, pst, rs);
//        return userList;
        List<TopicFollow> topicFollowList = new ArrayList<>();
        try {
            while (rs.next()) {
                TopicFollow topicFollow = new TopicFollow();
                topicFollow.setId(rs.getLong("id"));
                topicFollow.setUserId(rs.getLong("user_id"));
                topicFollow.setTopicId(rs.getLong("topic_id"));

                topicFollowList.add(topicFollow);
            }
        } catch (SQLException e) {
        }
        int i=0;
        int x=topicFollowList.size();
        List<User> userList1 = new ArrayList<>();
        while (i<x){
            UserVo userVo = new UserVo();
            userVo=userDao.getUser(topicFollowList.get(i).getUserId());
            userList1.add(userVo.getUser());
            i++;
        }


        return userList1;
    }
}
