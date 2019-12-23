package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Comment;

import java.sql.SQLException;
import java.util.List;

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

    /**
     * 删除品论
     * @param comment
     * @throws SQLException
     */
    void delete(Comment comment) throws SQLException;

    /**
     * 根据文章id查询评论
     * @param article
     * @return
     * @throws SQLException
     */
    List<Comment> getComment(Long article) throws  SQLException;

    /**
     * 根据用户id查询评论
     * @param userId
     * @return
     * @throws SQLException
     */
    List<Comment> getCommentUserId(Long userId) throws SQLException;
}
