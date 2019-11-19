package com.niit.web.blog.dao;

import com.niit.web.blog.dao.impl.StudentDaoImpl;
import com.niit.web.blog.entity.Student;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.JSoupDemo;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDaoTest {
    private StudentDao studentDao = DaoFactory.getStudentDaoInstance();

    @Test
    public void selectAll() throws SQLException {
        List<Student> students=studentDao.selectAll();
        students.forEach(student-> System.out.println(student));
    }

    @Test
    public void batchInsert() throws SQLException {
        List<Student> students= JSoupDemo.getData();


        int[] n =studentDao.batchInsert(students);
        System.out.println(n.length);
    }
}