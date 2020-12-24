/*
 * Copyright © 2014-2020 Kikatech.com, All Rights Reserved.
 */

package com.kika.network.rx;

import com.kika.network.bean.Result;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 异常处理和数据转换
 *
 * @author ywx
 * @date 2020-12-17
 */
public class TransformUtils {

    public static <T> ObservableTransformer<Result<T>, T> defaultSchedulers() {
        return resultObservable -> resultObservable.map(new ServerResultFunc<T>()).
            onErrorResumeNext(new HttpResultFunc<T>()).
            unsubscribeOn(Schedulers.io())
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
