package com.example.rshc4u.appv3.api;

import com.example.rshc4u.appv3.data_model.DataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by linux64 on 10/1/16.
 */

public interface ApplicationConfig {

    @Headers("Accept: application/json")
    @GET("mobileFetch-app_layout/devid-")

    Call<ArrayList<DataModel>> getHomeData();


}
