package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.domain.Vo.UserVo;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.util.BeanHandler;
import com.niit.web.blog.util.DBUtils;

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
    @Override
    public int[] batchInsert(List<User> userList) throws SQLException {
//        连接数据库
        Connection connection = DBUtils.getConnection();
//        sql语句，数据执行操作，
        String sql = "INSERT INTO t_user (mobile,password,nickname,avatar,gender,birthday,address,introduction,create_time) VALUES (?,?,?,?,?,?,?,?,?) ";
//        关闭自动提交（true的时候为自动提交）
        connection.setAutoCommit(false);
//        执行sql语句
        PreparedStatement pstmt=connection.prepareStatement(sql);
//        遍历，将数据存入数据哭，有两个参数，pstmt,setString(位置，存入数据)
        userList.forEach(user -> {
            try {
                pstmt.setString(1, user.getMobile());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getNickname());
                pstmt.setString(4, user.getAvatar());
                pstmt.setString(5, user.getGender());
                pstmt.setObject(6, user.getBirthday());
                pstmt.setString(7, user.getAddress());
                pstmt.setString(8, user.getIntroduction());
                pstmt.setObject(9, user.getCreateTime());
//                addBatch将语句添加到同一批操作
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
        DBUtils.close(connection,pstmt);
        return result;
    }

    @Override
    public User findUserByMobile(String mobile) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql="SELECT * FROM t_user WHERE mobile = ?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,mobile);
        ResultSet rs=pstmt.executeQuery();
        User user=null;
        while(rs.next()){
            user= new User();
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday").toLocalDate());
            user.setIntroduction(rs.getString("introduction"));
            user.setAddress(rs.getString("address"));
            user.setFollows(rs.getShort("Follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticles(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setStatus(rs.getShort("status"));

        }
        return user;
    }

    @Override
    public void insert(User user) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "INSERT INTO t_user (mobile,password) VALUES (?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setString(1, user.getMobile());
        pst.setString(2, user.getPassword());
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
}
