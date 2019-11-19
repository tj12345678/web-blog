package com.niit.web.blog.domain.Dto;

import lombok.Data;

@Data
public class UserDto {
    private String mobile;
    private String password;
//    数据传输对象
    public UserDto(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public UserDto() {
    }
}
