package com.jru.mlmsteacher;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        Dexter.withActivity(this).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        new NodeJSServer(SplashscreenActivity.this).run();
                        startActivity(new Intent(SplashscreenActivity.this, QuizCreatorActivity.class));
                        SplashscreenActivity.this.finish();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

//        new NodeJSServer(this).run();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashscreenActivity.this, QuizActivity.class));
////                startActivity(new Intent(SplashscreenActivity.this, TestActivity.class));
//            }
//        }, 5000);
<<<<<<< HEAD
//        startActivity(new Intent(SplashscreenActivity.this, QuizCreatorActivity.class));
=======
        startActivity(new Intent(SplashscreenActivity.this, LoginActivity.class));
>>>>>>> master-forked
    }
}
