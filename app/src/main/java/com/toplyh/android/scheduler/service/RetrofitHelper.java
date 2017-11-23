package com.toplyh.android.scheduler.service;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 我 on 2017/11/15.
 */

public class RetrofitHelper {

    private Context mContext;

    OkHttpClient client=new OkHttpClient();

    GsonConverterFactory factory=GsonConverterFactory.create(new GsonBuilder().create());

    private static RetrofitHelper instance=null;

    private Retrofit mRetrofit=null;

    public static RetrofitHelper getInstance(Context context){
        if (instance==null){
            instance=new RetrofitHelper(context);
        }
        return instance;
    }

    private RetrofitHelper(Context context){
        mContext=context;
        init();
    }

    private void init(){
        resetApp();
    }

    private void resetApp(){
        mRetrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.137.1:8081/api/")
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

    }

    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }
}
