package com.niit.web.blog.service;

import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.util.Result;
import org.junit.Test;

import static org.junit.Assert.*;

public class FollowServiceTest {
    private FollowService followService = ServiceFactory.getFollowServiceInstance();

    @Test
    public void getUserFollows() {
        Result result = followService.getUserFollows(2);
        System.out.println(result.getData());
    }

    @Test
    public void getUserFans() {
        Result result = followService.getUserFans(2);
        System.out.println(result.getData());
    }

    @Test
    public void getTopicFollows() {
        Result result = followService.getTopicFollows(2);
        System.out.println(result.getData());
    }
}