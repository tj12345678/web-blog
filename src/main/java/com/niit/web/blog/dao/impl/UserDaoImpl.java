package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.domain.Vo.UserVo;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.util.BeanHandler;
import com.niit.web.blog.util.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author tj
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public class UserDaoImpl implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void batchInsert(List<User> userList) throws SQLException {
//        连接数据库
        Connection connection = DBUtils.getConnection();
//        sql语句，数据执行操作，
        connection.setAutoCommit(false);
        String sql = "INSERT INTO t_user (id,mobile,password,nickname,avatar,gender,birthday,address,introduction,banner,homepage,follows,fans,articles,create_time,status) VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        userList.forEach(user -> {
            try {
                pst.setString(1, user.getMobile());
                pst.setString(2, user.getPassword());
                pst.setString(3, user.getNickname());
                pst.setString(4, user.getAvatar());
                pst.setString(5, user.getGender());
                pst.setObject(6, user.getBirthday());
                pst.setString(7, user.getAddress());
                pst.setString(8, user.getIntroduction());
                pst.setString(9, user.getBanner());
                pst.setString(10, user.getHomepage());
                pst.setInt(11, 0);
                pst.setInt(12, 0);
                pst.setInt(13, 0);
                pst.setObject(14, user.getCreateTime());
                pst.setShort(15, user.getStatus());
                pst.addBatch();
            } catch (SQLException e) {
                logger.error("批量加入用户数据产生异常");
            }
        });
        pst.executeBatch();
        connection.commit();
        DBUtils.close(connection,pst);

    }

    @Override
    public User findUserByMobile(String mobile) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "SELECT * FROM t_user WHERE mobile = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, mobile);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        User user = null;
        if (userList.size() != 0) {
            user = userList.get(0);
        }
        DBUtils.close(connection, pst, rs);
        return  user;
    }

    @Override
    public void insert(User user) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "INSERT INTO t_user (mobile,password,avatar,birthday,create_time) VALUES (?,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, user.getMobile());
        pst.setString(2, user.getPassword());
        pst.setString(3,user.getAvatar());
        pst.setObject(4,user.getBirthday());
        pst.setObject(5,user.getCreateTime());
        pst.executeUpdate();
        DBUtils.close(connection, pst);
    }

    @Override
    public List<User> selectHotUsers() throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "SELECT * FROM t_user ORDER BY fans DESC LIMIT 10 ";
        PreparedStatement pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DBUtils.close(connection, pst, rs);
        return userList;
    }

    @Override
    public List<User> selectByPage(int currentPage, int count) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "SELECT * FROM t_user LIMIT ?,? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setInt(1, (currentPage - 1) * count);
        pst.setInt(2, count);
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DBUtils.close(connection, pst, rs);
        return userList;
    }

    @Override
    public UserVo getUser(long id) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "SELECT * FROM t_user WHERE id = ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        UserVo userVo = new UserVo();
        User user = BeanHandler.convertUser(rs).get(0);
        userVo.setUser(user);
        DBUtils.close(connection, pst, rs);
        return userVo;
    }

    @Override
    public List<User> selectByKeywords(String keywords) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "SELECT * FROM t_user " +
                "WHERE nickname LIKE ?  OR introduction LIKE ? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, "%" + keywords + "%");
        pst.setString(2, "%" + keywords + "%");
        ResultSet rs = pst.executeQuery();
        List<User> userList = BeanHandler.convertUser(rs);
        DBUtils.close(connection, pst, rs);
        return userList;
    }

    @Override
    public void deleteUserById(Long userId) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "DELETE FROM t_user WHERE id = ?  ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, userId);
        pst.executeUpdate();
        DBUtils.close(connection, pst);
    }



    @Override
    public void changeUser(User user) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "Update t_user Set password=?,nickname=?,gender=?,address=?,introduction=?  Where id = ?";
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setString(1,user.getPassword());
        pst.setString(2,user.getNickname());
        pst.setString(3,user.getGender());
        pst.setString(4,user.getAddress());
        pst.setString(5,user.getIntroduction());
        pst.setLong(6,user.getId());
        pst.executeUpdate();
        DBUtils.close(connection, pst);

    }
}
