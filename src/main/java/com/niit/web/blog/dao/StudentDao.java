package com.niit.web.blog.dao;

import com.niit.web.blog.entity.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * @author tj
 * @ClassName StudentDao
 * @Description TODO
 * @Date 2019/11/5
 * @Version 1.0
 **/
public abstract interface StudentDao {
    /**
     * 查询所有学生
     * @return
     * @throws SQLException
     */
    public abstract List<Student> selectAll() throws SQLException;

    /**
     * 批量插入学生
     * @param studentList
     * @return
     * @throws SQLException
     */
    public abstract int[] batchInsert(List<Student> studentList) throws SQLException;
}
