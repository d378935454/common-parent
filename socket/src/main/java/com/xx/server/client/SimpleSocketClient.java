/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.client;

import com.google.common.math.IntMath;
import com.xx.server.codec.ProtocolDecoder;
import com.xx.server.codec.ProtocolEncoder;
import com.xx.server.message.Packet;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.MyLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author gukaitong
 * @since 1.0
 */
public class SimpleSocketClient {
    private MyLogger LOGGER = new MyLogger(SimpleSocketClient.class);

    private String host;
    private int port;
    private Channel channel;
    private EventLoopGroup group;
    private boolean connected = false;
    private int retryCount = 0;
    private BlockingQueue<Packet> messages = new LinkedBlockingQueue<>();

    public SimpleSocketClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public SimpleSocketClient(int port) {
        this("localhost", port);
    }

    public SimpleSocketClient connect() {
        group = new NioEventLoopGroup();

        int maxRetryTimes = 10;
        do {
            LOGGER.info("Connecting to server [{0}:{1}] ...", host, port);
            try {
                Bootstrap b = new Bootstrap();
                b.group(group)
                        .channel(NioSocketChannel.class)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ChannelPipeline p = ch.pipeline();
                                p.addLast(new ProtocolDecoder());
                                p.addLast(new ProtocolEncoder());
                                p.addLast(new ClientSocketHandler(messages));
                            }
                        });
                this.channel = b.connect(host, port).sync().channel();

                this.setConnected();
//                // 控制台输入
//                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//                for (; ; ) {
//                    String line = in.readLine();
//                    if (line == null) {
//                        continue;
//                    }
//                             /*
//                  * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
//                  * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
//                  * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
//                  * */
//                    this.channel.writeAndFlush(line + "\r\n");
//                }
            } catch (Exception e) {
                LOGGER.error("{0}", e);
            }

            if (!connected) {
                try {
                    ++retryCount;
                    TimeUnit.SECONDS.sleep(Math.min(60, IntMath.pow(2, retryCount)));
                } catch (InterruptedException ignored) {
                }

                LOGGER.info("Trying to connect server again ... [第{0}次]", retryCount);
            }

        } while (!connected && retryCount <= maxRetryTimes);

        if (!connected) {
            LOGGER.info("Failed to connect，Please check your server or network！");
        }

        return this;
    }

    private void setConnected() {
        this.connected = true;
        retryCount = 0;
    }

    public Channel getChannel() {
        return channel;
    }

    public void disconnect() {
        group.shutdownGracefully();
        this.connected = false;
        retryCount = 0;
    }

    private void ensureConnective() {
        if (!connected) {
            this.connect(); // 发送数据时自动连接
        }
    }

//    public WriteFuture writeAsync(Packet message) {
//        this.ensureConnective();
//        channel.writeAndFlush(message);
//        return new WriteFuture(messages, message.getCmd());
//    }

    public Packet write(Packet message) {
        this.ensureConnective();
        channel.writeAndFlush(message);

        try {
            return messages.take();
        } catch (InterruptedException e) {
            LOGGER.error("发生了中断异常：", e);
            return null;
        }
    }

}
