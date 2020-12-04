package com.example.androidtest.repository;

import com.example.androidtest.models.NewsResponseModel;

import io.reactivex.Observable;

public interface NewsRepository {

    Observable<NewsResponseModel> getNewsResponseModel();

}
