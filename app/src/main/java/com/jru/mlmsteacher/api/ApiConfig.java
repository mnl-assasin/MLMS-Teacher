package com.jru.mlmsteacher.api;

public class ApiConfig {

    private static String BASE_URL = "";
    private static String APP_SECRET = "";

    public static void setConfig(String URL, String secret) {
        setURL(URL);
        setSecret(secret);
    }

    public static void setSecret(String secret) {
        APP_SECRET = secret;
    }

    public static String getSecret() {
        return APP_SECRET;
    }

    public static void setURL(String url) {
//        BASE_URL = url + ":8000/";
        BASE_URL = url;
    }

    public static String getURL() {
        return BASE_URL;
    }
}
