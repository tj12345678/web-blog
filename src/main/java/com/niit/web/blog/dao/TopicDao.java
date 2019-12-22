package com.niit.web.blog.dao;

import com.niit.web.blog.domain.Vo.TopicVo;
import com.niit.web.blog.entity.Topic;

import java.sql.SQLException;
import java.util.List;

/**
 * @author tj
 * @ClassName TopicDao
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
public interface TopicDao {
    /**
     * 批量新增专题
     *
     * @param topicList
     * @return
     * @throws SQLException
     */
    void batchInsert(List<Topic> topicList) throws SQLException;

    /**
     * 获取所有专题
     * @return
     * @throws SQLException
     */
    List<Topic> selectAll() throws SQLException;


    /**
     * 获取热门专题
     * @return
     * @throws SQLException
     */
    List<Topic> selectHotTopics() throws SQLException;

    /**
     * 分页获取专题
     * @param currentPage
     * @param count
     * @return
     * @throws SQLException
     */
    List<Topic> selectByPage(int currentPage, int count)throws SQLException;

    /**
     * 根据id获取专题详情
     * @param id
     * @return
     * @throws SQLException
     */
    TopicVo getTopic(long id)throws SQLException;

    /**
     * 模糊搜索专题
     * @param keywords
     * @return
     * @throws SQLException
     */
    List<Topic> selectByKeywords(String keywords) throws SQLException;

    /**
     * 添加专题
     * @param topic
     * @throws SQLException
     */
    void insert(Topic topic) throws  SQLException;

    /**
     * 删除专题
     * @param id
     * @throws SQLException
     */
    void delete(Long id) throws SQLException;

    /**
     * 修改专题
     * @param topic
     * @throws SQLException
     */
    void change(Topic topic) throws SQLException;
}
