package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.Adaptor.CategoryAdaptor;
import com.example.user.Adaptor.PopularAdaptor;
import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pojo.GetCategories;
import com.example.user.pojo.PopularProducts;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeMerchantGeneral extends AppCompatActivity implements CategoryAdaptor.CategoryCommunication, PopularAdaptor.PopularCommunication {

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.LayoutManager layoutManager2;


    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_merchant_general);

        layoutManager=new LinearLayoutManager(this);
        layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView = findViewById(R.id.recycler1);
        recyclerView2=findViewById(R.id.recycler2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);


        APIInterface apiInterface= App.getClient().create(APIInterface.class);

        //get categories call
        apiInterface.getCategories().enqueue(new Callback<List<GetCategories>>() {
            @Override
            public void onResponse(Call<List<GetCategories>> call, Response<List<GetCategories>> response) {

                Toast.makeText(HomeMerchantGeneral.this,"server connected",Toast.LENGTH_SHORT).show();
                List<GetCategories> list=response.body();
                mAdapter=new CategoryAdaptor(list,HomeMerchantGeneral.this);
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<GetCategories>> call, Throwable t) {

                Toast.makeText(HomeMerchantGeneral.this,"server error",Toast.LENGTH_SHORT).show();

            }
        });

        //popular products call

        apiInterface.getPopular().enqueue(new Callback<List<PopularProducts>>() {
            @Override
            public void onResponse(Call<List<PopularProducts>> call, Response<List<PopularProducts>> response) {

                Toast.makeText(HomeMerchantGeneral.this,"server connected",Toast.LENGTH_SHORT).show();
                List<PopularProducts> popularProducts=response.body();
                mAdapter=new PopularAdaptor(popularProducts,HomeMerchantGeneral.this);
                recyclerView2.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<List<PopularProducts>> call, Throwable t) {

                Toast.makeText(HomeMerchantGeneral.this,"server error",Toast.LENGTH_SHORT).show();

            }
        });

        //bottom navg
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
                        SharedPreferences sharedPreferences=getSharedPreferences("emailAccess", Context.MODE_PRIVATE);
                        if(sharedPreferences.getBoolean("flag",false)){

                            Log.d("dasd", "onNavigationItemSelected: ");

                            startActivity(new Intent(HomeMerchantGeneral.this,UserDetailsActivity.class));

                        }
                        else {
                            startActivity(new Intent(HomeMerchantGeneral.this, SignInActivity.class));
                            Log.d("dasdas", "secound: ");
                        }
                        overridePendingTransition(0,0);
                        return true;

                        default:return false;
                }
            }
        });

        }

    @Override
    public void onClick(GetCategories getCategories) {
        Intent intent =new Intent(HomeMerchantGeneral.this,CategoryDisplayActivity.class);
        Bundle b=new Bundle();
        b.putString("id",getCategories.getId());
        b.putString("name",getCategories.getCategoryName());
        intent.putExtras(b);
        startActivity(intent);

    }

    @Override
    public void onClick(PopularProducts popularProducts) {
        Intent intent=new Intent(HomeMerchantGeneral.this,ProductActivity.class);
        Bundle b=new Bundle();
        b.putString("catName",popularProducts.getCategoryName());
        b.putString("pmid",popularProducts.getMerchantAndProductId());
        b.putString("pid",popularProducts.getProductId());
        b.putString("disc",popularProducts.getDescription());
        b.putString("img1",popularProducts.getUrl1());
        b.putString("img2",popularProducts.getUrl2());
        b.putString("img3",popularProducts.getUrl3());
        b.putInt("price",popularProducts.getPrice());
        b.putInt("dprice",popularProducts.getSellingPrice());
        b.putDouble("rating",popularProducts.getProductRating());
        b.putString("name",popularProducts.getProductName());
        intent.putExtras(b);
        startActivity(intent);
    }
}




