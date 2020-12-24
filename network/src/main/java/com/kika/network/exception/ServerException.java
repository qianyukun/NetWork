/*
 * Copyright © 2014-2020 Kikatech.com, All Rights Reserved.
 */

package com.kika.network.exception;

/**
 *
 * @author ywx
 * @date 2020-12-17
 */
public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }


    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }
}
