package com.jru.mlmsteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

//        new NodeJSServer(this).run();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashscreenActivity.this, QuizActivity.class));
////                startActivity(new Intent(SplashscreenActivity.this, TestActivity.class));
//            }
//        }, 5000);
        startActivity(new Intent(SplashscreenActivity.this, LoginActivity.class));
    }
}
