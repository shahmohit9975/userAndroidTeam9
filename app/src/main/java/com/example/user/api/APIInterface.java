package com.example.user.api;

import android.content.SharedPreferences;

import com.example.user.pojo.CartCall;
import com.example.user.pojo.CartChangeReact;
import com.example.user.pojo.CartChangeResponse;
import com.example.user.pojo.CartDeleteReact;
import com.example.user.pojo.CartDeleteResponse;
import com.example.user.pojo.CartDetails;
import com.example.user.pojo.CartDetailsResponse;
import com.example.user.pojo.CartResponse;
import com.example.user.pojo.GetCategories;
import com.example.user.pojo.GetOtp;
import com.example.user.pojo.LoginRes;
import com.example.user.pojo.PopularProducts;
import com.example.user.pojo.ProductReact;
import com.example.user.pojo.UserDetails;
import com.example.user.pojo.BooleanResponse;
import com.example.user.pojo.LoginDetails;
import com.example.user.pojo.VerifyOtp;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIInterface {




    @POST("/user/login")
    Call<LoginRes> login(@Body LoginDetails loginDetails);

    @GET("/")
    Call<UserDetails> userdetails();

    @GET("/google/login")
    Call<Boolean> glogin();

    @GET("/facebook/login")
    Call<Boolean> flogin();

    @POST("/otp/get")
    Call<VerifyOtp> sendotp(@Header("dnamic-header") String header,@Body GetOtp getOtp);

    @GET("/verifyotp")
    Call<BooleanResponse> verifyOtp(@Body VerifyOtp verifyOtp);

    @POST("/create")
    Call<Boolean> create(@Body LoginDetails loginDetails);

    @POST("/user/addProfile")
    Call<Response<Boolean>> createnewuser(@Body UserDetails userDetails);

    //@GET("/bins/1eg5iu")
    @GET("/category/getAll")
    Call<List<GetCategories>> getCategories();

    //@GET("/bins/11b47a")
    @GET("/category/getAllProductsByRating")
    Call<List<PopularProducts>> getPopular();

    //@GET("/bins/11b47a")
    @GET("/category/getAllProductsByRating")
    Call<List<PopularProducts>> getCategoryProducts();

    @POST("/merchantAndProduct/get/product")
    Call<PopularProducts> getProductInfo(ProductReact productReact);

    @PUT("/cart/add")
    Call<CartDetailsResponse> addToCart(CartDetails cartDetails);

    @GET("/cart/get")
    Call<List<CartResponse>> getCart(CartCall cartCall);

    @PUT("/cart/add")
    Call<CartChangeResponse> changeCart(CartChangeReact cartChangeReact);

    @DELETE("/cart/delete")
    Call<CartDeleteResponse> deleteCart(CartDeleteReact cartDeleteReact);

}
