package com.xx.server.emuns;

/**
 * Created by bean on 2016/8/30.
 */
public enum ProtocolVersion {
    Version("版本号",0x21),
    Demo("hehe",111);
    // 成员变量
    private String name;
    private int value;
    private ProtocolVersion(String name, int value) {
        this.name = name;
        this.value = value;
    }

    // 普通方法
    public static String getName(int value) {
        for (ProtocolVersion pv : ProtocolVersion.values()) {
            if (pv.getValue() == value) {
                return pv.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
