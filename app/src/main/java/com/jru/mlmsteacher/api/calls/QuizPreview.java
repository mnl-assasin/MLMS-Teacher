package com.jru.mlmsteacher.api.calls;

import com.jru.mlmsteacher.api.response.QuizPreviewResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizPreview extends BaseRequest {

    public static void request(String header, final RequestListener listener) {
        listener.showLoadingDialog();
        Call<QuizPreviewResponse> call = getSecuredApi(header).quizPreview();
        call.enqueue(new Callback<QuizPreviewResponse>() {
            @Override
            public void onResponse(Call<QuizPreviewResponse> call, Response<QuizPreviewResponse> response) {
                listener.hideLoadingDialog();
                if (response.isSuccessful())
                    listener.isSuccessful(response.body());
                else {
                    try {
                        listener.onFailure(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<QuizPreviewResponse> call, Throwable t) {
                listener.hideLoadingDialog();
                listener.onFailure(t.toString());
            }
        });

    }

    public interface RequestListener extends BaseListener {
        void isSuccessful(QuizPreviewResponse response);
    }
}
