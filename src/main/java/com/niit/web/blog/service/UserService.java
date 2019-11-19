package com.niit.web.blog.service;

import com.niit.web.blog.domain.Dto.UserDto;

import java.util.Map;

/**
 * @author tj
 * @ClassName UserService
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public interface UserService {
    /**
     * 用户登录功能
     * @param userDto
     * @return
     */
    Map<String,Object> signIn(UserDto userDto);
}
