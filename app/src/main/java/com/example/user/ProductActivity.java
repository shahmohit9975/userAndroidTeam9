package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pogo.PopularProducts;
import com.example.user.pogo.ProductReact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {

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

        APIInterface apiInterface= App.getClient().create(APIInterface.class);
        apiInterface.getProductInfo(productReact).enqueue(new Callback<PopularProducts>() {
            @Override
            public void onResponse(Call<PopularProducts> call, Response<PopularProducts> response) {

            }

            @Override
            public void onFailure(Call<PopularProducts> call, Throwable t) {

            }
        });



    }
}
