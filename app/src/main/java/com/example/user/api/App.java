package com.example.user.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App {

    static Retrofit retrofit;

    public static Retrofit getClient(){
        OkHttpClient client = new OkHttpClient.Builder().build();

        retrofit=new Retrofit.Builder()
                .baseUrl("https://api.androidhive.info")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }
}
