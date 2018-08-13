package com.jru.mlmsteacher.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.jru.mlmsteacher.api.response.PersonalDetailsResponse;

/**
 * Created by mykelneds on 08/05/2018.
 */

public class EZSharedPreferences {

    private final static String USER_PREFERENCES = "MLMSTeacher";

    private final static String KEY_LOGIN = "isLogin";

    private final static String KEY_APK_LAST_UPDATE = "apkLastUpdate";
    private final static String KEY_ACCESS_TOKEN = "accessToken";

    private final static String KEY_USER_ID = "userId";
    private final static String KEY_USER_NAME = "userName";
    private final static String KEY_EMAIL = "userEmail";
    private final static String KEY_USER_TYPE = "userType";

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

    private static boolean getBoolean(Context ctx, String keyword) {
        return getSharedPref(ctx).getBoolean(keyword, false);
    }

    public static boolean isLogin(Context ctx) {
        return getBoolean(ctx, KEY_LOGIN);
    }

    public static String getAccessToken(Context ctx) {
        return getString(ctx, KEY_ACCESS_TOKEN);
    }

//    public static String get(Context ctx, String key) {
//        return getSharedPref(ctx).getString(key, "");
//    }

    /**
     * S E T T E R
     */

    public static void setAPKLastUpdate(Context ctx, long lastUpdate) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.putLong(KEY_APK_LAST_UPDATE, lastUpdate);
        editor.apply();
    }

    public static void setLogin(Context ctx, boolean isLogin) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.putBoolean(KEY_LOGIN, isLogin);
        editor.apply();
    }

    public static void setAccessToken(Context ctx, String accessToken) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.apply();
    }

    public static void setPersonalDetails(Context ctx, PersonalDetailsResponse details) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.putInt(KEY_USER_ID, details.getId());
        editor.putString(KEY_USER_NAME, details.getName());
        editor.putString(KEY_EMAIL, details.getEmail());
        editor.putString(KEY_USER_TYPE, details.getUserType());
        editor.putBoolean(KEY_LOGIN, true);
        editor.apply();


    }


}
