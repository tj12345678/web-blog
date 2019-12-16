package com.niit.web.blog.service;

import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.util.Result;
import org.junit.Test;

public class ArticleServiceTest {
    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();

    @Test
    public void getHotArticles() {
        Result result =  articleService.getHotArticles();
        System.out.println(result.getData());
    }

    @Test
    public void getArticlesByPage() {
    }

    @Test
    public void getArticle() {
        Result result =  articleService.getArticle(1);
        System.out.println(result.toString());

    }

    @Test
    public void selectByKeywords() {
    }

    @Test
    public void insert() {
        Result result = new Result();
        Article article = new Article();
        article.setUserId((long) 31);
        article.setTopicId((long) 23);
        article.setTitle("字");
        article.setContent("abcdefghou");
        result=articleService.insert(article);
        System.out.println(result.toString());
    }

    @Test
    public void delete() {
        Result result = new Result();
        Article article = new Article();
        article.setId((long) 30);
        result = articleService.delete(article.getId());
    }

    @Test
    public void changeArticle() {
        Result result = new Result();
        Article article = new Article();
        article.setId((long) 31);
        article.setUserId((long) 31);
        article.setTopicId((long) 23);
        article.setTitle("字");
        article.setContent("abcde");
        result = articleService.changeArticle(article);

    }
}