package com.xx.server;

import com.xx.server.client.SimpleSocketClient;
import com.xx.server.message.Packet;
import com.xx.server.util.DatasUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * Created by bean on 2016/8/25.
 */
public class SmartClient {
//    public static String host = "120.24.78.240";
    public static String host = "127.0.0.1";
    public static int port = 8191;

    /**
     * 19      * @param args
     * 20      * @throws InterruptedException
     * 21      * @throws IOException
     * 22
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        SimpleSocketClient simpleSocketClient = new SimpleSocketClient(host, port);
        simpleSocketClient.connect();
        String boxId = "00000000";
        String cmd = "03";
        String data =DatasUtil.intTo(0)
                +DatasUtil.stringTo("97855640")
                +DatasUtil.stringTo("07")
                ;


        int timestam = (int) new Date().getTime();
        Packet packet = new Packet(boxId, cmd, data, timestam);

        simpleSocketClient.write(packet);

    }
}
