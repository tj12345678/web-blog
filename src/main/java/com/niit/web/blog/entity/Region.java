package com.niit.web.blog.entity;

import lombok.Data;

/**
 * @author tj
 * @ClassName Region
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
@Data
public class Region {
    private  Integer id;
    private String name;
    private Integer parentId;
    private Integer level;
    private String cityCode;
    private String postCode;
    private String mergeName;
}
