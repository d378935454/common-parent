/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */
package com.xx.server.server;

import com.xx.server.message.Packet;
import com.xx.server.message.PacketMessageDispatcher;
import com.xx.server.util.Errors;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static com.google.common.base.Preconditions.checkNotNull;

@Sharable
public class  SocketServerHandler extends SimpleChannelInboundHandler<Packet> {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketServerHandler.class);
    private ThreadPoolExecutor executor;

    private PacketMessageDispatcher messageDispatcher;

    private int corePoolSize = 5;
    private int maxPoolSize = 50;
    private long threadKeepAliveTime = 60L;
    private int workQueueSize = 100;


    public void setMessageDispatcher(PacketMessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    /**
     * 设置线程池保持不销毁的线程数
     *
     * @param corePoolSize 线程数
     */
    @SuppressWarnings("unused")
    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    /**
     * 设置线程池最大线程数
     *
     * @param maxPoolSize 最大线程数
     */
    @SuppressWarnings("unused")
    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    /**
     * 设置线程池线程存活时间（秒）
     *
     * @param threadKeepAliveTime 线程池线程存活时间（秒）
     */
    @SuppressWarnings("unused")
    public void setThreadKeepAliveTime(long threadKeepAliveTime) {
        this.threadKeepAliveTime = threadKeepAliveTime;
    }

    /**
     * 设置线程池队列大小
     *
     * @param workQueueSize 线程池队列大小
     */
    @SuppressWarnings("unused")
    public void setWorkQueueSize(int workQueueSize) {
        this.workQueueSize = workQueueSize;
    }

    @PostConstruct
    public void init() {
        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                threadKeepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(workQueueSize),
                new ThreadFactory() {
                    private AtomicLong nextId = new AtomicLong(0);

                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(checkNotNull(r), "MessageHandler-" + nextId.incrementAndGet());
                    }
                });
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        // 服务器未就绪，一律拒绝客户端的连接请求
        if (!Application.getInstance().isAvailable()) {
            // 告诉客户端服务器还没准备好，然后立即关闭此连接
            Packet packet = new Packet(null, "00", "服务器还未准备好");
            ctx.channel().writeAndFlush(packet).addListener(ChannelFutureListener.CLOSE);
        } else {
            super.channelRegistered(ctx);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("客户端连接成功，channelActive: {}", ctx.channel());
//        SocketServer.allChannels.add(ctx.channel());
        super.channelActive(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, final Packet packet) throws Exception {
        LOGGER.debug("收到请求: {}", packet);
        // TODO 处理心跳请求

        try {
            final Channel channel = ctx.channel();

            executor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        messageDispatcher.dispatch(channel, packet);
                    } catch (Exception e) {
                        LOGGER.error("消息处理时发生未捕获的异常: {}", e);
                        Errors.sendError(channel, packet.getBoxId(), Errors.SERVER_INTERNAL_ERROR);
                    }
                }
            });
        } catch (Exception e) {
            LOGGER.error("提交任务发生异常：", e);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {

        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.debug("exceptionCaught: {}", ctx.channel());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        LOGGER.debug("客户端连接断开，channelInactive：{}", ctx.channel());
        for (Map.Entry<String, Channel> entry :SocketServer.keyMap.entrySet()){
            if(entry.getValue()==ctx.channel()){
                SocketServer.keyMap.remove(entry.getKey());
                break;
            }
        }
        this.onChannelClosing(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            if (e.state() == IdleState.READER_IDLE) {
                LOGGER.info("读通道空闲中，正在关闭连接[{}] ...", ctx.channel());
                ctx.close();
            } else if (e.state() == IdleState.WRITER_IDLE) {
                LOGGER.info("写通道空闲中");
            } else if (e.state() == IdleState.ALL_IDLE) {
                LOGGER.info("读写通道空闲中，正在关闭连接[{}]", ctx.channel());
                ctx.close();
            }
        }

        super.userEventTriggered(ctx, evt);
    }

    private void onChannelClosing(ChannelHandlerContext ctx) {
//        try {
//            Session session = ctx.attr(SESSION_KEY).get();
//            if (session != null) {
//                Role role = session.getAttribute(Constants.ATTR_ROLE);
//
//                if (role != null) {
//                    // 设置退出时间
//                    role.setLogoutTime(new Date());
//
//                    // 同步触发SessionClosing事件
//                    eventPublisher.fireAsyncEvent(new SessionClosingEvent(role));
//                }
//
//                session.close();
//            }
//        } catch (Exception e) {
//            LOGGER.error("ChannelClosing处理异常：", e);
//        }
    }
}
