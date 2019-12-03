package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Region;

import java.sql.SQLException;
import java.util.List;

/**
 * @author tj
 * @ClassName RegionDao
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
public interface RegionDao {
    /**
     *  查询所有地址
     *
     * @return
     */
    List<Region> selectAll()throws SQLException;
}
