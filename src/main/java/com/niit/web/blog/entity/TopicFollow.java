package com.niit.web.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tj
 * @ClassName TopicFollow
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicFollow {
    private Long id;
    private Long userId;
    private Long topicId;
}
