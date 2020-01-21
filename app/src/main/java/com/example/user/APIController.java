package com.example.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIController {
    @GET("json/glide.json")
    Call<List<Movie>> getData();
}
