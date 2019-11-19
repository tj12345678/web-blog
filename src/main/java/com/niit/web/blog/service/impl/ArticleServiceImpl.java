package com.niit.web.blog.service.impl;

import com.niit.web.blog.dao.ArticleDao;
import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @author tj
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Override
    public List<Article> listArticle() {
        List<Article> articleList=null;

        try {
            articleList = articleDao.selectAll();
        } catch (SQLException e) {
            logger.error("查询所有文章信息出现异常");
        }
        return articleList;
    }

    @Override
    public List<ArticleVo> listAuthorArticle(long id) {
        List<ArticleVo> articleVoList = null;
        try {
            articleVoList = articleDao.selectAuthorArticle(id);
        } catch (SQLException e) {
            logger.error("查询登录用户文章信息异常");
        }
        return articleVoList;
    }
}
