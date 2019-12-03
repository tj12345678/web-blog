package com.niit.web.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author tj
 * @ClassName Topic
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
@Data
public class Topic {
    private Long id;
    private Long adminId;
    private String topicName;
    private String logo;
    private String description;
    private String homepage;
    private Integer articles;
    private Integer follows;
    private LocalDateTime createTime;
}
