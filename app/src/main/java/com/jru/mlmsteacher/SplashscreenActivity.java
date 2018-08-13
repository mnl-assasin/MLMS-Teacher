package com.jru.mlmsteacher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.jru.mlmsteacher.api.ApiConfig;
import com.jru.mlmsteacher.data.EZSharedPreferences;

public class SplashscreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

//        startActivity(new Intent(this, QuizCreatorActivity.class));
//        finish();
        configAPI();
    }

    private void configAPI() {

        final String appSecret = "I4IUb07pyO3HCui6UEXfA2kZTI7l4uPKOunSdYri";

        final EditText editText = new EditText(this);
//        editText.setText("http://");
        editText.setText("https://2ee640b0.ngrok.io/");
        editText.setTextColor(getResources().getColor(R.color.textColor));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter server address");
        builder.setView(editText);
        builder.setCancelable(false);
        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String baseURL = editText.getText().toString();
                ApiConfig.setConfig(baseURL, appSecret);

//                startActivity(new Intent(getApplicationContext(), TestActivity.class));

                if (EZSharedPreferences.isLogin(SplashscreenActivity.this))
                    startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
                else
                    startActivity(new Intent(SplashscreenActivity.this, LoginActivity.class));

                finish();
            }
        }).show();

    }
}
