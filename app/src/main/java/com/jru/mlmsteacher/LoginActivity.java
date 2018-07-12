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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etUsername)
    TextInputEditText etUsername;
    @BindView(R.id.tilUsername)
    TextInputLayout tilUsername;
    @BindView(R.id.etBirthday)
    TextInputEditText etBirthday;
    @BindView(R.id.tilBirthday)
    TextInputLayout tilBirthday;
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
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tvSignup:
                break;
        }
    }
}
