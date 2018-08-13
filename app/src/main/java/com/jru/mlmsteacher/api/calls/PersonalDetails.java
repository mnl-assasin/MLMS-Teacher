package com.jru.mlmsteacher.api.calls;

import com.jru.mlmsteacher.api.response.PersonalDetailsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalDetails extends BaseRequest {

    public static void request(String header, final RequestListener listener) {
        listener.showLoadingDialog();
        Call<PersonalDetailsResponse> call = getSecuredApi(header).personalDetails();
        call.enqueue(new Callback<PersonalDetailsResponse>() {
            @Override
            public void onResponse(Call<PersonalDetailsResponse> call, Response<PersonalDetailsResponse> response) {
                listener.hideLoadingDialog();
                if (response.isSuccessful())
                    listener.isSuccessful(response.body());
                else
                    listener.onFailure(response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<PersonalDetailsResponse> call, Throwable t) {
                listener.hideLoadingDialog();
                listener.onFailure(t.toString());
            }
        });
    }

    public interface RequestListener extends BaseListener {
        void isSuccessful(PersonalDetailsResponse response);
    }
}
