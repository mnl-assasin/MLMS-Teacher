package com.jru.mlmsteacher.api.calls;

import android.util.Log;

import com.jru.mlmsteacher.api.ApiConfig;
import com.jru.mlmsteacher.api.request.LoginRequest;
import com.jru.mlmsteacher.api.response.LoginResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends BaseRequest {

    public static void request(String username, String password, final RequestListener listener) {
        listener.showLoadingDialog();
        final LoginRequest request = new LoginRequest("password", 2, ApiConfig.getSecret(), username, password, "");
        Call<LoginResponse> call = getApi().login(request);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
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
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                listener.hideLoadingDialog();
                listener.onFailure(t.toString());
            }
        });

        Log.d("TAG_", call.request().url().toString());
    }

    public interface RequestListener extends BaseListener {
        void isSuccessful(LoginResponse response);
    }
}
