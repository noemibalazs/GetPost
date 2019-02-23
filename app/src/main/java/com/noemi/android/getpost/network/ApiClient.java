package com.noemi.android.getpost.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://reqres.in/api/";
    private static Retrofit sInstance = null;

    public static Retrofit getRetrofitInstance(){

        if (sInstance == null){
            sInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  sInstance;
    }
}
