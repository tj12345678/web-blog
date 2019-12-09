package com.niit.web.blog.service;

import com.niit.web.blog.util.Result;

import java.sql.SQLException;

/**
 * @author tj
 * @ClassName FollowService
 * @Description TODO
 * @Date 2019/12/5
 * @Version 1.0
 **/
public interface FollowService {
    /**
     * 根据用户id获取他关注的用户列表
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    Result getUserFollows(long userId);

    /**
     * 获取用户的粉丝列表
     *
     * @param userId
     * @return
     * @throws SQLException
     */
    Result getUserFans(long userId) ;

    /**
     * 根据专题id获取其所有关注用户
     * @param topicId
     * @return
     * @throws SQLException
     */
    Result getTopicFollows(long topicId) ;
}
