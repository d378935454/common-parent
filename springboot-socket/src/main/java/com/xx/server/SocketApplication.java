package com.xx.server;

import com.xx.server.message.PacketMessageDispatcher;
import com.xx.server.server.SocketServer;
import com.xx.server.server.SocketServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by duhongda on 2017/5/20.
 */
@SpringBootApplication(scanBasePackages = "com")
public class SocketApplication {
    private static final Logger log = LoggerFactory.getLogger(SocketApplication.class);

    @Value("${wss.server.host}")
    private String host;

    @Value("${wss.server.port}")
    private Integer port;

    @Bean(initMethod = "start",destroyMethod = "stop")
    public SocketServer socketIOServer(SocketServerHandler socketServerHandler,PacketMessageDispatcher packetMessageDispatcher) {
        SocketServer socketServer = new SocketServer(port);
        socketServer.setHandler(socketServerHandler);
        socketServer.setMessageDispatcher(packetMessageDispatcher);
        socketServer.setKeepAliveTimeout(60);
        return socketServer;
    }


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SocketApplication.class);
//		DefaultProfileUtil.addDefaultProfile(app);
        //关闭web功能
        app.setWebEnvironment(false);
        Environment env = app.run(args).getEnvironment();
        try {
            log.info("\n----------------------------------------------------------\n\t" + "Application '{}' is running! Access URLs:\n\t" + "Local: \t\thttp://localhost:{}\n\t" + "External: \thttp://{}:{}\n----------------------------------------------------------", env.getProperty("spring.application.name"), env.getProperty("wss.server.port"), InetAddress.getLocalHost().getHostAddress(), env.getProperty("wss.server.port"));
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }

        String configServerStatus = env.getProperty("configserver.status");
        log.info("\n----------------------------------------------------------\n\t" + "Config Server: \t{}\n----------------------------------------------------------", configServerStatus == null ? "Not found or not setup for this application" : configServerStatus);
    }
}
