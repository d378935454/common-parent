/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.message;

import com.xx.server.server.ExecutionException;
import com.xx.server.util.Errors;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import utils.MyLogger;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author gukaitong
 * @since 1.0
 */
public class PacketMessageDispatcher implements ApplicationContextAware {
    private static final MyLogger LOGGER = new MyLogger(PacketMessageDispatcher.class);
    protected Map<Object, MessageHandler<?>> handlersByKey = new HashMap<>();
    private ApplicationContext context;

    @PostConstruct
    public void init() {
        Map<String, Object> beansMap = new HashMap<>();
        beansMap.putAll(context.getBeansWithAnnotation(Service.class));
        beansMap.putAll(context.getBeansWithAnnotation(Controller.class));

        Map<Object, MessageHandler<?>> handlersMap = PacketMessageHandlerFinder.find(beansMap.values());
        for (Map.Entry<Object, MessageHandler<?>> entry : handlersMap.entrySet()) {
            this.registerHandler(entry.getKey(), entry.getValue());
        }
    }

    private void registerHandler(Object key, MessageHandler<?> handler) {
        checkNotNull(key);
        checkNotNull(handler);

        LOGGER.info("正在注册handler: {0} --> {1}", key, handler);

        this.handlersByKey.put(key, handler);
    }

    public void dispatch(Channel channel, Packet packet) {
        if (packet != null) {
            MessageHandler<Packet> handler = getHandler(packet.getCmd());

            if (handler != null) {
                try {
                    handler.handle(channel, packet);
                } catch (Exception e) {
                    if (e instanceof ExecutionException) {
                        ExecutionException e1 = (ExecutionException) e;
                        LOGGER.info("{0}({1}), {2}", e1.getErrCode(), e1.getErrCode(), e1.getMessage());
                        Errors.sendError(channel, packet.getBoxId(), e1.getErrCode(), e1.getMessage());
                    } else {
                        // 其他类型的异常一律通知客户端发生SERVER_INTERNAL_ERROR
                        LOGGER.error("处理消息{0}时发生异常：{1}", packet.getCmd(), e.getMessage());
                        Errors.sendError(channel, packet.getBoxId(), Errors.SERVER_INTERNAL_ERROR, e.getMessage());
                    }
                }
            } else {
                LOGGER.error("*** No handler for {0} ***", packet.getCmd());
            }
        }
    }

    @SuppressWarnings("unchecked")
    protected MessageHandler<Packet> getHandler(Object key) {
        return (MessageHandler<Packet>) handlersByKey.get(checkNotNull(key));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
