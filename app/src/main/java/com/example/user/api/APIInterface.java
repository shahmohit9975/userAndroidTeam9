package com.example.user.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIInterface {

    @GET("/login")
    Call<Boolean> login(String email,String password);

    @GET("/google/login")
    Call<Boolean> glogin();

    @GET("/facebook/login")
    Call<Boolean> flogin();

    @GET("/sendotp")
    Call<Boolean> sendotp(String email);

    @GET("/verifyotp")
    Call<Boolean> verifyOtp(String otp);

    @POST
    Call<Boolean> create(String email,String password);

}
