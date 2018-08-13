package com.jru.mlmsteacher.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jru.mlmsteacher.api.model.QuizCreatorDataResponse;

public class QuizCreatorResponse {

    @SerializedName("data")
    @Expose
    private QuizCreatorDataResponse data;
    @SerializedName("message")
    @Expose
    private String message;

    public QuizCreatorDataResponse getQuizCreatorDataResponse() {
        return data;
    }

    public void setQuizCreatorDataResponse(QuizCreatorDataResponse data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}