package com.example.user.api;

import com.example.user.pogo.GetCategories;
import com.example.user.pogo.GetOtp;
import com.example.user.pogo.LoginRes;
import com.example.user.pogo.LoginResponse;
import com.example.user.pogo.UserDetails;
import com.example.user.pogo.BooleanResponse;
import com.example.user.pogo.LoginDetails;
import com.example.user.pogo.VerifyOtp;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("/user/login")
    Call<LoginRes> login(@Body LoginDetails loginDetails);

    @GET("https://api.myjson.com/bins/17n256")
    Call<UserDetails> userdetails();

    @GET("/google/login")
    Call<Boolean> glogin();

    @GET("/facebook/login")
    Call<Boolean> flogin();

    @POST("/otp/get")
    Call<VerifyOtp> sendotp(@Body GetOtp getOtp);

    @GET("/verifyotp")
    Call<BooleanResponse> verifyOtp(@Body VerifyOtp verifyOtp);

    @POST("/create")
    Call<Boolean> create(@Body LoginDetails loginDetails);

    @POST("/user/addProfile")
    Call<Response<Boolean>> createnewuser(@Body UserDetails userDetails);

    @GET("/category/getAll")
    Call<List<GetCategories>> getCategories();

}
