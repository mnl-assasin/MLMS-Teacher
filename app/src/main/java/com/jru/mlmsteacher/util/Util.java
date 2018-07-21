package com.jru.mlmsteacher.util;

import android.content.Context;
import android.content.pm.PackageManager;

import com.jru.mlmsteacher.data.EZSharedPreferences;

public class Util {


    public static boolean wasAPKUpdated(Context ctx) {
        return EZSharedPreferences.getAPKLastUpdate(ctx) != getAPKLastUpdate(ctx);
    }

    public static long getAPKLastUpdate(Context ctx) {
        try {
            return ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
