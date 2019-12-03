package com.niit.web.blog.util;

import com.niit.web.blog.domain.Vo.ArticleVo;
import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tj
 * @ClassName BeanHandler
 * @Description TODO
 * @Date 2019/12/2
 * @Version 1.0
 **/
public class BeanHandler {
    private static Logger logger = LoggerFactory.getLogger(BeanHandler.class);

    public static List<User> convertUser(ResultSet rs) {
        List<User> userList = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setMobile(rs.getString("mobile"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setAvatar(rs.getString("avatar"));
                user.setGender(rs.getString("gender"));
                user.setBirthday(rs.getDate("birthday").toLocalDate());
                user.setIntroduction(rs.getString("introduction"));
                user.setAddress(rs.getString("address"));
                user.setFollows(rs.getShort("Follows"));
                user.setFans(rs.getShort("fans"));
                user.setArticles(rs.getShort("articles"));
                user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
                user.setStatus(rs.getShort("status"));
                userList.add(user);
            }
        } catch (SQLException e) {
            logger.error("用户数据结果集解析产生异常");
        }
        return userList;
    }
    public static List<ArticleVo> convertArticle(ResultSet rs) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        try {
            while (rs.next()) {
                ArticleVo articleVo = new ArticleVo();
                //文章自身信息
                Article article = new Article();
                article.setId(rs.getLong("id"));
                article.setAuthorId(rs.getLong("author_id"));
                article.setAvatar(rs.getString("avatar"));
                article.setTitle(rs.getString("title"));
                article.setContent(rs.getString("content"));
                article.setCommentAccount(rs.getInt("comment_account"));
                article.setLikeAccount(rs.getInt("like_account"));
                article.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
                //作者信息
                User author = new User();
                author.setId(rs.getLong("user_id"));
                author.setNickname(rs.getString("nickname"));
                author.setAvatar(rs.getString("avatar"));

                //给文章视图对象设置三块内容
                articleVo.setArticle(article);
                articleVo.setAuthor(author);

                //加入列表
                articleVoList.add(articleVo);
            }
        } catch (SQLException e) {
            logger.error("文章数据结果集解析异常");
        }
        return articleVoList;
    }
}
