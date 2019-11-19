package com.niit.web.blog.dao.impl;

import com.niit.web.blog.dao.StudentDao;
import com.niit.web.blog.entity.Student;
import com.niit.web.blog.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author tj
 * @ClassName StudentDaoImpl
 * @Description TODO
 * @Date 2019/11/5
 * @Version 1.0
 **/
public class StudentDaoImpl implements StudentDao {

    private QueryRunner runner = new QueryRunner();
    @Override
    public List<Student> selectAll() throws SQLException {
        String sql="SELECT * FROM t_student ORDER BY id DESC";

        List<Student> students=runner.query(DBUtils.getConnection(),
                sql,
                new BeanListHandler<>(Student.class));
        return students;

    }

    @Override
    public int[] batchInsert(List<Student> studentList) throws SQLException {
        Connection connection= DBUtils.getConnection();
        connection.setAutoCommit(false);
        String sql="INSERT INTO t_student VALUES (NULL,?,?,?,?)";
        PreparedStatement pstmt=connection.prepareStatement(sql);
        studentList.forEach(student -> {
            try {
                pstmt.setString(1,student.getUsername());
                pstmt.setString(2,student.getIntrlduction());
                pstmt.setString(3,student.getAvatar());
                pstmt.setTimestamp(4, Timestamp.valueOf(student.getCreateTime()));
                pstmt.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        int[] n=pstmt.executeBatch();
        connection.commit();
        pstmt.close();
        connection.close();
        return n;
    }
}
