package com.example.androidtest.models;

import com.example.androidtest.enums.ResponseType;

public class ResponseModel {

    private ResponseType mResponseType;
    private String mResponseMessage;

    public ResponseModel(ResponseType mResponseType, String mResponseMessage) {
        this.mResponseType = mResponseType;
        this.mResponseMessage = mResponseMessage;
    }

    public ResponseType getAnswerType() {
        return mResponseType;
    }

    public void setAnswerType(ResponseType mResponseType) {
        this.mResponseType = mResponseType;
    }

    public String getAnswerMessage() {
        return mResponseMessage;
    }

    public void setAnswerMessage(String mAnswerMessage) {
        this.mResponseMessage = mAnswerMessage;
    }

    @Override
    public String toString() {
        return  mResponseType +"  " + mResponseMessage;
    }
}
