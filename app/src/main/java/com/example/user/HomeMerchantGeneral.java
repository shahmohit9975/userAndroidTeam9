package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.Adaptor.CategoryAdaptor;
import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pogo.GetCategories;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeMerchantGeneral extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_merchant_general);

        layoutManager=new GridLayoutManager(this,2);
        recyclerView = (RecyclerView)findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(layoutManager);


        APIInterface apiInterface= App.getClient().create(APIInterface.class);
        apiInterface.getCategories().enqueue(new Callback<List<GetCategories>>() {
            @Override
            public void onResponse(Call<List<GetCategories>> call, Response<List<GetCategories>> response) {
                List<GetCategories> list=response.body();
                mAdapter=new CategoryAdaptor(list);
                recyclerView.setLayoutManager(layoutManager);
            }

            @Override
            public void onFailure(Call<List<GetCategories>> call, Throwable t) {

            }
        });


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(HomeMerchantGeneral.this,HomeMerchantGeneral.class));// do changes
                       overridePendingTransition(0,0);
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(HomeMerchantGeneral.this,CartActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.user_account:
                        startActivity(new Intent(HomeMerchantGeneral.this,SignInActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                        default:return false;
                }
            }
        });

        }

    }




