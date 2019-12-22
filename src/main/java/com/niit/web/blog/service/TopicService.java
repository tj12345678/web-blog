package com.niit.web.blog.service;

import com.niit.web.blog.entity.Topic;
import com.niit.web.blog.util.Result;

/**
 * @author tj
 * @ClassName TopicService
 * @Description TODO
 * @Date 2019/12/3
 * @Version 1.0
 **/
public interface TopicService {

    /**
     * 获取热门专题
     * @return
     */
    Result getHotTopics();


    /**
     * 根据id获取专题详情
     * @param id
     * @return
     */
    Result getTopic(long id);

    /**
     * 根据名称或描述模糊搜索专题
     *
     * @param keywords
     * @return
     */
    Result selectByKeywords(String keywords);


    /**
     * 分页查询专题信息
     * @param currentPage
     * @param count
     * @return
     */
    Result selectByPage(int currentPage,int count);

    /**
     * 新增专题
     * @param topic
     * @return
     */
    Result insert(Topic topic);

    /**
     * 删除专题
     * @param id
     * @return
     */
    Result delete(Long id);

    /**
     * 修改专题
     * @param topic
     * @return
     */
    Result change(Topic topic);
}
