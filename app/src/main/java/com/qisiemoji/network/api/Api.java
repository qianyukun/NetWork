package com.qisiemoji.network.api;


import com.kika.network.bean.Result;
import com.qisiemoji.network.bean.Sound;
import com.qisiemoji.network.http.RequestManager;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * 测试用，指定了两个不同的域名，可根据请求log过滤'Request'查看
 */
public interface Api {

    //通过header指定请求域名，替换域名原理是通过okttp Interceptor截取header信息进行替换
    @Headers({RetrofitUrlManager.DOMAIN_NAME_HEADER + RequestManager.OFFICIAL})
    @GET("sounds/tapSounds")
    Observable<Result<Sound>> fetchKika();


    @Headers({RetrofitUrlManager.DOMAIN_NAME_HEADER + RequestManager.TENOR})
    @GET("tenor/gifs")
    Observable<Result<Sound>> fetchTenor();

}
