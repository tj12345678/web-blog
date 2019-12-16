package com.niit.web.blog.service;

import com.niit.web.blog.entity.Article;
import com.niit.web.blog.util.Result;

/**
 * @author tj
 * @ClassName ArticleService
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public interface ArticleService {
    /**
     * 获取热门文章
     *
     * @return
     */
    Result getHotArticles();

    /**
     * 发布文章
     * @param article
     * @return
     */
    Result insert(Article article);

    /**
     * 删除文章
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 修改文章
     * @param article
     * @return
     */
    Result changeArticle(Article article);
    /**
     * 获取分页文章
     *
     * @param currentPage
     * @param count
     * @return
     */
    Result getArticlesByPage(int currentPage, int count);

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    Result getArticle(long id);


    /**
     * 根据标题或摘要模糊查询文章
     *
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);
}
