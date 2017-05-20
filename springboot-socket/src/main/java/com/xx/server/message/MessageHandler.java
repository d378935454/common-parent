/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.message;

import com.xx.server.server.ExecutionException;
import io.netty.channel.Channel;

/**
 * @author gukaitong
 * @since 1.0
 */
public interface MessageHandler<T> {
    void handle(Channel channel, T message) throws ExecutionException;
}
