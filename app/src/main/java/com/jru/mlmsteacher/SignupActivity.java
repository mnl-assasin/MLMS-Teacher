package com.jru.mlmsteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jru.mlmsteacher.api.calls.SignUp;
import com.jru.mlmsteacher.api.request.SignUpRequest;
import com.jru.mlmsteacher.data.EZSharedPreferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {

    @BindView(R.id.etName)
    TextInputEditText etName;
    @BindView(R.id.tilName)
    TextInputLayout tilName;
    @BindView(R.id.etEmail)
    TextInputEditText etEmail;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.containerCredentials)
    LinearLayout containerCredentials;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.tvSignIn)
    TextView tvSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSignUp, R.id.tvSignIn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                onSignUpClicked();
                break;
            case R.id.tvSignIn:
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }

    private void onSignUpClicked() {

        String name = etName.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();


        boolean isValid = true;

        if (name.equals("")) {
            isValid = false;
        }
        if (password.equals("")) {
            isValid = false;
        }
        if (email.equals("")) {
            isValid = false;
        }

        if (isValid) {
            String header = EZSharedPreferences.getAccessToken(this);
            SignUpRequest request = new SignUpRequest(name, email, password, "teacher");
            SignUp.execute(header, request, new SignUp.Callback() {
                @Override
                public void isSuccessful() {
                    Toast.makeText(SignupActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                    finish();
                }

                @Override
                public void onFailure(String errorMessage) {

                }

                @Override
                public void showLoadingDialog() {

                }

                @Override
                public void hideLoadingDialog() {

                }
            });
        }


    }
}
