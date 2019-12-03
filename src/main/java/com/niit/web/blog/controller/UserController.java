package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.domain.Dto.UserDto;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.UserService;
import com.niit.web.blog.util.HttpUtil;
import com.niit.web.blog.util.MySessionContext;
import com.niit.web.blog.util.ResultCode;

import com.niit.web.blog.util.UrlPatten;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.io.IOException;

/**
 * @author tj
 * @ClassName UserController
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/user", "/api/user/*"})
public class UserController extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        if (UrlPatten.USER.equals(uri)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            if (page != null) {
                HttpUtil.getResponseBody(resp, (Result) userService.selectByPage(Integer.parseInt(page), Integer.parseInt(count)));
            } else if (keywords != null) {
                HttpUtil.getResponseBody(resp, (Result) userService.selectByKeywords(keywords));
            } else {
                HttpUtil.getResponseBody(resp, (Result) userService.getHotUsers());
            }
        } else {
            System.out.println(uri);
            HttpUtil.getResponseBody(resp, (Result) userService.getUser(Long.parseLong(HttpUtil.getPathParam(req))));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().trim();
        switch (uri) {
            case UrlPatten.USER_SIGN_IN:
                signIn(req, resp);
                break;
            case UrlPatten.USER_SIGN_UP:
                signUp(req, resp);
                break;
            case UrlPatten.USER_CHECK_MOBILE:
                String mobile = req.getParameter("mobile");
                HttpUtil.getResponseBody(resp, (Result) userService.checkMobile(mobile));
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
        String sessionId = req.getHeader("Access-Token");
        //从自定义的监听代码中取得之前的session对象
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        //取得当时存入的验证码
        String correctCode = session.getAttribute("code").toString();
        //忽略大小写比对
        if (inputCode.equalsIgnoreCase(correctCode)) {
            HttpUtil.getResponseBody(resp, (Result) userService.signIn(userDto));
            //验证码正确，进入登录业务逻辑调用
        } else {
            //验证码错误，直接将错误信息返回给客户端，不要继续登录流程了
            HttpUtil.getResponseBody(resp, (Result) com.niit.web.blog.util.Result.failure(ResultCode.USER_VERIFY_CODE_ERROR));
        }
    }


    private void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("注册");
    }
}
