package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Rating;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pojo.CartDetails;
import com.example.user.pojo.CartDetailsResponse;
import com.example.user.pojo.PopularProducts;
import com.example.user.pojo.ProductReact;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        //views

        final TextView name=findViewById(R.id.nameTextView);
        TextView desc=findViewById(R.id.descriptionTextView);
        RatingBar rating=findViewById(R.id.ratingTextView);
        TextView orginalPrice=findViewById(R.id.orginalPrice);
        TextView discountPrice=findViewById(R.id.discountPrice);
        ImageView firstImage=findViewById(R.id.firstImage);
        //ImageView secoundImage=findViewById(R.id.secoundImage);
        //ImageView thirdImage=findViewById(R.id.thirdImage);


        Intent intent=getIntent();
        final Bundle bundle=intent.getExtras();

        //important quantities
        final String productName=bundle.getString("name");
        final String pid=bundle.getString("pid");
        final String pmid=bundle.getString("pmid");
        final int cost=bundle.getInt("dprice");
        final String image=bundle.getString("img1");


        name.setText(bundle.getString("name"));
        desc.setText(bundle.getString("disc"));
        rating.setRating((float)bundle.getDouble("rating"));
        orginalPrice.setText(String.valueOf(bundle.getInt("price")));
        discountPrice.setText(String.valueOf(bundle.getInt("dprice")));
        Glide.with(ProductActivity.this).load(bundle.getString("img1")).into(firstImage);
        //Glide.with(ProductActivity.this).load(bundle.getString("img2")).into(secoundImage);
        //Glide.with(ProductActivity.this).load(bundle.getString("img3")).into(thirdImage);





//        String id=bundle.getString("pid");
//        String name=bundle.getString("catName");
//        String pmid=bundle.getString("pmid");

        //get
//        APIInterface apiInterface= App.getClient().create(APIInterface.class);
//        apiInterface.getProductInfo(productReact).enqueue(new Callback<PopularProducts>() {
//            @Override
//            public void onResponse(Call<PopularProducts> call, Response<PopularProducts> response) {
//                PopularProducts popularProducts;
//                popularProducts=response.body();
//
//                TextView name=findViewById(R.id.nameTextView);
//                TextView desc=findViewById(R.id.descriptionTextView);
//                TextView rating=findViewById(R.id.ratingTextView);
//                TextView orginalPrice=findViewById(R.id.orginalPrice);
//                TextView discountPrice=findViewById(R.id.discountPrice);
//                ImageView firstImage=findViewById(R.id.firstImage);
//                ImageView secoundImage=findViewById(R.id.secoundImage);
//                ImageView thirdImage=findViewById(R.id.thirdImage);
//
//
//                //setViews
//                name.setText(popularProducts.getProductName());
//                desc.setText(popularProducts.getDescription());
//                //rating.setText(popularProducts.getProductRating());
//                orginalPrice.setText(popularProducts.getPrice());
//                discountPrice.setText(popularProducts.getSellingPrice());
//                Glide.with(ProductActivity.this).load(popularProducts.getUrl1()).into(firstImage);
//                Glide.with(ProductActivity.this).load(popularProducts.getUrl1()).into(secoundImage);
//                Glide.with(ProductActivity.this).load(popularProducts.getUrl1()).into(thirdImage);
//
//            }
//
//            @Override
//            public void onFailure(Call<PopularProducts> call, Throwable t) {
//
//            }
//        });

        // add or drop quantity;
        ImageButton add=findViewById(R.id.addButton);
        ImageButton drop=findViewById(R.id.minusButton);
        final TextView quantity=findViewById(R.id.selectQuantity);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value=Integer.parseInt(quantity.getText().toString());

                if(value>=5){
                    Toast.makeText(ProductActivity.this,"Can't add more than 5 products",Toast.LENGTH_LONG).show();
                }
                else {
                    quantity.setText(String.valueOf(value+1));

                }

            }
        });

        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value=Integer.parseInt(quantity.getText().toString());

                if(value<=1){
                    Toast.makeText(ProductActivity.this,"Should have atleast one product",Toast.LENGTH_LONG).show();

                }
                else{
                    quantity.setText(String.valueOf(value-1));
                }

            }
        });

        //addToCart

        final CartDetails cartDetails=new CartDetails();
        cartDetails.setUserEmail("set Shared Preferences");//shared preferences
        cartDetails.setCartQuantity(Integer.parseInt(quantity.getText().toString()));
        cartDetails.setMerchantAndProductId(pmid);


        Button addToCart=findViewById(R.id.addToCartButton);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface=App.getClient().create(APIInterface.class);
                apiInterface.addToCart(cartDetails).enqueue(new Callback<CartDetailsResponse>() {
                    @Override
                    public void onResponse(Call<CartDetailsResponse> call, Response<CartDetailsResponse> response) {

                        if(response!=null){
                            Toast.makeText(ProductActivity.this,"sucess!!",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(ProductActivity.this,"fail!!",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<CartDetailsResponse> call, Throwable t) {

                        Toast.makeText(ProductActivity.this,"server error!!!",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });



        //proceedToPay
        //check quantity api not done
        Button proceedToPay=findViewById(R.id.proceedToPayButton);
        proceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int value=Integer.parseInt(quantity.getText().toString());

                Intent intent=new Intent(ProductActivity.this,FinishActivity.class);
                Bundle b=new Bundle();
                b.putString("name",productName);
                b.putString("pid",pid);
                b.putString("pmid",pmid);
                b.putInt("cost",cost);
                b.putInt("quantity",value);
                b.putString("image",image);
                intent.putExtras(b);
                startActivity(intent);

            }
        });



        //selectDifferntMerchant




        //bottom navg
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home1:
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
