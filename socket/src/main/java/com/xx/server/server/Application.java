/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.server;

import com.google.common.base.Preconditions;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import utils.MyLogger;

import java.net.URL;

/**
 * @author gukaitong
 * @since 1.0
 */
public class Application implements ApplicationContextAware {
    private MyLogger LOGGER = new MyLogger(Application.class);
    private static Application INSTANCE;
    private ApplicationContext context;
    protected ApplicationConfig config;
    private long startTime = System.currentTimeMillis();

    public final static int STARTING = 1;
    public final static int RUNNING = 2;
    public final static int SHUTTING_DOWN = 3;

    private volatile int state = 0;

    public synchronized void start() {
        LOGGER.info("正在启动应用程序 ...");
        INSTANCE = this;
        state = STARTING;

        try {
            this.loadConfig().print();

            this.onStarting();

            LOGGER.info("Adding shutdown hook");
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    state = SHUTTING_DOWN;
                    try {
                        LOGGER.info("*** 将要关闭应用程序,因为JVM正在被关闭 ***");
                        Application.this.stop();
                    } catch (Exception e) {
                        LOGGER.error("应用程序关闭失败: {0}", e.getMessage());
                    }
                }
            }, "Shutdown-Hook"));

            state = RUNNING;

            this.onStarted();
        } catch (Exception e) {
            LOGGER.error("应用程序启动失败: {0}", e.getMessage());
            System.exit(0);
        }
    }

    public static Application getInstance() {
        Preconditions.checkState(INSTANCE != null, "应用程序还未启动，请稍后调用");
        return INSTANCE;
    }

    /**
     * 判断当前服务器是否处于可服务状态，当服务器正在启动或正在停止期间是不对外提供服务的，因此来自客户端的任何请求和接入都要被拒绝
     *
     * @return
     */
    public boolean isAvailable() {
        return this.state == RUNNING;
    }

    public boolean isRunning() {
        return state == RUNNING;
    }

    public int getState() {
        return state;
    }

    private ApplicationConfig loadConfig() {
        LOGGER.info("正在加载应用程序配置 ...");
        try {
//            String configFile = this.getClass().getResource("/server.properties").getFile();
            URL url = this.getClass().getResource("/server.properties");
            config = new ApplicationConfig(url);
        } catch (Exception e) {
            LOGGER.error("加载应用程序配置失败: {0}", e.getMessage());
            System.exit(0);
        }

        return config;
    }

    /**
     * 获得应用程序正常运行的时间
     *
     * @return 返回服务器持续运行的时间，单位：毫秒
     */
    public long uptime() {
        return System.currentTimeMillis() - startTime;
    }

    public ApplicationConfig getConfig() {
        return config;
    }

    protected void onStarting() {
    }

    protected void onStarted() {
    }

    public void stop() {
        LOGGER.info("*** 正在关闭服务器 ***");

        System.err.println("*** 应用程序已关闭 ***");
    }

    public boolean isDebugMode() {
        return getConfig().getBoolean("debug", false);
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
