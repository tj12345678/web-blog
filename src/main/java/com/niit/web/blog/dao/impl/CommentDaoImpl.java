package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.CommentDao;
import com.niit.web.blog.entity.Comment;
import com.niit.web.blog.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tj
 * @ClassName CommentDaoImpl
 * @Description TODO
 * @Date 2019/12/9
 * @Version 1.0
 **/

public class CommentDaoImpl implements CommentDao {
    @Override
    public void insert(Comment comment) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "INSERT INTO t_comment (id,user_id,article_id,create_time,content) VALUES (null,?,?,?,?) ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1, comment.getUserId());
        pst.setLong(2, comment.getArticleId());
        pst.setObject(3,comment.getCreateTime());
        pst.setString(4,comment.getContent());
        pst.executeUpdate();
        DBUtils.close(connection, pst);

    }



    @Override
    public void delete(Comment comment) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "DELETE FROM t_comment WHERE user_id = ?  ";
        PreparedStatement pst = connection.prepareStatement(sql);

        pst.setLong(1, comment.getId());
        pst.executeUpdate();
        DBUtils.close(connection, pst);
    }

    @Override
    public List<Comment> getComment(Long article) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "SELECT * FROM t_comment WHERE article_id=? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1,article);
        ResultSet rs = pst.executeQuery();
        List<Comment> comments = new ArrayList<>();
        while (rs.next()){
            Comment comment = new Comment();
            comment.setId(rs.getLong("id"));
            comment.setUserId(rs.getLong("user_id"));
            comment.setArticleId(rs.getLong("article_id"));
            comment.setCreateTime((LocalDateTime) rs.getObject("create_time"));
            comment.setContent(rs.getString("content"));

            comments.add(comment);
        }
        DBUtils.close(connection, pst, rs);
        return comments;
    }

    @Override
    public List<Comment> getCommentUserId(Long userId) throws SQLException {
        Connection connection = DBUtils.getConnection();
        String sql = "SELECT * FROM t_comment WHERE user_id=? ";
        PreparedStatement pst = connection.prepareStatement(sql);
        pst.setLong(1,userId);
        ResultSet rs = pst.executeQuery();
        List<Comment> comments = new ArrayList<>();
        while (rs.next()){
            Comment comment = new Comment();
            comment.setId(rs.getLong("id"));
            comment.setUserId(rs.getLong("user_id"));
            comment.setArticleId(rs.getLong("article_id"));
            comment.setCreateTime((LocalDateTime) rs.getObject("create_time"));
            comment.setContent(rs.getString("content"));

            comments.add(comment);
        }
        DBUtils.close(connection, pst, rs);
        return comments;    }
}
