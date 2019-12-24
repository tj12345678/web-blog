package com.niit.web.blog.service.impl;

import com.niit.web.blog.dao.CommentDao;
import com.niit.web.blog.entity.Comment;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.service.CommentService;
import com.niit.web.blog.util.Result;
import com.niit.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @author tj
 * @ClassName CommentServiceImpl
 * @Description TODO
 * @Date 2019/12/10
 * @Version 1.0
 **/
public class CommentServiceImpl implements CommentService {
    private static Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
    private CommentDao commentDao = DaoFactory.getCommentInstance();
    @Override
    public Result insert(Comment comment) {
        try {
            commentDao.insert(comment);
        } catch (SQLException e) {
            logger.error("新增评论失败");
        }
        return null;
    }

    @Override
    public Result delete(Comment comment) {
        try {
            commentDao.delete(comment);
        } catch (SQLException e) {
            logger.error("删除评论失败");
        }
        return null;
    }

    @Override
    public Result getUserComment(Long userId) {
        List<Comment> comment = null;
        try {
            comment=commentDao.getCommentUserId(userId);
        } catch (SQLException e) {
            logger.error("查询用户评论失败");
        }
        if (comment != null) {
            return Result.success(comment);
        } else {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        }
    }

    @Override
    public Result getArticleComment(Long articleId) {
        List<Comment> comment = null;
        try {
            comment=commentDao.getComment(articleId);
        } catch (SQLException e) {
            logger.error("查询文章评论失败");
        }
        if (comment != null) {
            return Result.success(comment);
        } else {
            return Result.failure(ResultCode.USER_HAS_EXISTED);
        }
    }
}
