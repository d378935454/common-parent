/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.server;

public interface Server {
    String DEFAULT_LOCAL_ADDRESS = "127.0.0.1";

    void start();

    void stop();
}
