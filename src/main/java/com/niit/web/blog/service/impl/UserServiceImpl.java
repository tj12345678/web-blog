package com.niit.web.blog.service.impl;

import com.niit.web.blog.dao.UserDao;
import com.niit.web.blog.domain.Dto.UserDto;
import com.niit.web.blog.entity.User;
import com.niit.web.blog.factory.DaoFactory;
import com.niit.web.blog.service.UserService;
import com.niit.web.blog.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tj
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2019/11/18
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {
    private UserDao userDao= DaoFactory.getUserDaoInstance();
    private static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);




    @Override
    public Map<String, Object> signIn(UserDto userDto) {
        User user = null;
        Map<String,Object> map= new HashMap<>();
        try {
            user=userDao.findUserByMobile(userDto.getMobile());
        } catch (SQLException e) {
            logger.error("根据手机号查询用户出现异常");
        }
        if(user!=null){
//            将前端获得的密码与user中的密码进行比较
            if(user.getPassword().equals(userDto.getPassword())){
                map.put("msg", Message.SIGN_IN_SUCCESS);
                map.put("data",user);
            }else{
                map.put("msg",Message.PASSWORD_ERROR);
            }
        }else{
            map.put("msg",Message.MOBILE_NOT_FOUND);
        }
        return map;
    }
}
