package com.niit.web.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author tj
 * @ClassName Article
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
@Data
public class Article {
    private Long id;
    private Long authorId;
    private String title;
    private String description;
    private String content;
    private String avatar;
    private Integer commentAccount;
    private Integer likeAccount;
    private Integer forwardAccount;
    private LocalDateTime createTime;
}
