/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.server;

public class ExecutionException extends RuntimeException {
    private int errCode;

    public int getErrCode() {
        return errCode;
    }

    public ExecutionException(int errCode) {
        this(errCode, errCode + "(" + errCode + ")");
    }

    public ExecutionException(int errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public ExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutionException(Throwable cause) {
        super(cause);
    }

    public ExecutionException() {
        super();
    }


}
