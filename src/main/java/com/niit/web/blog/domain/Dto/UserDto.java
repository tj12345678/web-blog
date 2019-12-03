package com.niit.web.blog.domain.Dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private String mobile;
    private String password;
    private String code;
}
