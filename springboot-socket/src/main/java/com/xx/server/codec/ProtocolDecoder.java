package com.xx.server.codec;

import com.xx.server.message.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;

import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by gukt on 2016/7/30.
 *
 * @author gukt
 * @version 1.0
 */
public class ProtocolDecoder extends ByteToMessageDecoder {
    private Charset utf8 = Charset.forName("utf-8");

    /**
     * Protocol format: magic_num(2),21(1),gid(8),command(2),len(4),timestamp(4),data(len)
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 2) {
            return;
        }

        in.markReaderIndex();

        // Check to see if the specified magic num is valid
        byte byte1 = in.readByte();
        byte byte2 = in.readByte();
        if (byte1 != 0x59 || byte2 != 0x47) {
            throw new Exception("invalid message");
        }

        if (in.readableBytes() < 15) {
            in.resetReaderIndex();
            return;
        }

        in.readByte(); // skip version
        ByteBuf bufBoxId = in.readBytes(8);
        ByteBuf bufCmd = in.readBytes(2);
        int length = in.readInt();

        if (length < 0) {
            throw new CorruptedFrameException("negative length: " + length);
        }

        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        } else {
            String boxId = bufBoxId.toString(utf8);
            String cmd = bufCmd.toString(utf8);
            int timestamp = in.readInt();

            ByteBuf bufData = in.readBytes(length);
            String data = bufData.toString(utf8);

            out.add(new Packet(boxId, cmd, data,timestamp));
        }
    }
}
