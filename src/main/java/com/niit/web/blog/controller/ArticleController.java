package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.ArticleService;
import com.niit.web.blog.service.CommentService;
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
 * @ClassName ArticleController
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/article","/api/article/*"})
public class ArticleController extends HttpServlet {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private CommentService commentService = ServiceFactory.getCommentServiceInstance();



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id= req.getParameter("id");
        System.out.println(id);
        int id1=Integer.parseInt(id);
        Result result = articleService.delete((long)id1);
        Gson gson= new GsonBuilder().create();
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //取得请求地址
        String url = req.getRequestURI().trim();
        if ("/api/article".equals(url)) {
            String page = req.getParameter("page");
            String keywords = req.getParameter("keywords");
            String count = req.getParameter("count");
            String articleId=req.getParameter("articleId");
            if (page != null) {
                getArticlesByPage(resp, Integer.parseInt(page), Integer.parseInt(count));
            } else if (keywords != null) {
                getArticlesByKeywords(resp, keywords);
            } else  {
                getHotArticles(req, resp);
            }
        } else {
            getArticle(req, resp);
        }
    }



    private void getHotArticles(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.getHotArticles();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getArticlesByPage(HttpServletResponse resp, int page, int count) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.getArticlesByPage(page, count);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getArticlesByKeywords(HttpServletResponse resp, String keywords) throws ServletException, IOException {
        Gson gson = new GsonBuilder().create();
        Result result = articleService.selectByKeywords(keywords);
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }

    private void getArticle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String info = req.getPathInfo().trim();
        //取得路径参数
        String id = info.substring(info.indexOf("/") + 1);
        Result result = articleService.getArticle(Long.parseLong(id));
        System.out.println(result.toString());
        Gson gson = new GsonBuilder().create();
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
        Article article= gson.fromJson(stringBuilder.toString(),Article.class);
        Result result;
        result = articleService.insert(article);
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
        Article article=gson.fromJson(stringBuilder.toString(),Article.class);
        System.out.println(article.toString());
        Result result =articleService.changeArticle(article);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out =resp.getWriter();
        out.print(gson.toJson(result));
        out.close();
    }
}
