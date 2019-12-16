package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.domain.Dto.UserDto;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.ServiceFactory;
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
import javax.servlet.http.HttpSession;
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
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
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
            if (page != null) {
                HttpUtil.getResponseBody(resp, userService.selectByPage(Integer.parseInt(page), Integer.parseInt(count)));
            } else if (keywords != null) {
                HttpUtil.getResponseBody(resp, userService.selectByKeywords(keywords));
            } else {
                HttpUtil.getResponseBody(resp, userService.getHotUsers());
            }
        } else {
            System.out.println(url);
            HttpUtil.getResponseBody(resp, userService.getUser(Long.parseLong(HttpUtil.getPathParam(req))));
        }
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
      /*  String requestBody = HttpUtil.getRequestBody(req);
        logger.info("登录用户信息：" + requestBody);
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(requestBody, UserDto.class);
        //客户端输入的验证码
        String inputCode = userDto.getCode().trim();
        System.out.println(inputCode);
        //取得客户端请求头里带来的token
        String sessionId = req.getHeader("Access-Token");
        //从自定义的监听代码中取得之前的session对象
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        //取得当时存入的验证码
        String correctCode = session.getAttribute("code").toString();
        System.out.println(correctCode);
        //忽略大小写比对
//        System.out.println(correctCode);*/
        /*获得前端提交的数据*/
        BufferedReader reader = req.getReader();
        /*新建一个可变的字符序列*/
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        /*通过while循环，一行一行的读入内容*/
        while ((line = reader.readLine()) != null){
            /*将得到的字符放入stringBuilder中*/
            stringBuilder.append(line);
        }
        /*将错误打入日志*/
        logger.info("登录用户信息:" + stringBuilder.toString());
        /*new一个gson*/
        Gson gson = new GsonBuilder().create();
        /*将得到的gson类型数据转换成java类型存入userDto中*/
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        String inputCode = userDto.getCode().trim();
        /*获得客户端请求头里带来的token*/
        String sessionId = req.getHeader("Access-Token");
        System.out.println("客户端传来的JSESSIONID: " + sessionId);
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        String correctCode = session.getAttribute("code").toString();
        System.out.println("正确的验证码：" + correctCode);
        PrintWriter out = resp.getWriter();
        String msg = null;
        ResponseObject ro;
        if(inputCode.equalsIgnoreCase(correctCode)){
            /*定义map获取signIn的返回值*/
            Result rs = userService.signIn(userDto);
            /* 通过map.get方法获得键为msg对应的值*/
            out.print(gson.toJson(rs));
        }else {
            out.print(Result.failure(ResultCode.USER_VERIFY_CODE_ERROR));
        }
        out.close();
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
        UserDto user= gson.fromJson(stringBuilder.toString(),UserDto.class);
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        System.out.println(user.toString());
        Result result;
        result = userService.signUp(user);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }
}
