package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieActivity extends AppCompatActivity implements MovieAdapter.IEmployeeCommunication {
    private MovieAdapter mov;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        List<Movie> myList=new ArrayList<>();

        OkHttpClient client=new OkHttpClient.Builder().build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.androidhive.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIController obj=retrofit.create(APIController.class);

        Call<List<Movie>> call=obj.getData();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Toast.makeText(MovieActivity.this,"Something went wrong...Please try later!",Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void generateDataList(List<Movie> movies){
        recyclerView=findViewById(R.id.recycler);
        mov=new MovieAdapter(movies, MovieActivity.this);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(MovieActivity.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mov);
    }

    @Override
    public void onClick(Movie movie) {
        Intent intent=new Intent(MovieActivity.this,Main4Activity.class);
        intent.putExtra("movie",movie);
        startActivity(intent);
    }
}
