package com.niit.web.blog.service;

import com.niit.web.blog.domain.Dto.UserDto;
import com.niit.web.blog.factory.ServiceFactory;
import com.niit.web.blog.util.Result;
import org.junit.Test;

public class UserServiceTest {
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn() {
        Result result = new Result();
        UserDto userDto = new UserDto();
        userDto.setMobile("13910676118");
        userDto.setPassword("111");

        result=userService.signIn(userDto);
        System.out.println(result.toString());
    }

    @Test
    public void getHotUsers() {
    }

    @Test
    public void selectByPage() {
    }

    @Test
    public void getUser() {
        Result result = new Result();
        result= userService.getUser(1);
        System.out.println(result.toString());
    }

    @Test
    public void selectByKeywords() {
    }

    @Test
    public void checkMobile() {
    }

    @Test
    public void signUp() {
    }
}