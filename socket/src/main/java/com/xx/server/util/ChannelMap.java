package com.xx.server.util;

import io.netty.channel.Channel;


/**
 * Created by bean on 2016/9/6.
 */
public class ChannelMap {
    private String boxId;
    private Channel channel;

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public ChannelMap(String boxId, Channel channel) {
        this.boxId = boxId;
        this.channel = channel;
    }
}
