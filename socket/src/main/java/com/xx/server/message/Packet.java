package com.xx.server.message;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

/**
 * Created by gukt on 2016/7/30.
 *
 * @author gukt
 * @version 1.0
 */
public class Packet {
    private String boxId;
    private String cmd;
    private String data;
    private int timestamp;

    public Packet(String boxId, String cmd, String data) {
        this(boxId, cmd, data, (int) (System.currentTimeMillis() / 1000));
    }

    public Packet(String boxId, String cmd, String data, int timestamp) {
        this.boxId = Strings.padEnd(boxId, 8, '0');
        this.cmd = cmd;
        this.data = data;
        this.timestamp = timestamp;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("boxId", boxId)
                .add("cmd", cmd)
                .add("data", data)
                .add("timestamp", timestamp)
                .toString();
    }
}
