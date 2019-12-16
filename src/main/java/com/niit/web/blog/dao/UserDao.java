package com.niit.web.blog.dao;

import com.niit.web.blog.domain.Vo.UserVo;
import com.niit.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author tj
 * @ClassName UserDao
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public interface UserDao {
    /**
     * 批量插入
     * @param userList
     * @return
     * @throws SQLException
     */
    void batchInsert(List<User> userList) throws SQLException;

    /**
     * 通过手机号查询
     * @param mobile
     * @return
     * @throws SQLException
     */
    User findUserByMobile(String mobile) throws SQLException;

    /**
     *新增用户
     * @param user
     * @throws SQLException
     */
    void insert(User user) throws SQLException;

    /**
     * 查询热门用户
     * @return
     * @throws SQLException
     */
    List<User> selectHotUsers() throws SQLException;

    /**
     * 查询分页用户
     * @param currentPage
     * @param count
     * @return
     * @throws SQLException
     */
    List<User> selectByPage(int currentPage, int count) throws SQLException;

    /**
     * 根据id查询用户
     * @param id
     * @return
     * @throws SQLException
     */
    UserVo getUser(long id) throws SQLException;


    /**
     * 模糊搜索用户
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<User> selectByKeywords(String keywords) throws SQLException;

    /**
     * 根据id删除用户
     * @param userId
     * @throws SQLException
     */
    void deleteUserById(Long userId) throws SQLException;


    /**
     * 修改用户
     * @param user
     * @return
     * @throws SQLException
     */
    void changeUser(User user) throws SQLException;


}
