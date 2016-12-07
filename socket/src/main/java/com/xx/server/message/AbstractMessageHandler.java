/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.message;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.base.Objects;
import com.xx.server.server.ExecutionException;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gukaitong
 * @since 1.0
 */
public abstract class AbstractMessageHandler<T> implements MessageHandler<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMessageHandler.class);

    protected Object delegate;
    protected MethodAccess methodAccess;
    protected String methodName;
    protected int methodIndex;

    public AbstractMessageHandler(Object delegate, MethodAccess methodAccess, String methodName) {
        this.delegate = delegate;
        this.methodAccess = methodAccess;
        this.methodName = methodName;
        this.methodIndex = methodAccess.getIndex(methodName);
    }

    /**
     * 执行对指定消息的处理
     *
     * @param channel 消息执行上下文
     * @param message 要处理的消息
     * @throws ExecutionException 消息处理器执行异常
     */
    @Override
    public void handle(Channel channel, T message) {
        throw new UnsupportedOperationException("该方法还没有提供具体的实现");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractMessageHandler<?> that = (AbstractMessageHandler<?>) o;
        return Objects.equal(delegate, that.delegate) &&
                Objects.equal(methodName, that.methodName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(delegate, methodName);
    }

    @Override
    public String toString() {
        return "AbstractMessageHandler{" +
                "delegate=" + delegate.getClass().getSimpleName() +
                ", method=" + methodName +
                '}';
    }
}
