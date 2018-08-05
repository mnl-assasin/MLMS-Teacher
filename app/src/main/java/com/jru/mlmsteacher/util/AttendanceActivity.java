package com.jru.mlmsteacher.util;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jru.mlmsteacher.R;

import butterknife.ButterKnife;

public class AttendanceActivity extends AppCompatActivity {

    private static final String TAG = AttendanceActivity.class.getSimpleName();
    WifiApManager mWifiApManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        ButterKnife.bind(this);
        initialize();
    }

    private void initialize(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkIfCanWrite();
        }else {
            try {
                mWifiApManager = new WifiApManager(this);
                mWifiApManager.setWifiApState(mWifiApManager.getWifiApConfiguration(), true);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void checkIfCanWrite(){
        if(!Settings.System.canWrite(this)){
            Intent intent = new Intent(android.provider.Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + this.getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return;
        }

        try {
            mWifiApManager = new WifiApManager(this);
            mWifiApManager.setWifiApState(mWifiApManager.getWifiApConfiguration(), true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
