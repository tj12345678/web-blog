package com.niit.web.blog.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author tj
 * @ClassName SessionListener
 * @Description TODO
 * @Date 2019/12/17
 * @Version 1.0
 **/
public class SessionListener implements HttpSessionListener {
    private MySessionContext myc = MySessionContext.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        myc.addSession(session);
        System.out.println("新的session创建：" + session.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        myc.delSession(session);
    }

}
