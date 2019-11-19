package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.domain.Dto.UserDto;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.UserService;
import com.niit.web.blog.util.Message;
import com.niit.web.blog.util.ResponseObject;
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
import java.util.Map;

/**
 * @author tj
 * @ClassName UserController
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/sign-in")
public class UserController extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获得前端提交数据
        BufferedReader reader= req.getReader();
//        新建一个可变的字符序列
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
//        通过while循环，一行一行的读入内容
        while ((line=reader.readLine())!=null){
//            将得到的字符放入stringBuilder中
            stringBuilder.append(line);
        }
//        将错误打入日志
        logger.info("登录用户信息：" + stringBuilder.toString());
//        new一个gson
        Gson gson = new GsonBuilder().create();
//        将得到的gson类型数据转换成java类型存入userDto中
        UserDto userDto = gson.fromJson(stringBuilder.toString(),UserDto.class);
//        定义map获取signIn的返回值
        Map<String,Object> map= userService.signIn(userDto);
        String msg = (String) map.get("msg");
        ResponseObject ro;
        if(msg.equals(Message.SIGN_IN_SUCCESS)){
//            如果登录成功，取出键位data对应的值
            ro=ResponseObject.success(200,msg,map.get("data"));
        }else{
            ro = ResponseObject.success(200,msg);
        }
//        定义输出流
        PrintWriter out = resp.getWriter();
//        转换为gsons数据并输出
        out.print(gson.toJson(ro));
        out.close();

    }
}
