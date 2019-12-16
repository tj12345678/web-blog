package com.niit.web.blog.dao;

import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class ArticleDaoTest {

    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();

    @Test
    public void batchInsert() throws SQLException {
        articleDao.batchInsert(JSoupSpider.getArticles());
    }

    @Test
    public void selectHotArticles() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectHotArticles();
        System.out.println(articleVoList.size());
    }

    @Test
    public void getArticle() throws SQLException {
        ArticleVo article = articleDao.getArticle(1);
        System.out.println(article);
    }

    @Test
    public void selectByKeywords() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectByKeywords("微");
        System.out.println(articleVoList.size());
    }

    @Test
    public void selectByPage() throws SQLException {
        List<ArticleVo> articleVoList = articleDao.selectByPage(1, 10);
        articleVoList.forEach(System.out::println);
    }

    @Test
    public void insert() throws SQLException {
        Article article = new Article();
        article.setUserId((long) 31);
        article.setTopicId((long) 23);
        article.setTitle("字母");
        article.setContent("abcdefghou");
        articleDao.insert(article);
    }

    @Test
    public void delete() throws SQLException {
        ArticleVo article = articleDao.getArticle(29);
        articleDao.delete(article.getArticle().getId());
    }

    @Test
    public void changeArticle() throws SQLException {
        ArticleVo article = articleDao.getArticle(29);
        article.getArticle().setTitle("字母123");
        articleDao.changeArticle(article.getArticle());
    }
}