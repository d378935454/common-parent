package com.xx.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gukt
 * @version 1.0
 */
public class SmartServer  {

    public static void main(String[] args) {
//        System.setProperty("org.jboss.logging.provider", "slf4j");
        new ClassPathXmlApplicationContext(new String[] {"classpath:spring-mybatis.xml","classpath:context.xml"});
    }
}
