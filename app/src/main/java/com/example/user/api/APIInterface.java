package com.example.user.api;

import com.example.user.pogo.GetOtp;
import com.example.user.pogo.LoginResponse;
import com.example.user.pogo.UserDetails;
import com.example.user.pogo.BooleanResponse;
import com.example.user.pogo.LoginDetails;
import com.example.user.pogo.VerifyOtp;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @GET("https://api.myjson.com/bins/ygca2")
    Call<LoginResponse> login();

    @GET("https://api.myjson.com/bins/17n256")
    Call<UserDetails> userdetails();

    @GET("/google/login")
    Call<Boolean> glogin();

    @GET("/facebook/login")
    Call<Boolean> flogin();

    @GET("/sendotp")
    Call<BooleanResponse> sendotp(@Body GetOtp getOtp);

    @GET("/verifyotp")
    Call<BooleanResponse> verifyOtp(@Body VerifyOtp verifyOtp);

    @POST("/create")
    Call<Boolean> create(@Body LoginDetails loginDetails);

    @POST("/user/addProfile")
    Call<Response<Boolean>> createnewuser(@Body UserDetails userDetails);

}
