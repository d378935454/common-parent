/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.message;

import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 该类用于查找所有的实例化对象上标有Listen注解的方法，并将这些方法转换成MessageHandler
 * Created by gukt on 2016/1/28.
 *
 * @author gukt
 * @version 1.0
 */
public class PacketMessageHandlerFinder {
    public static Map<Object, MessageHandler<?>> find(Collection<Object> beans) {
        Map<Object, MessageHandler<?>> handlersMap = new HashMap<>();

        for (Object delegate : beans) {
            Class<?> clazz = delegate.getClass();

            /** 找出有{@link Listen}注解的并且继承自{@link MessageHandler}的类 */
            if (clazz.isAnnotationPresent(Listen.class) && clazz.isAssignableFrom(MessageHandler.class)) {
                handlersMap.put(clazz.getAnnotation(Listen.class).value(), (MessageHandler<?>) delegate);
            } else {
                MethodAccess methodAccess = MethodAccess.get(clazz);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Listen.class)) {
                        handlersMap.put(method.getAnnotation(Listen.class).value(),
                                new PacketMessageHandlerAdapter(delegate, methodAccess, method.getName()));
                    }
                }
            }
        }

        return handlersMap;
    }
}
