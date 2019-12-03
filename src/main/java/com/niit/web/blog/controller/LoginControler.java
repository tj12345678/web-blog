package com.niit.web.blog.controller;

import com.niit.web.blog.util.MySessionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author tj
 * @ClassName LoginControler
 * @Description TODO
 * @Date 2019/11/20
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/login")
public class LoginControler extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        System.out.println(code);
        String sessionId = null;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if("JSESSIONID".equals(cookie.getName())){
                sessionId = cookie.getValue();
                break;
            }
        }
        System.out.println(sessionId);
        MySessionContext myc = MySessionContext.getInstance();
        HttpSession session = myc.getSession(sessionId);
        String correctCode = session.getAttribute("code").toString();
        if(code.equalsIgnoreCase(correctCode)){
            resp.getWriter().write("登录成功");
        }else {
            resp.getWriter().write("验证码错误");
        }
    }
}
