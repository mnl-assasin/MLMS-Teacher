package com.jru.mlmsteacher.api.calls;

import com.jru.mlmsteacher.api.request.SignUpRequest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class SignUp extends BaseRequest {

    public static void execute(String header, final SignUpRequest request, final Callback callback) {
        callback.showLoadingDialog();
        Call<Void> call = getSecuredApi(header).signup(request);
        call.enqueue(new retrofit2.Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                callback.hideLoadingDialog();
                if (response.isSuccessful())
                    callback.isSuccessful();
                else {
                    try {
                        callback.onFailure(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.hideLoadingDialog();
                callback.onFailure(t.toString());
            }
        });
    }

    public interface Callback extends BaseListener {
        void isSuccessful();
    }
}
