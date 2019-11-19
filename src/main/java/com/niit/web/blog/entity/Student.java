package com.niit.web.blog.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author tj
 * @ClassName Student
 * @Description TODO
 * @Date 2019/11/5
 * @Version 1.0
 **/
@Data
public class Student {
    private Integer id;
    private String username;
    private String avatar;
    private String intrlduction;
    private LocalDateTime createTime;



}
