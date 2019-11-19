package com.niit.web.blog.service;

import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.ServiceFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

public class ArticleServiceTest {
    private  ArticleService articleService = ServiceFactory.getArticleServiceInstance();
    private Logger logger = LoggerFactory.getLogger(ArticleServiceTest.class);

    @Test
    public void listArticle() {
        List<Article> articleList = articleService.listArticle();
        System.out.println(articleList.size());
    }

    @Test
    public void listAuthorArticle() {
        List<ArticleVo> articleVoList = articleService.listAuthorArticle(57);
        System.out.println(articleVoList.size());
    }


}