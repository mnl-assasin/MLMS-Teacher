package com.jru.mlmsteacher.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mykelneds on 08/05/2018.
 */

public class EZSharedPreferences {

    private final static String USER_PREFERENCES = "MLMSTeacher";

    private final static String KEY_APK_LAST_UPDATE = "apkLastUpdate";
    private static SharedPreferences getSharedPref(Context ctx) {
        return ctx.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context ctx) {
        return getSharedPref(ctx).edit();
    }

    private static void dropSharedPref(Context ctx) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.clear();
        editor.apply();
    }

    /**
     * G E T T E R
     */

    private static long getLong(Context ctx, String keyword) {
        return getSharedPref(ctx).getLong(keyword, 0);
    }

    private static String getString(Context ctx, String keyword) {
        return getSharedPref(ctx).getString(keyword, "");
    }

    private static int getInt(Context ctx, String keyword) {
        return getSharedPref(ctx).getInt(keyword, 0);
    }

    public static long getAPKLastUpdate(Context ctx) {
        return getLong(ctx, KEY_APK_LAST_UPDATE);
    }

    public static String get(Context ctx, String key) {
        return getSharedPref(ctx).getString(key, "");
    }

    /**
     * S E T T E R
     */

    public static void setAPKLastUpdate(Context ctx, long lastUpdate) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.putLong(KEY_APK_LAST_UPDATE, lastUpdate);
        editor.apply();
    }


}
