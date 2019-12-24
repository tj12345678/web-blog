package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.domain.Dto.UserDto;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.FollowService;
import com.niit.web.blog.service.UserService;
import com.niit.web.blog.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author tj
 * @ClassName UserController
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/user", "/api/user/*"})
public class UserController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();
    private FollowService followService= ServiceFactory.getFollowServiceInstance();



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader=req.getReader();
        StringBuilder stringBuilder =new StringBuilder();
        String line=null;
        while ((line=reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        Gson gson=new GsonBuilder().create();
        User user=gson.fromJson(stringBuilder.toString(),User.class);
        System.out.println(user.toString());
        Result result =userService.changeUser(user);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().trim();
        if (UrlPatten.USER.equals(url)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            String topicId=req.getParameter("topicId");
            if (page != null) {
                HttpUtil.getResponseBody(resp, userService.selectByPage(Integer.parseInt(page), Integer.parseInt(count)));
            } else if (keywords != null) {
                HttpUtil.getResponseBody(resp, userService.selectByKeywords(keywords));
            } else if(count !=null){
                HttpUtil.getResponseBody(resp, userService.getHotUsers());
            } else{
                getTopicFollows(resp, Integer.parseInt(topicId));
            }
        } else {
            System.out.println(url);
            HttpUtil.getResponseBody(resp, userService.getUser(Long.parseLong(HttpUtil.getPathParam(req))));
        }
    }

    private void getTopicFollows(HttpServletResponse resp, int parseInt) throws IOException {
        Gson gson = new GsonBuilder().create();
        Result result = followService.getTopicFollows(parseInt);
        PrintWriter out = resp.getWriter();
        System.out.println(result.getData());
        out.print(gson.toJson(result));
        out.close();
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id= req.getParameter("id");
        System.out.println(id);
        int id1=Integer.parseInt(id);
        Result result = userService.delete((long)id1);
        Gson gson= new GsonBuilder().create();
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().trim();
        switch (url) {
            case UrlPatten.USER_SIGN_IN:
                signIn(req, resp);
                break;
            case UrlPatten.USER_SIGN_UP:
                signUp(req, resp);
                break;
            case UrlPatten.USER_CHECK_MOBILE:
                String mobile = req.getParameter("mobile");
                HttpUtil.getResponseBody(resp, userService.checkMobile(mobile));
                break;
            default:
        }
    }

    private void signIn(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestBody = HttpUtil.getRequestBody(req);
        logger.info("登录用户信息：" + requestBody);
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(requestBody, UserDto.class);
        //客户端输入的验证码
        String inputCode = userDto.getCode().trim();
        //取得客户端请求头里带来的token
//        String sessionId = req.getHeader("Access-Token");
//        //从自定义的监听代码中取得之前的session对象
//        MySessionContext myc = MySessionContext.getInstance();
//        HttpSession session = myc.getSession(sessionId);
//        //取得当时存入的验证码
//        String correctCode = session.getAttribute("code").toString();
        //忽略大小写比对
        if (inputCode.equalsIgnoreCase(inputCode)) {
            HttpUtil.getResponseBody(resp, userService.signIn(userDto));
            //验证码正确，进入登录业务逻辑调用
        } else {
            //验证码错误，直接将错误信息返回给客户端，不要继续登录流程了
            HttpUtil.getResponseBody(resp, Result.failure(ResultCode.USER_VERIFY_CODE_ERROR));
        }

    }


    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader=req.getReader();
        StringBuilder stringBuilder=new StringBuilder();
        String line=null;
        while((line=reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        Gson gson=new GsonBuilder().create();
        User user= gson.fromJson(stringBuilder.toString(),User.class);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        user.setCreateTime(DataUtil.getNowTime());
        user.setBirthday(DataUtil.getBirthday());
        user.setAvatar("https://upload.jianshu.io/users/upload_avatars/2594450/a5bbf755-a88e-42da-9eb7-eb4c0e895683.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/180/h/180");
        System.out.println(user.toString());
        Result result;
        result = userService.signUp(user);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }
}
