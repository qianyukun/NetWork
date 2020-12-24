package com.qisiemoji.network.http;

import android.util.Log;

import com.kika.network.BuildConfig;
import com.kika.network.RetrofitManager;

import java.util.concurrent.TimeUnit;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.OkHttpClient;

/**
 * 功能描述
 *
 * @author ywx
 * @date 2020-12-24
 */
public class RequestManager {

    public static final String OFFICIAL = "official";
    public static final String OFFICIAL_URL = "https://api.pro.kikakeyboard.com/v1/";
    public static final String DEV_URL = "https://api-dev.kikakeyboard.com/v1/";

    public static final String TENOR = "tenor";
    public static final String TENOR_BASE_URL = "https://g.tenor.com/v1/";

    public static void init() {
        //初始化client
        RetrofitManager.initClient(getClient(), BuildConfig.DEBUG ? DEV_URL : OFFICIAL_URL);

        //设置多域名存入缓存
        RetrofitUrlManager.getInstance()
            .putDomain(OFFICIAL, BuildConfig.DEBUG ? DEV_URL : OFFICIAL_URL);
        RetrofitUrlManager.getInstance().putDomain(TENOR, TENOR_BASE_URL);
    }

    public static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(BuildConfig.DEBUG ? 30 : 15, TimeUnit.SECONDS)
            .followRedirects(true);

        builder = RetrofitUrlManager.getInstance().with(builder);

        HttpLoggingInterceptor httpLoggingInterceptor =
            new HttpLoggingInterceptor(message -> Log.e("Request", message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
        return builder.build();
    }
}
