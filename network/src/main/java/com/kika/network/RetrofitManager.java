/*
 * Copyright © 2014-2020 Kikatech.com, All Rights Reserved.
 */

package com.kika.network;

import android.text.TextUtils;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author ywx
 * @date 2020-12-17
 */
public class RetrofitManager {

    private volatile static RetrofitManager instance = null;
    private Retrofit retrofit;
    private Map<String, Object> apiCache;

    private RetrofitManager(OkHttpClient okHttpClient, String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)) {
            throw new RuntimeException("baseUrl为空");
        }
        OkHttpClient client;
        if (okHttpClient == null) {
            client = getOkhttpClient();
        } else {
            client = okHttpClient;
        }
        retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(LoganSquareConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
        apiCache = new HashMap<>();
    }

    public static RetrofitManager initClient(OkHttpClient client, String baseUrl) {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager(client, baseUrl);
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> apiSourceClass) {
        String className = apiSourceClass.getName();
        Object api = apiCache.get(className);
        if (api != null) {
            return (T) api;
        }
        try {
            api = retrofit.create(apiSourceClass);
            apiCache.put(className, api);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) api;
    }

    public static RetrofitManager getInstance() {
        return initClient(null, null);
    }

    private static OkHttpClient getOkhttpClient() {
        return RetrofitUrlManager.getInstance()
            .with(new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)).build();
    }

}

