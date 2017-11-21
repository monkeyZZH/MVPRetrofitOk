package com.wpz.mymvpframe.api;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wpz.mymvpframe.application.Content;
import com.wpz.mymvpframe.application.MyApplication;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wpz on 2017/5/17.
 * <p>
 * 将Retrofit封装起来，返回Api
 */

public class RetrofitApi {
    public static final String PREF_COOKIES="PREF_COOKIES";

    static class RetrofitInstance {
        private static SharedPrefsCookiePersistor prefsCookiePersistor=new SharedPrefsCookiePersistor(MyApplication.getInstance());
        private static OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .cookieJar(new PersistentCookieJar(new SetCookieCache(), prefsCookiePersistor))
                .build();

        private static Api api = new Retrofit.Builder()
                .baseUrl(Content.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
    }

    //得到Server对象
    public static Api getServer() {
        return RetrofitInstance.api;
    }


    public static List<Cookie> getCookies(){
       return RetrofitInstance.prefsCookiePersistor.loadAll();
    }
    public static void clear(){
        RetrofitInstance.prefsCookiePersistor.clear();
    }
}
