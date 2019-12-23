package com.niit.web.blog.service;

import com.niit.web.blog.entity.Comment;
import com.niit.web.blog.util.Result;

/**
 * @author tj
 * @ClassName CommentService
 * @Description TODO
 * @Date 2019/12/10
 * @Version 1.0
 **/
public interface CommentService {
    /**
     * 添加评论
     * @param comment
     * @return
     */
    Result insert(Comment comment);

    /**
     * 删除评论
     * @param comment
     * @return
     */
    Result delete(Comment comment);

    /**
     * 查询用户评论
     * @param userId
     * @return
     */
    Result getUserComment(Long userId);

    /**
     * 查询文章评论
     * @param articleId
     * @return
     */
    Result getArticleComment(Long articleId);
}
