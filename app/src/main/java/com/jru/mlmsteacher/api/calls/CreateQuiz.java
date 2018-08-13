package com.jru.mlmsteacher.api.calls;

import com.jru.mlmsteacher.api.request.QuizCreatorRequest;
import com.jru.mlmsteacher.api.response.QuizCreatorResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateQuiz extends BaseRequest {

    public static void request(String header, final QuizCreatorRequest request, final RequestListener listener) {
        listener.showLoadingDialog();
        Call<QuizCreatorResponse> call = getSecuredApi(header).createQuiz(request);
        call.enqueue(new Callback<QuizCreatorResponse>() {
            @Override
            public void onResponse(Call<QuizCreatorResponse> call, Response<QuizCreatorResponse> response) {
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
            public void onFailure(Call<QuizCreatorResponse> call, Throwable t) {
                listener.hideLoadingDialog();
                listener.onFailure(t.toString());
            }
        });

    }

    public interface RequestListener extends BaseListener {
        void isSuccessful(QuizCreatorResponse response);
    }
}
