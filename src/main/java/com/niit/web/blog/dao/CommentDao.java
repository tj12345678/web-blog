package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Comment;

import java.sql.SQLException;

/**
 * @author tj
 * @ClassName CommentDao
 * @Description TODO
 * @Date 2019/12/9
 * @Version 1.0
 **/
public interface CommentDao {

    /**
     * 添加评论
     * @param comment
     * @throws SQLException
     */
    void insert(Comment comment) throws SQLException;
}
