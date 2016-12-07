package com.xx.server.util;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * Created by bean on 2016/8/31.
 */
public class DatasUtil {



    public static String stringTo(String data) {
        byte[] dataBytes=data.getBytes(Charset.forName("utf-8"));
        int dataLen = dataBytes.length;
        byte[] bytes = new byte[dataLen+4];
        ByteBuf buf = wrappedBuffer(bytes);
        buf.resetWriterIndex();
        buf.writeInt(dataLen);
        buf.writeBytes(dataBytes);
        data=buf.toString(Charset.forName("utf-8"));
        return data;
    }
    public static String intTo(int data) {
        byte[] bytes = new byte[4];
        ByteBuf buf = wrappedBuffer(bytes);
        buf.resetWriterIndex();
        buf.writeInt(data);
        return buf.toString(Charset.forName("utf-8"));
    }


//    public static List<Object> dataToList(String data) {
//        byte[] dataBytes=data.getBytes(Charset.forName("utf-8"));
//        List<Object> list=new ArrayList<Object>();
//
//        int dataLen = dataBytes.length;
//        byte[] bytes = new byte[dataLen];
//        ByteBuf buf = wrappedBuffer(bytes);
//        buf.resetWriterIndex();
//        buf.readInt();
//        buf.writeInt(data);
//        return list;
//    }

}
