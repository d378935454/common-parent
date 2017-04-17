package com.bean.springboot.utils;

import java.io.Serializable;


/**
 * Created by bean on 2016/6/15.
 */
public class RSTFulBody implements Serializable {
    private int code = 0;//成功1 失败0 session超时 3
    private Object data ;//反回json对象
    private String  msg= "";//返回备注信息

    /**
     * 成功
     */
    public RSTFulBody success() {
        this.code = 1;
        return this;
    }

    /**
     * 成功放入对象
     */
    public RSTFulBody success(Object data) {
        this.code = 1;
        this.data = data;
        return this;
    }

    /**
     * 失败
     */
    public RSTFulBody fail() {
        this.code = 0;
        return this;
    }
    /**
     * session超时
     * @param href  返回路劲
     */
    public RSTFulBody sessionTimeOut(String href) {
        this.code = 3;
        this.data=href;
        return this;
    }

    /**
     * 放入对象
     *
     * @param data
     */
    public RSTFulBody data(Object data) {
        this.data = data;
        return this;
    }

    /**
     * 放入消息
     *
     * @param msg
     */
    public RSTFulBody msg(String msg) {
        this.msg = msg;
        return this;
    }

    public RSTFulBody() {
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

