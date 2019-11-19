package com.niit.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.service.ArticleService;
import com.niit.web.blog.util.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author tj
 * @ClassName ArticleController
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/article","/article/*"})
public class ArticleController extends HttpServlet {
    ArticleService articleService = ServiceFactory.getArticleServiceInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ResponseObject ro =new ResponseObject();
        String requestpath = req.getRequestURI().trim();

        List<Article> articleList=null;
        List<ArticleVo> articleVoList=null;
        if(requestpath.equals("/article")){
//            查询所有文章信息
            articleList = articleService.listArticle();
            System.out.println("获取成功");
        }else{
            int position = requestpath.lastIndexOf("/");
            String id = requestpath.substring(position+1);
//            两表联查，查询用户对应的文章信息
            articleVoList = articleService.listAuthorArticle(Long.parseLong(id));
            System.out.println("加载成功");
        }
        ro.setCode(resp.getStatus());
        if (resp.getStatus()==200){
            ro.setMsg("请求成功");
        }else{
            ro.setMsg("请求失败");
        }

        if (articleList!=null){
            ro.setData(articleList);
        }else{
            ro.setData(articleVoList);
        }
        Gson gson = new GsonBuilder().create();

//        从response中取以个响应客户端的流对象
        PrintWriter out = resp.getWriter();

//        通过out流将Java代码转换为gson数据
        out.print(gson.toJson(ro));
        out.close();
    }
}
