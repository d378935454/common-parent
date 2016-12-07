/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package utils;

import org.apache.log4j.Logger;

import java.text.MessageFormat;


/**
 * 日志封装
 * @author NINGPAI-LIH
 * @since 2015年2月26日14:38:55
 */
public class MyLogger {

    public MyLogger(Class<?> c){
        LOGGER = Logger.getLogger(c);
    }
    
    /** 记录日志对象 */
    private Logger LOGGER;
    
    /**
     * 日志记录
     * @param message 日志内容
     */
    public void error(String message){
      LOGGER.error(message);
    }
    public void error(String message,String... e){
        LOGGER.error(MessageFormat.format(message, e));
    }
    /**
     * 日志记录
     * @param message
     */
    public void error(String message,Throwable throwable){
        LOGGER.error(message,throwable);
    }
    
    /**
     * 日志记录
     * @param message
     */
    public void debug(String message){
        LOGGER.debug(message);
    }
    
    /**
     * 记录信息
     * @param message
     */
    public void info(Object message){
        LOGGER.info(message);
    }
    /**
     * 记录多个信息
     * @param message
     */
    public void info(String message,Object... objects){
        LOGGER.info(MessageFormat.format(message, objects));
    }
    
}
