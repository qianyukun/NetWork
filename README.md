# NetWork使用文档

本库基于rxjava + okhttp + retrofit

## 导入方式：
### 将Maven添加到构建文件中(项目根目录下build.gradle文件)
```
     maven {
            url 'http://nexus.kikaops.com:37409/repository/maven-public/'
            credentials {
                username 'read'
                password 'kika365'
            }
        }
       
```
### 添加依赖项
```
implementation "com.kika.network:network:1.0.0.1"

//logansquare-compiler 需在主项目中单独添加，否则会抛出转换异常
annotationProcessor 'com.bluelinelabs:logansquare-compiler:1.3.7'
```

## 初始化
```
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RequestManager.init();
    }

}

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
```
## 发起请求
```
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

    private void fetchKika() {
        RetrofitManager.getInstance().create(Api.class)
            .fetchKika()
            .compose(TransformUtils.<Sound>defaultSchedulers())
            .subscribe(new CustomObserver<Sound>() {
                @Override
                public void onError(ApiException e) {
                    // errorCode区分异常类型
                    int code = e.getCode();
                }

                @Override
                public void success(Sound sound) {
                    //服务端返回errorCode = 0会回调到success，TransformUtils.<Sound>defaultSchedulers()内部做了判断
                    if (sound != null) {

                    }
                }

                @Override
                public void onSubscribe(Disposable d) {
                    addDisposable(d);
                }
            });
    }
```

## 混淆
```
#LoganSquare
-keep class com.bluelinelabs.logansquare.** { *; }
-keep @com.bluelinelabs.logansquare.annotation.JsonObject class *
-keep class **$$JsonObjectMapper { *; }
-keep class com.github.aurae.retrofit2.LoganSquareConverterFactory { *; }
```
