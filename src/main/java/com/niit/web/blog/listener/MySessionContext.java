package com.niit.web.blog.listener;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author tj
 * @ClassName MySessionContext
 * @Description TODO
 * @Date 2019/12/17
 * @Version 1.0
 **/
public class MySessionContext {
    private static MySessionContext instance;
    private HashMap<String, HttpSession> sessionMap;

    private MySessionContext() {
        sessionMap = new HashMap<String, HttpSession>();
    }

    public static MySessionContext getInstance() {
        if (instance == null) {
            instance = new MySessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }
}
