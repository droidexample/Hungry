package com.example.rshc4u.appv3.api;

import com.example.rshc4u.appv3.utils.URLParams;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by linux64 on 10/1/16.
 */

public class AppClient implements URLParams {
    private static Retrofit getRetrofitInstance() {


        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_API)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApplicationConfig getApiService() {
        return getRetrofitInstance().create(ApplicationConfig.class);
    }
}
