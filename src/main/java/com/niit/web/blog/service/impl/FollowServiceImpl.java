package com.niit.web.blog.service.impl;

import com.niit.web.blog.dao.FollowDao;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.service.FollowService;
import com.niit.web.blog.util.Result;
import com.niit.web.blog.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tj
 * @ClassName FollowServiceImpl
 * @Description TODO
 * @Date 2019/12/5
 * @Version 1.0
 **/
public class FollowServiceImpl implements FollowService{
    private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private FollowDao followDao = DaoFactory.getFollowDaoInstance();

    @Override
    public Result getUserFollows(long userId)  {
        List<User> userList = new ArrayList<>();
        try {
            userList = followDao.getUserFollows(userId);
        } catch (SQLException e){
            logger.error("获取用户数据失败");
        }
        if (userList != null) {
            return Result.success(userList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result getUserFans(long userId) {
        List<User> userList = new ArrayList<>();
        try {
            userList = followDao.getUserFans(userId);
        } catch (SQLException e){
            logger.error("获取用户数据失败");
        }
        if (userList != null) {
            return Result.success(userList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }

    @Override
    public Result getTopicFollows(long topicId) {
        List<User> userList = new ArrayList<>();
        try {
            userList = followDao.getTopicFollows(topicId);
        } catch (SQLException e){
            logger.error("获取用户数据失败");
        }
        if (userList != null) {
            return Result.success(userList);
        } else {
            return Result.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
    }
}
