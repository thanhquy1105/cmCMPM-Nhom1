package com.example.cnpm.model;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String BASE_URL = "https://powerful-harbor-05723.herokuapp.com/";
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder().build();
        if (retrofit ==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
