package com.bean.socket;

import org.springframework.beans.factory.InitializingBean;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by bean on 2016/6/20.
 */
public class SocketServlet implements InitializingBean {


    public void close(){
    }
    public void afterPropertiesSet() throws Exception {
        //在这里启动你的线程
        //方式1 利用构造方法把bean传递过去
        new Thread(new Server()).start();
        //方式2 在thread 内部使用我之前说的获取bean的方式
    }
//    private Server server=null;
//    public void init(ServletConfig config) throws ServletException {
//        new Thread(new Server()).start();
////        this.socket=server.getSocket();
//    }

}

