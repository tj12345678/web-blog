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
     * 批量插入文本信息
     * @param articleList
     * @return
     * @throws SQLException
     */
    void batchInsert(List<Article> articleList) throws SQLException;

    /**
     * 添加文章
     * @param article
     * @throws SQLException
     */
    void insert(Article article) throws SQLException;

    /**
     * 删除文章
     * @param id
     * @throws SQLException
     */
    void delete(Long id) throws SQLException;

    /**
     * 修改文章
     * @param article
     * @throws SQLException
     */
    void changeArticle(Article article) throws SQLException;


    /**
     * 查询热门文章，返回视图集合
     *
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectHotArticles() throws SQLException;

    /**
     * 分页获得文章数据
     *
     * @param currentPage
     * @param count
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectByPage(int currentPage, int count) throws SQLException;


    /**
     * 根据关键字模糊查询所有文章
     *
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectByKeywords(String keywords) throws SQLException;


    /**
     * 根据专题id查询所有文章
     *
     * @param topicId
     * @return
     * @throws SQLException
     */

    List<ArticleVo> selectByTopicId(long topicId) throws SQLException;


    /**
     * 根据作者id查询所有文章
     * @param userId
     * @return
     * @throws SQLException
     */
    List<ArticleVo> selectByUserId(long userId) throws SQLException;

    /**
     * 根据id获取文章详情
     *
     * @param id
     * @return
     * @throws SQLException
     */
    ArticleVo getArticle(long id) throws SQLException;
}
