package com.jru.mlmsteacher.api;

import com.jru.mlmsteacher.api.request.LoginRequest;
import com.jru.mlmsteacher.api.request.QuizCreatorRequest;
import com.jru.mlmsteacher.api.response.LoginResponse;
import com.jru.mlmsteacher.api.response.PersonalDetailsResponse;
import com.jru.mlmsteacher.api.response.QuizCreatorResponse;
import com.jru.mlmsteacher.api.response.QuizPreviewResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/oauth/token")
    Call<LoginResponse> login(@Body LoginRequest request);

    @GET("api/me")
    Call<PersonalDetailsResponse> personalDetails();

    @POST("/api/teacher/quizzes")
    Call<QuizCreatorResponse> createQuiz(@Body QuizCreatorRequest request);

    @GET("/api/teacher/quizzes")
    Call<QuizPreviewResponse> quizPreview();
}
