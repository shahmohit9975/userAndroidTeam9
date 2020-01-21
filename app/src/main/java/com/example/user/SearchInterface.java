package com.example.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchInterface {
    @GET("bins/7uude")
    Call<List<Books>> getBooks();

}
