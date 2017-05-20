/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.message;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Packet类型消息处理程序适配器
 *
 * @author gukaitong
 * @since 1.0
 */
public class PacketMessageHandlerAdapter extends AbstractMessageHandler<Packet> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PacketMessageHandlerAdapter.class);

    public PacketMessageHandlerAdapter(Object delegate, MethodAccess methodAccess, String methodName) {
        super(delegate, methodAccess, methodName);
    }

    @Override
    public void handle(Channel channel, Packet packet) {
        long t1 = System.currentTimeMillis();
        Object response = methodAccess.invoke(delegate, methodIndex, channel, packet);

        LOGGER.debug("处理请求#{}完成, 耗时{}毫秒", packet.getCmd(), System.currentTimeMillis() - t1);

        if (response != null) {
            LOGGER.debug("发送响应：{}", response);
            channel.writeAndFlush(response);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacketMessageHandlerAdapter that = (PacketMessageHandlerAdapter) o;
        return Objects.equal(delegate, that.delegate) &&
                Objects.equal(methodName, that.methodName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(delegate, methodName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("delegate", delegate)
                .add("method", methodName)
                .toString();
    }
}
