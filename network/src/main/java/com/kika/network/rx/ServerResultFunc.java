/*
 * Copyright © 2014-2020 Kikatech.com, All Rights Reserved.
 */

package com.kika.network.rx;

import com.kika.network.bean.Result;
import com.kika.network.exception.ServerException;

import io.reactivex.functions.Function;

/**
 * server返回结果处理
 *
 * @author ywx
 * @date 2020-12-17
 */
class ServerResultFunc<T> implements Function<Result<T>, T> {
    @Override
    public T apply(Result<T> httpResult) throws Exception {
        //服务端0为成功
        if (httpResult.getCode() == 0) {
            return httpResult.getData();
        }
        throw new ServerException(httpResult.getCode(), httpResult.getMessage());
    }
}
