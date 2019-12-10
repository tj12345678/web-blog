package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.CommentDao;
import com.niit.web.blog.entity.Comment;
import com.niit.web.blog.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
