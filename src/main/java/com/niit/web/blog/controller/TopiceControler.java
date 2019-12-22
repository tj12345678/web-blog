package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.entity.Topic;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.TopicService;
import com.niit.web.blog.util.Result;
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
 * @ClassName TopiceControler
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/topic", "/api/topic/*"})
public class TopiceControler extends HttpServlet {
    private TopicService topicService = ServiceFactory.getTopicServiceInstance();
    private static Logger logger = LoggerFactory.getLogger(TopiceControler.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().trim();
        if ("/api/topic".equals(url)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            if (page != null) {
                getTopicsByPage(resp, Integer.parseInt(page), Integer.parseInt(count));
            } else if (keywords != null) {
                getTopicsByKeywords(resp, keywords);
            } else {
                getHotTopics(req, resp);
            }
        } else {
            getTopic(req, resp);
        }
    }

    private void getHotTopics(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = topicService.getHotTopics();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getTopicsByPage(HttpServletResponse resp, int page, int count) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = topicService.selectByPage(page, count);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getTopicsByKeywords(HttpServletResponse resp, String keywords) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = topicService.selectByKeywords(keywords);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getTopic(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = req.getPathInfo().trim();
        //取得路径参数
        String id = info.substring(info.indexOf("/") + 1);
        Gson gson = new GsonBuilder().create();
        Result result = topicService.getTopic(Long.parseLong(id));
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

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
        Topic topic= gson.fromJson(stringBuilder.toString(),Topic.class);
        Result result;
        result = topicService.insert(topic);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

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
        Topic topic=gson.fromJson(stringBuilder.toString(),Topic.class);
        System.out.println(topic.toString());
        Result result =topicService.change(topic);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id= req.getParameter("id");
        System.out.println(id);
        int id1=Integer.parseInt(id);
        Result result = topicService.delete((long)id1);
        Gson gson= new GsonBuilder().create();
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }
}
