/*
 * Copyright © 2014-2020 Kikatech.com, All Rights Reserved.
 */

package com.kika.network.exception;

/**
 * 功能描述
 *
 * @author ywx
 * @date 2020-12-17
 */
public class ApiException extends Exception {
    private final Throwable throwable;
    private int code;
    private String displayMessage;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.throwable = throwable;
        this.code = code;

    }

    public Throwable getThrowable() {
        return throwable;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public int getCode() {
        return code;
    }
}
