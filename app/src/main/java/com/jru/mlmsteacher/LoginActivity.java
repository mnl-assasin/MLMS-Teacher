package com.jru.mlmsteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jru.mlmsteacher.api.calls.Login;
import com.jru.mlmsteacher.api.calls.PersonalDetails;
import com.jru.mlmsteacher.api.response.LoginResponse;
import com.jru.mlmsteacher.api.response.PersonalDetailsResponse;
import com.jru.mlmsteacher.components.BaseActivity;
import com.jru.mlmsteacher.data.EZSharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.etUsername)
    TextInputEditText etUsername;
    @BindView(R.id.tilUsername)
    TextInputLayout tilUsername;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.containerCredentials)
    LinearLayout containerCredentials;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.tvSignup)
    TextView tvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSignIn, R.id.tvSignup})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignIn:
                onSignInClicked();
                break;
            case R.id.tvSignup:
                startActivity(new Intent(this, SignupActivity.class));
                finish();
                break;
        }
    }

    private void onSignInClicked() {

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        Login.request(username, password, new Login.RequestListener() {
            @Override
            public void isSuccessful(LoginResponse response) {
                Log.d("TAG_", response.toString());
                loginSuccessful(response);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("TAG_", "onFailure: " + errorMessage);
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void showLoadingDialog() {
                // TODO: showLoadingDialog;
                showProgressDialog("Logging in...");
                Log.d("TAG_", "showLoadingDialog");
            }

            @Override
            public void hideLoadingDialog() {
                // TODO: hideLoadingDialog;
                hideProgressDialog();
                Log.d("TAG_", "hideLoadingDialog");
            }
        });
    }

    private void loginSuccessful(LoginResponse response) {
        String accessToken = "Bearer " + response.getAccessToken();
        EZSharedPreferences.setAccessToken(this, accessToken);

        PersonalDetails.request(accessToken, new PersonalDetails.RequestListener() {
            @Override
            public void isSuccessful(PersonalDetailsResponse response) {
                Log.d("TAG_", response.toString());
                getPersonalDetails(response);
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.d("TAG_", "onFailure: " + errorMessage);
            }

            @Override
            public void showLoadingDialog() {
                // TODO: showLoadingDialog;
                showProgressDialog("Getting user info...");
                Log.d("TAG_", "showLoadingDialog");
            }

            @Override
            public void hideLoadingDialog() {
                // TODO: hideLoadingDialog;
                hideProgressDialog();
                Log.d("TAG_", "hideLoadingDialog");
            }

        });

    }

    private void getPersonalDetails(PersonalDetailsResponse response) {

        String userType = response.getUserType();
        Log.d("TAG_", "userType: " + userType);
        if (!userType.equals("teacher"))
            Toast.makeText(this, "ACCESS DENIED THIS IS APP IS FOR TEACHERS ONLY", Toast.LENGTH_LONG).show();
        else {
            EZSharedPreferences.setPersonalDetails(this, response);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }
}
