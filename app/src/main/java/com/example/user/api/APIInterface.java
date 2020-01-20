package com.example.user.api;

import com.example.user.UserDetails;
import com.example.user.pogo.LoginDetails;
import com.example.user.pogo.VerifyOtp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIInterface {

    @GET("/login")
    Call<Boolean> login(@Body LoginDetails loginDetails);

    @GET("/google/login")
    Call<Boolean> glogin();

    @GET("/facebook/login")
    Call<Boolean> flogin();

    @GET("/sendotp")
    Call<Boolean> sendotp(@Body LoginDetails loginDetails);

    @GET("/verifyotp")
    Call<Boolean> verifyOtp(@Body VerifyOtp verifyOtp);

    @POST
    Call<Boolean> create(@Body LoginDetails loginDetails);

    @POST
    Call<Boolean> createnewuser(@Body UserDetails userDetails);

}
