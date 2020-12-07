package com.example.androidtest.api;

import com.example.androidtest.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static volatile ApiClient sInstance;
    private Retrofit mRetrofit;

    public ApiClient() {
        createRetrofit();
    }

    public static ApiClient getInstance() {
        if (sInstance == null) {
            synchronized (ApiClient.class) {
                if (sInstance == null) {
                    return new ApiClient();
                }
            }
        }
        return sInstance;
    }

    private void createRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit getRetrofitInstance() {
        return mRetrofit;
    }
}
