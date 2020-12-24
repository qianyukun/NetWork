/*
 * Copyright © 2014-2020 Kikatech.com, All Rights Reserved.
 */

package com.kika.network.rx;

import com.kika.network.exception.ExceptionEngine;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 *
 * @author ywx
 * @date 2020-12-17
 */
public class HttpResultFunc<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) {
        return Observable.error(ExceptionEngine.handleException(throwable));
    }
}
