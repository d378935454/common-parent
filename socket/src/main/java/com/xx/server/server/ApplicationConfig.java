/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.server;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.MyLogger;

import java.net.URL;
import java.util.Iterator;

/**
 * @author gukaitong
 * @since 1.0
 */
public class ApplicationConfig extends PropertiesConfiguration {
    private MyLogger LOGGER = new MyLogger(Application.class);

    public ApplicationConfig(URL url) throws ConfigurationException {
        super(url);

        this.setReloadingStrategy(new FileChangedReloadingStrategy());
        this.setAutoSave(true);

        for (Iterator<String> iterator = getKeys(); iterator.hasNext(); ) {
            String key = iterator.next();
            String value = resolveSystemProperty(key);
            if (value != null) {
                this.setProperty(key, value);
            }
        }
    }

    /**
     * 获得指定key的系统或环境变量值
     *
     * @param key 指定的key
     * @return 返回key对应的系统属性或环境变量值
     */
    protected String resolveSystemProperty(String key) {
        try {
            String value = System.getProperty(key);
            if (value == null) {
                value = System.getenv(key);
            }
            return value;
        } catch (Throwable ex) {
            LOGGER.error("不能访问系统属性'" + key + "': " + ex);
            return null;
        }
    }

    /**
     * 打印应用程序配置文件信息
     */
    public void print() {
        LOGGER.info("Application Configurations:");

        for (Iterator<String> iterator = this.getKeys(); iterator.hasNext(); ) {
            String key = iterator.next();
            LOGGER.info("{0}：{1}", key, this.getString(key));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        try {
            for (Iterator<String> iterator = this.getKeys(); iterator.hasNext(); ) {
                String key = iterator.next();
                sb.append(String.format("%s => %s\n", key, getProperty(key)));
            }
        } catch (Exception e) {
            LOGGER.error("{}", e);
        }

        return sb.toString();
    }
}
