/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.server;

import com.xx.server.codec.ProtocolDecoder;
import com.xx.server.codec.ProtocolEncoder;
import com.xx.server.message.Packet;
import com.xx.server.message.PacketMessageDispatcher;
import com.xx.server.util.DatasUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SocketServer
 */
public class SocketServer implements Server {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketServer.class);
    private static AtomicInteger nextId = new AtomicInteger(1);
    private PacketMessageDispatcher messageDispatcher;
    private final String host;
    private final int port;
    private ServerBootstrap serverBootStrap = new ServerBootstrap();
    //    public static ChannelGroup allChannels =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static Map<String, Channel> keyMap = new HashMap<>();

    public SocketServer(int port) {
        this(DEFAULT_LOCAL_ADDRESS, port);
    }

    public SocketServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private int keepAliveTimeout;

    public void setMessageDispatcher(PacketMessageDispatcher messageDispatcher) {
        this.messageDispatcher = messageDispatcher;
    }

    /**
     * 设置心跳超时时间（秒）
     * 超过该最大时间未收客户端发来的心跳包，则认为客户端已经掉线
     *
     * @param timeoutSeconds 超时时间,单位：秒
     */
    public void setKeepAliveTimeout(int timeoutSeconds) {
        this.keepAliveTimeout = timeoutSeconds;
    }

    private ChannelHandler handler;

    public void setHandler(ChannelHandler handler) {
        this.handler = handler;
    }

    @Override
    public void start() {
        new Thread(() -> {
            LOGGER.info("Socket服务正在启动 ...");
            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
            EventLoopGroup workerGroup = new NioEventLoopGroup(0);

            try {
                serverBootStrap.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .option(ChannelOption.TCP_NODELAY, true)
                        .handler(new LoggingHandler(LogLevel.INFO))
                        .childHandler(new SocketServerChannelInitializer());

                ChannelFuture future = serverBootStrap.bind(port).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (future.isSuccess()) {
                            LOGGER.info("Socket服务启动成功, 正在监听：{}", port);
                        }
                    }
                });

                // 保持一直等到Channel关闭时该线程才会退出
                future.sync().channel().closeFuture().sync();
            } catch (InterruptedException e) {
                LOGGER.error("*** Socket服务线程异常中断! ***");
            } catch (Exception e) {
                LOGGER.error("Socket服务器启动发生异常：", e);
                e.printStackTrace();
            } finally {
                SocketServer.this.stop();
            }
        }, "Socket-Server-Bootstrap-" + nextId.getAndIncrement()).start();
        //启动控制台
        new Thread(() -> {
            Charset utf8 = Charset.forName("utf-8");
            try {
                /* 控制台输入 */
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                while (true ) {
                    String line = null;
                    System.out.println("list:显示所有连接的云柜");
                    System.out.println("do:开始准备操作云柜");
                    line = in.readLine();

                    if (line == null) {
                        continue;
                    }
                    //业务处理
                    line = line.trim();
                    switch (line) {
                        case "list":
                            for (Map.Entry<String, Channel> entry : keyMap.entrySet()) {
                                System.out.println(MessageFormat.format("boxId:({0})and value =({1}) ", entry.getKey(), entry.getValue().toString()) + "");
                            }
                            break;
                        case "do":
                            for (; ; ) {
                                System.out.println("输入云柜号（退出输入'exit'）");
                                String boxId = in.readLine().trim();
                                Channel channel = keyMap.get(boxId);
                                if ("exit".equals(boxId.trim())) {
                                    //退出
                                    break;
                                } else if (channel != null) {
                                    System.out.println("输入指令（退出输入'exit'）");
                                    for (; ; ) {
                                        line = in.readLine().trim();//输入指令
                                        if (!"exit".equals(line)) {
                                            String data = "";
                                            String cmd = line;
                                            switch (line) {
                                                /**
                                                 * 描述： 设置副柜温控阀值
                                                 data：副柜温控阀值（int，单位为摄氏度，实际有效数据长度为2个字节，高字节表示启动压缩机的温度阀值
                                                 ，低字节表示关闭压缩机的温度阀值，均为带符号的数值）
                                                 */
                                                case "29":
                                                    System.out.println("输入最高温度");
                                                    for (; ; ) {
                                                        line = in.readLine().trim();//输入指令
                                                        if (Validation.isInteger(line)) {
                                                            break;
                                                        } else {
                                                            System.out.println("输入请输入数字");
                                                        }
                                                    }
//                                                        byte[] byt=new byte[2];
//                                                        byt[0]=(byte)Integer.valueOf(line).intValue();

//                                                        data += DatasUtil.intTo(Integer.valueOf(line));
                                                    data += (char) (byte) Integer.valueOf(line).intValue();
                                                    System.out.println("输入最低温度");
                                                    for (; ; ) {
                                                        line = in.readLine().trim();//输入指令
                                                        if (Validation.isInteger(line)) {
                                                            break;
                                                        } else {
                                                            System.out.println("输入请输入数字");
                                                        }
                                                    }
//                                                        data += DatasUtil.intTo(Integer.valueOf(line));
//                                                        byt[1]=(byte)Integer.valueOf(line).intValue() ;
//                                                        data=new String(byt,utf8);
                                                    data += (char) (byte) Integer.valueOf(line).intValue();
                                                    break;
                                                /**
                                                 * 描述：远程开门
                                                 * data： 柜门号（string）
                                                 * 开门后门的柜门的状态变化（int  0：状态不变 ；1：开门后
                                                 */
                                                case "10":
                                                    System.out.println("输入柜门号");
                                                    line = in.readLine().trim();//输入指令
                                                    data += DatasUtil.stringTo(line);
                                                    System.out.println("输入状态变化 int 0：状态不变 ；1：开门后");
                                                    line = in.readLine().trim();//输入指令
                                                    data += DatasUtil.intTo(Integer.valueOf(line));
                                                    break;
                                            }
                                            channel.writeAndFlush(new Packet(boxId, cmd, data));
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("没有此柜，请重新输入云柜号");
                                }

                            }
                            break;

                    }

                         /*
              * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
              * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
              * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
              * */
//                    this.channel.writeAndFlush(line + "\r\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void stop() {
        LOGGER.info("Socket服务即将关闭 ...");
        serverBootStrap.group().shutdownGracefully();
        serverBootStrap.childGroup().shutdownGracefully();
    }

    class SocketServerChannelInitializer extends ChannelInitializer {
        @Override
        protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline p = ch.pipeline();

            // 协议编码/解码器
            p.addLast(new ProtocolDecoder());
            p.addLast(new ProtocolEncoder());

            // 心跳处理
            p.addLast(new IdleStateHandler(keepAliveTimeout, 0, 0));

            //自己的逻辑
            p.addLast(handler);
        }
    }
}
