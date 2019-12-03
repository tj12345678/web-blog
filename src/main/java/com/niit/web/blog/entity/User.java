package com.niit.web.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author tj
 * @ClassName User
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String mobile;
    private String password;
    private String nickname;
    private String avatar;
    private String gender;
    private LocalDate birthday;
    private String address;
    private String introduction;
    private String homepage;
    private Short follows;
    private Short fans;
    private Short articles;
    private LocalDateTime createTime;
    private Short status;

    public User(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }
}
