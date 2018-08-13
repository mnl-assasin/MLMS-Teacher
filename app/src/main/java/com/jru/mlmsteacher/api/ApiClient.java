package com.jru.mlmsteacher.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit securedRetrofit = null;
    private static Retrofit retrofit = null;
    private static int TIME_OUT = 120;

    public static ApiInterface getApiInterface() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

    public static ApiInterface getSecuredApiInterface(String header) {
        return ApiClient.getSecuredClient(header).create(ApiInterface.class);
    }

    private static Retrofit getClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS);

        if (retrofit == null) {
            OkHttpClient client = httpClient.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiConfig.getURL())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }

        return retrofit;
    }

    private static Retrofit getSecuredClient(String header) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = getSecuredHttpClient("Authorization", header);

        if (securedRetrofit == null) {
            securedRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiConfig.getURL())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return securedRetrofit;

    }

    private static OkHttpClient getSecuredHttpClient(final String headerKey, final String headerValue) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        httpClient.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {

                return true;
            }
        });

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header(headerKey, headerValue);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });


        return httpClient.build();
    }

}
