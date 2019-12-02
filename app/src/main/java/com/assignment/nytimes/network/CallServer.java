package com.assignment.nytimes.network;


import androidx.annotation.NonNull;

import com.assignment.nytimes.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallServer {
    public static String serverError = "Server not responding. Please try again later.";
    private static CallServer instance;

    private ApiUtils utils;


    private CallServer() {
        buildRetrofitServices();
    }


    public static CallServer get() {
        if (instance == null) {
            synchronized (CallServer.class) {
                if (instance == null) {
                    instance = new CallServer();
                }
            }
        }
        return instance;
    }

    private void buildRetrofitServices() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient
                .Builder()
                .connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();


        this.utils = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiUtils.class);

    }

    @NonNull
    public ApiUtils getAPIName() {
        return utils;
    }
}
