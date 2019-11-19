package com.niit.web.blog.dao;

import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;

import java.sql.SQLException;
import java.util.List;

/**
 * @author tj
 * @ClassName ArticleDao
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public interface ArticleDao {

    /**
     * 查询所有信息
     * @return
     * @throws SQLException
     */
    List<Article> selectAll() throws SQLException;

    /**
     * 批量插入文本信息
     * @param articleList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<Article> articleList) throws SQLException;


    /**
     * 查看登录用户的文章信息
     * @param id
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectAuthorArticle(long id) throws SQLException;
}
