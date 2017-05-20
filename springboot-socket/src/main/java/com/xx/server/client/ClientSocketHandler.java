package com.xx.server.client;

import com.xx.server.message.Packet;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by gukt on 2016/7/30.
 *
 * @author gukt
 * @version 1.0
 * @date 2016/7/30
 */
@Sharable
public final class ClientSocketHandler extends SimpleChannelInboundHandler<Packet> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientSocketHandler.class);

    private BlockingQueue<Packet> queue = new LinkedBlockingQueue<>();

    public ClientSocketHandler(BlockingQueue<Packet> queue) {
        this.queue = queue;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        LOGGER.debug("收到响应：{}", msg);
//        ctx.channel().writeAndFlush(msg);
        queue.offer(msg);
    }
}
