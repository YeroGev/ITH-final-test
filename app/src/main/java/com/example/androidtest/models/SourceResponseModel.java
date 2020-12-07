package com.example.androidtest.models;

import com.google.gson.annotations.SerializedName;

public class SourceResponseModel {

    @SerializedName("id")
    private Object id;

    @SerializedName("name")
    private String name;

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
