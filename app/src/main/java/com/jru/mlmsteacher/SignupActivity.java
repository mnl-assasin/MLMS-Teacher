package com.jru.mlmsteacher;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;

public class SignupActivity extends AppCompatActivity {

   @BindView(R.id.etFirstName)
   AppCompatEditText mFirstNameEditText;
   @BindView(R.id.etLastName)
   AppCompatEditText mLastNameEditText;
   @BindView(R.id.etUsername)
   AppCompatEditText mUsernameEditText;
   @BindView(R.id.etBirthday)
   AppCompatEditText mBirthdayEditText;
   @BindView(R.id.etPassword)
   AppCompatEditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }
}
