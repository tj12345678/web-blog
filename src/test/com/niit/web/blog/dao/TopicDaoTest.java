package com.niit.web.blog.dao;

import com.niit.web.blog.domain.Vo.TopicVo;
import com.niit.web.blog.entity.Topic;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.util.JSoupSpider;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TopicDaoTest {
    private TopicDao topicDao = DaoFactory.getTopicDaoInstance();

    @Test
    public void batchInsert() throws SQLException {
        topicDao.batchInsert(JSoupSpider.getTopics());
    }

    @Test
    public void selectHotTopics() throws SQLException {
        List<Topic> topicList = topicDao.selectHotTopics();
        System.out.println(topicList.size());
    }

    @Test
    public void selectByKeywords() throws SQLException {
        List<Topic> topicList = topicDao.selectByKeywords("人物");
        topicList.forEach(System.out::println);
    }

    @Test
    public void getTopic() throws SQLException {
        TopicVo topicVo = topicDao.getTopic(1);
        System.out.println(topicVo);
    }


    @Test
    public void selectAll() throws SQLException {
        List<Topic> topic = topicDao.selectAll();
        System.out.println(topic.size());
    }

    @Test
    public void selectByPage() throws SQLException {
        List<Topic> topic = topicDao.selectByPage(3,4);
        System.out.println(topic.toString());
    }
}