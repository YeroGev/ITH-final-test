package com.example.androidtest.repository;

import com.example.androidtest.api.ApiClient;
import com.example.androidtest.api.services.ApiService;
import com.example.androidtest.models.NewsResponseModel;

import io.reactivex.Observable;

public class NewsRepositoryImpl implements NewsRepository{

    private ApiService mApiService;

    public NewsRepositoryImpl() {
        mApiService = ApiClient.getInstance().getRetrofitInstance().create(ApiService.class);
    }

    @Override
    public Observable<NewsResponseModel> getNewsResponseModel() {
        return mApiService.getNewsResponseModel("bitcoin","2020-12-02","publishedAt","2f1a594074e645b4b73e5d194a1541f7");
    }
}
