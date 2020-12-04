package com.example.androidtest.models;

import com.example.androidtest.models.ArticleResponseModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsResponseModel {

    @SerializedName("status")
    private String status;

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<ArticleResponseModel> articles = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleResponseModel> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleResponseModel> articles) {
        this.articles = articles;
    }
}
