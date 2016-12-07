package com.bean.RSTFul;

import java.io.Serializable;


/**
 * Created by bean on 2016/6/15.
 */
public class RSTFulBody implements Serializable {
    private int code = 0;//成功1 失败0
    private Object body = null;//反回json对象
    private String data = null;//返回备注信息

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
    public RSTFulBody success(Object body) {
        this.code = 1;
        this.body = body;
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
     * 放入对象
     *
     * @param body
     */
    public RSTFulBody body(Object body) {
        this.body = body;
        return this;
    }

    /**
     * 放入对象
     *
     * @param data
     */
    public RSTFulBody data(String data) {
        this.data = data;
        return this;
    }

    public RSTFulBody() {
    }

    public int getCode() {
        return code;
    }

    public Object getBody() {
        return body;
    }

    public String getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

