/*
 * Copyright © 2014-2020 Kikatech.com, All Rights Reserved.
 */

package com.kika.network.exception;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

/**
 * @author ywx
 * @date 2020-12-17
 */
public class ExceptionEngine {

    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 协议出错
     */
    public static final int HTTP_ERROR = 1001;
    /**
     * 无网络
     */
    public static final int NO_NET_ERROR = 1002;
    /**
     * 请求超时
     */
    public static final int TIME_OUT_ERROR = 1003;
    /**
     * 请求取消
     */
    public static final int REQUEST_INTERRUPTED_ERROR = 1003;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException || e instanceof ConnectException) {
            ex = new ApiException(e, HTTP_ERROR);
            ex.setDisplayMessage(e.getMessage());
            return ex;
        }
        if (e instanceof ServerException) {
            ServerException resultException = (ServerException) e;
            ex = new ApiException(resultException, resultException.getCode());
            ex.setDisplayMessage(resultException.getMsg());
            return ex;
        }
        if (e instanceof SocketException ||
            e instanceof UnknownHostException) {
            ex = new ApiException(e, NO_NET_ERROR);
            ex.setDisplayMessage(e.getMessage());
            return ex;
        }
        if (e instanceof SocketTimeoutException) {
            ex = new ApiException(e, TIME_OUT_ERROR);
            ex.setDisplayMessage(e.getMessage());
            return ex;
        }
        if (e instanceof InterruptedException) {
            ex = new ApiException(e, REQUEST_INTERRUPTED_ERROR);
            ex.setDisplayMessage(e.getMessage());
            return ex;
        }
        if (e instanceof ApiException) {
            return (ApiException) e;
        }
        ex = new ApiException(e, UNKNOWN);
        ex.setDisplayMessage(e.getMessage());
        return ex;
    }
}
