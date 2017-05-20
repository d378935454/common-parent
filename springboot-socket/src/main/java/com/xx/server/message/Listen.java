/*
 * Copyright 2002-2016 XianYu Game Co. Ltd, The Inuyasha Project
 */

package com.xx.server.message;

import java.lang.annotation.*;

/**
 * @author gukaitong
 * @since 1.0
 */
@Inherited
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Listen {
    String value();
}