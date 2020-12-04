package com.example.androidtest.models;

import com.example.androidtest.enums.AnswerType;

public class AnswerModel {

    private AnswerType mAnswerType;
    private String mAnswerMessage;

    public AnswerModel() {
    }

    public AnswerModel(AnswerType mAnswerType, String mAnswerMessage) {
        this.mAnswerType = mAnswerType;
        this.mAnswerMessage = mAnswerMessage;
    }

    public AnswerType getAnswerType() {
        return mAnswerType;
    }

    public void setAnswerType(AnswerType mAnswerType) {
        this.mAnswerType = mAnswerType;
    }

    public String getAnswerMessage() {
        return mAnswerMessage;
    }

    public void setAnswerMessage(String mAnswerMessage) {
        this.mAnswerMessage = mAnswerMessage;
    }

    @Override
    public String toString() {
        return  mAnswerType +"  " + mAnswerMessage;
    }
}
