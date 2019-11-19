package com.niit.web.blog.util;

import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tj
 * @ClassName JSoupSpider
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public class JSoupSpider {
    private static Logger logger = LoggerFactory.getLogger(JSoupSpider.class);
    private static DataUtil UserDataUtil;


    public static List<User> getUsers() {
        Document document = null;
        List<User> userList = new ArrayList<>(100);
        for (int i = 1; i <= 3; i++) {
            try {
                document = Jsoup.connect("https://www.jianshu.com/recommendations/users?utm_source=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("col-xs-8");
            divs.forEach(div -> {
                Element wrapDiv = div.child(0);
                Element link = wrapDiv.child(0);
                Elements linkChildren = link.children();
                User user = new User();
                user.setMobile(UserDataUtil.getMobile());
                user.setPassword(UserDataUtil.getPassword());
                user.setGender(UserDataUtil.getGender());
                user.setAvatar("https:" + linkChildren.get(0).attr("src"));
                user.setNickname(linkChildren.get(1).text());
                user.setIntroduction(linkChildren.get(2).text());
                user.setBirthday(UserDataUtil.getBirthday());
                user.setCreateTime(LocalDateTime.now());
                userList.add(user);
            });
        }
        return userList;
    }

    /**
     * 爬取文章信息
     *
     * @return
     */
    public static List<Article> getArticles() {
        Document document = null;

        List<Article> articleList = new ArrayList<>(100);
        for (int i = 0; i <= 3; i++) {

            try {
                document = Jsoup.connect("https://www.jianshu.com/c/0b6ad2de4b21?order_by=top&count=50&page=ource=desktop&utm_medium=index-users&page=" + i).get();
            } catch (IOException e) {
                logger.error("连接失败");
            }
            Elements divs = document.getElementsByClass("have-img");
            divs.forEach(div -> {
                String articleUrl = div.child(0).attr("href");
                Document document1 = null;

                try {
                    document1 = Jsoup.connect("https://www.jianshu.com" + articleUrl).get();
                } catch (IOException e) {
                    logger.error("文章获取失败");
                }
                Element articleElement = document1.getElementsByClass("_2rhmJa").first();
                Article article = new Article();
                article.setContent(articleElement.html());
                Elements elements = div.children();
                Element linkElement = elements.get(0);
                Element divElement = elements.get(1);
                article.setAuthorId(DataUtil.getAuthorid());
                article.setTitle(divElement.child(0).text());
                article.setForwardAccount(DataUtil.getForwordAccount());
                article.setDescription(divElement.child(1).text());
                String img = "https:" + linkElement.child(0).attr("src");
                int index = img.indexOf("?");
                article.setAvatar(img.substring(0, index));
                Elements metachildren = divElement.child(2).children();
                String comments = metachildren.get(2).text();
                String likes = metachildren.last().text();

                article.setCommentAccount(Integer.parseInt(comments));
                article.setLikeAccount(Integer.parseInt(likes));

                article.setCreateTime(LocalDateTime.now());
                articleList.add(article);

            });
        }
        System.out.println(articleList.size());
        return articleList;
    }
}
