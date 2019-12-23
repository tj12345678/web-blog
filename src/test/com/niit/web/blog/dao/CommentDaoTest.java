package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Comment;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.DataUtil;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;


public class CommentDaoTest {
    private CommentDao commentDao = DaoFactory.getCommentInstance();
    @Test
    public void insert() throws SQLException {

        Comment comment = new Comment();
        comment.setUserId((long) 1);
        comment.setArticleId((long) 1);
        comment.setCreateTime(DataUtil.getNowTime());
        comment.setContent("非常好");

        commentDao.insert(comment);
    }

    @Test
    public void delete() throws SQLException {
        Comment comment = new Comment();
        comment.setId((long) 1);
        comment.setUserId((long) 1);
        comment.setArticleId((long) 1);
        comment.setCreateTime(DataUtil.getNowTime());
        comment.setContent("非常好");

        commentDao.delete(comment);

    }

    @Test
    public void getComment() throws SQLException {
        List<Comment> comments=commentDao.getComment((long) 3);
        comments.forEach(System.out::println);
    }
}