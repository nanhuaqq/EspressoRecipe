package com.xty.espressorecipe.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.xty.espressorecipe.BaseApplication;
import com.xty.espressorecipe.BuildConfig;
import com.xty.espressorecipe.common.C;
import com.xty.espressorecipe.common.Util;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/1/27 0027.
 */
public class  RetrofitSingleton {
    private static RetrofitSingleton INSTANCE;

    private  static Retrofit sRetrofit = null;
    private static OkHttpClient sOkHttpClient = null;
    private static DoubanMovieApiInterface sApiService = null;
    private RetrofitSingleton() {
        initOkHttp();
        initRetrofit();
        initService();
    }

    public static RetrofitSingleton getInstance(){
        if (INSTANCE == null){
            INSTANCE = new RetrofitSingleton();
        }
        return INSTANCE;
    }

    private void initOkHttp(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 缓存 http://www.jianshu.com/p/93153b34310e
        File cacheFile = new File(C.NET_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            if (!Util.isNetworkConnected(BaseApplication.getAppContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            Response.Builder newBuilder = response.newBuilder();
            if (Util.isNetworkConnected(BaseApplication.getAppContext())) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                newBuilder.header("Cache-Control", "public, max-age=" + maxAge);
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                newBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
            }
            return newBuilder.build();
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);
        if (BuildConfig.DEBUG){
            builder.addNetworkInterceptor(new StethoInterceptor());
        }
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        sOkHttpClient = builder.build();
    }

    private void initRetrofit(){
        sRetrofit = new Retrofit.Builder().baseUrl(DoubanMovieApiInterface.HOST)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private void initService(){
        sApiService = sRetrofit.create(DoubanMovieApiInterface.class);
    }

    public DoubanMovieApiInterface getDoubanMovieInterface(){
        return sApiService;
    }
}
