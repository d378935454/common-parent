/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.util;

import com.xx.server.message.Packet;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 处理错误的工具类以及错误代码的常量定义
 */
public class Errors {
    private static final Logger LOGGER = LoggerFactory.getLogger(Errors.class);

    /**
     * 服务器内部错误
     */
    public static final int SERVER_INTERNAL_ERROR = 1;

    /**
     * 服务器还未准备好
     */
    public static final int SERVER_IS_NOT_READY = 2;

    public static void sendError(Channel channel, int errCode) {
        // Get boxId property from attribute
        String boxId = (String) channel.attr(AttributeKey.valueOf("boxId")).get();

        sendError(channel, boxId, errCode, null);
    }

    public static void sendError(Channel channel, String boxId, int errCode) {
        sendError(channel, boxId, errCode, "");
    }

    public static void sendError(Channel channel, String boxId, int errCode, String errMsg) {
        // Build response packet
        Packet packet = new Packet(boxId, "00", String.format("errCode:%s, errMsg:%s", errCode, errMsg));

        // Write response to client
        channel.writeAndFlush(packet);
    }
}
