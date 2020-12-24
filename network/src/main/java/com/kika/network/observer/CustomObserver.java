/*
 * Copyright © 2014-2020 Kikatech.com, All Rights Reserved.
 */

package com.kika.network.observer;

import com.kika.network.exception.ApiException;

import io.reactivex.Observer;

/**
 *
 * @author ywx
 * @date 2020-12-24
 */
public abstract class CustomObserver<T> implements Observer<T> {

    @Override
    public void onNext(T t) {
        success(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        }
    }

    @Override
    public void onComplete() {

    }

    public abstract void onError(ApiException e);

    public abstract void success(T t);
}
