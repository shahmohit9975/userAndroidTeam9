package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pogo.PopularProducts;
import com.example.user.pogo.ProductReact;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

//        TextView id=findViewById(R.id.textView6);
//        TextView name=findViewById(R.id.textView7);
//        TextView pmid=findViewById(R.id.textView4);


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        String id=bundle.getString("pid");
        String name=bundle.getString("catName");
        String pmid=bundle.getString("pmid");

        ProductReact productReact=new ProductReact();
        productReact.setProductId(id);
        productReact.setCategoryName(name);
        productReact.setMerchantAndProductId(pmid);

        //get
        APIInterface apiInterface= App.getClient().create(APIInterface.class);
        apiInterface.getProductInfo(productReact).enqueue(new Callback<PopularProducts>() {
            @Override
            public void onResponse(Call<PopularProducts> call, Response<PopularProducts> response) {
                PopularProducts popularProducts=new PopularProducts();
                popularProducts=response.body();

                TextView name=findViewById(R.id.nameTextView);
                TextView desc=findViewById(R.id.descriptionTextView);
                TextView rating=findViewById(R.id.ratingTextView);
                TextView orginalPrice=findViewById(R.id.orginalPrice);
                TextView discountPrice=findViewById(R.id.discountPrice);
                ImageView firstImage=findViewById(R.id.firstImage);
                ImageView secoundImage=findViewById(R.id.secoundImage);
                ImageView thirdImage=findViewById(R.id.thirdImage);


                //setViews
                name.setText(popularProducts.getProductName());
                desc.setText(popularProducts.getDescription());
                //rating.setText(popularProducts.getProductRating());
                orginalPrice.setText(popularProducts.getPrice());
                discountPrice.setText(popularProducts.getSellingPrice());
                Glide.with(ProductActivity.this).load(popularProducts.getUrl1()).into(firstImage);
                Glide.with(ProductActivity.this).load(popularProducts.getUrl1()).into(secoundImage);
                Glide.with(ProductActivity.this).load(popularProducts.getUrl1()).into(thirdImage);

            }

            @Override
            public void onFailure(Call<PopularProducts> call, Throwable t) {

            }
        });

        // add or drop quantity;

        //addToCart

        //proceedToPay



        //bottom navg
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        startActivity(new Intent(ProductActivity.this,HomeMerchantGeneral.class));// do changes
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(ProductActivity.this,CartActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.user_account:
                        SharedPreferences sharedPreferences=getSharedPreferences("email access", Context.MODE_PRIVATE);
                        if(sharedPreferences.getBoolean("flag",false)){

                            startActivity(new Intent(ProductActivity.this,UserDetailsActivity.class));

                        }
                        else
                            startActivity(new Intent(ProductActivity.this,SignInActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    default:return false;
                }
            }
        });



    }
}
