package com.example.androidtest.api.services;

import com.example.androidtest.Constants;
import com.example.androidtest.models.NewsResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Constants.END_POINT)
    Observable<NewsResponseModel> getNewsResponseModel(@Query("q") String bitcoin,
                                                       @Query("from") String from,
                                                       @Query("sortBy") String publishedAt,
                                                       @Query("apiKey") String apiKey);
}
