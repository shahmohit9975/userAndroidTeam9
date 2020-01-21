package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeMerchantGeneral extends AppCompatActivity {
   private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_merchant_general);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(HomeMerchantGeneral.this,SearchActivity.class));
                       overridePendingTransition(0,0);
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(HomeMerchantGeneral.this,CartActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.user_account:
                        startActivity(new Intent(HomeMerchantGeneral.this,AccountActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                        default:return false;
                }
            }
        });

        }

    }




//
//    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//@Override
//public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch(item.getItemId()){
//        case R.id.search:
//        startActivity(new Intent(HomeMerchantGeneral.this,SearchActivity.class));
//        overridePendingTransition(0,0);
//        return true;
//
//        case R.id.cart:
//        startActivity(new Intent(HomeMerchantGeneral.this,CartActivity.class));
//        overridePendingTransition(0,0);
//        return true;
//
//        case R.id.user_account:
//        startActivity(new Intent(HomeMerchantGeneral.this,AccountActivity.class));
//        overridePendingTransition(0,0);
//        return true;
//        }
//        return false; });


