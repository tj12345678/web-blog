package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.FollowService;
import com.niit.web.blog.util.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author tj
 * @ClassName FollowControler
 * @Description TODO
 * @Date 2019/12/5
 * @Version 1.0
 **/
@WebServlet(urlPatterns={"/api/follow","/api/follow/*"})
public class FollowControler extends HttpServlet {
    private FollowService followService= ServiceFactory.getFollowServiceInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取得请求地址
        String url = req.getRequestURI().trim();
        if ("/api/follow".equals(url)) {
            String userIdFollow = req.getParameter("userIdFollow");
            String userIdFans = req.getParameter("userIdFans");
            String topicId = req.getParameter("topicId");
            if (userIdFollow != null) {
                getUserFollows(resp, Integer.parseInt(userIdFollow));
            } else if (userIdFans != null) {
                getUserFans(resp, Integer.parseInt(userIdFans));
            } else {
                getTopicFollows(resp, Integer.parseInt(topicId));
            }
        }
    }

    private void getTopicFollows(HttpServletResponse resp, int topicId) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = followService.getTopicFollows(topicId);
        PrintWriter out = resp.getWriter();
        System.out.println(result.getData());
        out.print(gson.toJson(result));
        out.close();
    }


    private void getUserFans(HttpServletResponse resp, int userIdFans) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = followService.getUserFans(userIdFans);
        PrintWriter out = resp.getWriter();
        System.out.println(result.getData());
        out.print(gson.toJson(result));
        out.close();
    }

    private void getUserFollows(HttpServletResponse resp, int userIdFollow) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = followService.getUserFollows(userIdFollow);
        PrintWriter out = resp.getWriter();
        System.out.println(userIdFollow);

        System.out.println(result.getData());
        out.print(gson.toJson(result));
        out.close();
    }
}
