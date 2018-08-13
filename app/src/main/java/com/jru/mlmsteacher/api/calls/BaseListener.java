package com.jru.mlmsteacher.api.calls;

public interface BaseListener {
    void onFailure(String errorMessage);

    void showLoadingDialog();

    void hideLoadingDialog();
}
