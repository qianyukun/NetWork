package com.qisiemoji.network;

import android.app.Application;

import com.qisiemoji.network.http.RequestManager;

/**
 * 功能描述
 *
 * @author ywx
 * @date 2020-12-17
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RequestManager.init();
    }

}
