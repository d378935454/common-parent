/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.codec;

import com.xx.server.emuns.ProtocolVersion;
import com.xx.server.message.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * @author gukaitong
 * @since 1.0
 */
public class ProtocolEncoder extends MessageToMessageEncoder<Packet> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolEncoder.class);

    private Charset utf8 = Charset.forName("utf-8");

    /**
     * protocol: magic_num(2),21(1),gid(8),command(2),len(4),timestamp(4),data(len)
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        try {
            byte[] dataBytes = msg.getData().getBytes(utf8);
            int dataLen = dataBytes.length;

            int len = 21 + dataLen;
            byte[] bytes = new byte[len];

            ByteBuf buf = wrappedBuffer(bytes);
            buf.resetWriterIndex();

            buf.writeByte(0x59);
            buf.writeByte(0x47);
            buf.writeByte(ProtocolVersion.Version.getValue());
            buf.writeBytes(msg.getBoxId().getBytes(utf8));
            buf.writeBytes(msg.getCmd().getBytes(utf8));
            buf.writeInt(dataLen);
            buf.writeInt((int) (System.currentTimeMillis() / 1000));
            buf.writeBytes(dataBytes);

            out.add(buf);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("An exception occurred while encoding the message", e);
        }
    }
}