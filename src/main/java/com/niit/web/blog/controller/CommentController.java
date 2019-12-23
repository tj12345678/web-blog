package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.entity.Comment;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.CommentService;
import com.niit.web.blog.util.Result;

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
 * @ClassName CommentController
 * @Description TODO
 * @Date 2019/12/11
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/comment")
public class CommentController extends HttpServlet {
    private CommentService commentService = ServiceFactory.getCommentServiceInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader=req.getReader();
        StringBuilder stringBuilder=new StringBuilder();
        String line=null;
        while((line=reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        Gson gson=new GsonBuilder().create();
        Comment comment= gson.fromJson(stringBuilder.toString(),Comment.class);
        Result result;
        result =commentService .insert(comment);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().trim();

    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BufferedReader reader=req.getReader();
        StringBuilder stringBuilder=new StringBuilder();
        String line=null;
        while((line=reader.readLine())!=null){
            stringBuilder.append(line);
        }
        System.out.println(stringBuilder.toString());
        Gson gson=new GsonBuilder().create();
        Comment comment= gson.fromJson(stringBuilder.toString(),Comment.class);
        Result result = commentService.delete(comment);
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }
}
