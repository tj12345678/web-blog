package com.niit.web.blog.controller;

import com.niit.web.blog.util.DataUtil;
import com.niit.web.blog.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 获取验证码接口
 * @author tj
 * @ClassName CodeController
 * @Description TODO
 * @Date 2019/11/19
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/code")
public class CodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.调用字符串生成的方法，生成随机字符串
        String codeString = DataUtil.getVerification(6);
//        2.存入session，同时会返回给客户端的cookie
        HttpSession session = req.getSession();
        session.setAttribute("code",codeString);
//        req.getSession().setAttribute("code",codeString);
//        3.调用生成图形的工具方法，传入图片的高，宽，需要写入的字符出，得到的图形像
        BufferedImage img = ImageUtil.getImage(150,40,codeString);

        resp.setContentType("image/jpg");
//        6.通过输出流，将数组内容传送到客户端
        OutputStream outputStream = resp.getOutputStream();
        ImageIO.write(img,"jpg",outputStream);
//        7.关流
        outputStream.close();


    }
}
