package com.niit.web.blog.domain.Vo;

import com.niit.web.blog.entity.Article;
import com.niit.web.blog.entity.Topic;
import com.niit.web.blog.entity.User;
import lombok.Data;

/**
 * @author tj
 * @ClassName ArticleVo
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
@Data
public class ArticleVo {
    private Article article;
    private User author;
    private Topic topic;
}
