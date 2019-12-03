package com.niit.web.blog.domain.Vo;

import com.niit.web.blog.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author tj
 * @ClassName UserVo
 * @Description TODO
 * @Date 2019/12/2
 * @Version 1.0
 **/
@Data
public class UserVo {
    private User user;
    private List<ArticleVo> articleList;
}
