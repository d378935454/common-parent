package com.bean.socket;

import com.bean.service.VendingService;
import com.bean.socket.utils.SpringContextUtil;
import org.springframework.stereotype.Controller;
import utils.MyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by bean on 2016/6/20.
 */
@Controller
public class Server implements Runnable{
    /**
     * 日志记录
     **/
    private static final MyLogger LOGGER = new MyLogger(Server.class);
    private VendingService vendingService ;
    private ArrayList<ServerInfo> serverInfos=new ArrayList<ServerInfo>();
    private static Socket socket=null;
    private ServerSocket serverSocket=null;
    public static Socket getSocket() {
        return socket;
    }
    public void run() {
        System.out.println("this is the socket program ----zhangwenwen");

        try {
            vendingService= SpringContextUtil.getBean("vendingService");
            serverSocket=new ServerSocket(8191);
            while (true){
                System.out.println("做好准备");
                socket = serverSocket.accept();
                ServerInfo serverInfo=new ServerInfo(socket);
                new Thread(serverInfo).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class ServerInfo implements Runnable{


        private boolean isRunning=true;
        private Socket socket;
        public Socket getSocket() {
            return socket;
        }

        public void setSocket(Socket socket) {
            this.socket = socket;
        }
        public ServerInfo(Socket socket) {
            this.socket = socket;
        }
        public ServerInfo() {
        }

        public void run() {
            serverInfos.add(this);
            try {
            while (isRunning) {
                InputStream inputStream = this.socket.getInputStream();
                OutputStream outputStream = this.socket.getOutputStream();
                System.out.println("开始输入");
                Scanner in = new Scanner(inputStream);
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.write("Hello Enter BYE to exit!\n");
                printWriter.flush();
                boolean done = false;
                while (!done && in.hasNextLine()) {
                    String line = in.nextLine();
                    System.out.println(line);
                    printWriter.println("ECHO:" + line);
                    LOGGER.info("");
                    if (line.trim().equals("goods")) {
                        printWriter.println(":" + line);
                        vendingService.getOrderListByMap();
                    }
                    printWriter.flush();
                    if (line.trim().equals("BYE")) {
                        done = true;
                    }
                }
                in.close();
                inputStream.close();
                outputStream.close();
                isRunning=false;
                serverInfos.remove(this);
            }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
