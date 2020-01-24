package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.Adaptor.CartAdaptor;
import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.api.App3;
import com.example.user.pojo.CartCall;
import com.example.user.pojo.CartChangeReact;
import com.example.user.pojo.CartChangeResponse;
import com.example.user.pojo.CartDeleteReact;
import com.example.user.pojo.CartDeleteResponse;
import com.example.user.pojo.CartResponse;
import com.example.user.pojo.PopularProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements CartAdaptor.CartApiCall {

    RecyclerView recyclerView;
    RecyclerView.Adapter mAdatptor;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        Button proceedToPay =findViewById(R.id.proceedToPay);
        //get cart details

        SharedPreferences sharedPreferences=getSharedPreferences("emailAccess",Context.MODE_PRIVATE);
        CartCall cartCall=new CartCall();
        cartCall.setUserEmail(sharedPreferences.getString("email",null));

        recyclerView=findViewById(R.id.cartRecycler);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        APIInterface apiInterface=App.getClient().create(APIInterface.class);
        apiInterface.getCart(cartCall).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {
                List<CartResponse> list=response.body();
                mAdatptor=new CartAdaptor(list,CartActivity.this);
                recyclerView.setAdapter(mAdatptor);
                Toast.makeText(CartActivity.this,"connected!!",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {

                Toast.makeText(CartActivity.this,"server error!!",Toast.LENGTH_SHORT).show();

            }
        });



        //proceed to pay api implementation not done!!!
        proceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("com.user.application", Context.MODE_PRIVATE);
                if(sharedPreferences!=null) {
                    if (!sharedPreferences.getString("email",null).isEmpty()){
                        Intent intent=new Intent(CartActivity.this, FinishActivity.class);
                        startActivity(intent);//add bundle
                        finish();
                    }
                    else{
                        Intent intent=new Intent(CartActivity.this,SignInActivity.class);
                        startActivity(intent);
                    }

                }
                else{
                    Intent intent=new Intent(CartActivity.this,SignInActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    //add function api call
    @Override
    public void onAdd(CartResponse cartResponse) {

        CartChangeReact cartChangeReact=new CartChangeReact();
        cartChangeReact.setMerchantAndProductId(cartResponse.getMerchantAndProductId());
        SharedPreferences sharedPreferences=getSharedPreferences("emailAccess",Context.MODE_PRIVATE);
        cartChangeReact.setUserEmail(sharedPreferences.getString("email",null));
        cartChangeReact.setCartQuantity(cartResponse.getCartQuantity()+1);

        APIInterface apiInterface= App3.getClient().create(APIInterface.class);
        apiInterface.changeCart(cartChangeReact).enqueue(new Callback<CartChangeResponse>() {
            @Override
            public void onResponse(Call<CartChangeResponse> call, Response<CartChangeResponse> response) {
                Toast.makeText(CartActivity.this,"success!!!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CartChangeResponse> call, Throwable t) {
                Toast.makeText(CartActivity.this,"server error!!",Toast.LENGTH_SHORT).show();

            }
        });

    }
    // minus function api call
    @Override
    public void onMinus(CartResponse cartResponse) {

        CartChangeReact cartChangeReact=new CartChangeReact();
        cartChangeReact.setMerchantAndProductId(cartResponse.getMerchantAndProductId());
        SharedPreferences sharedPreferences=getSharedPreferences("emailAccess",Context.MODE_PRIVATE);
        cartChangeReact.setUserEmail(sharedPreferences.getString("email",null));
        cartChangeReact.setCartQuantity(cartResponse.getCartQuantity()-1);

        APIInterface apiInterface= App3.getClient().create(APIInterface.class);
        apiInterface.changeCart(cartChangeReact).enqueue(new Callback<CartChangeResponse>() {
            @Override
            public void onResponse(Call<CartChangeResponse> call, Response<CartChangeResponse> response) {
                Toast.makeText(CartActivity.this,"success!!!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CartChangeResponse> call, Throwable t) {
                Toast.makeText(CartActivity.this,"server error!!",Toast.LENGTH_SHORT).show();

            }
        });


    }
    // remove function api call
    @Override
    public void onRemove(CartResponse cartResponse) {

        CartDeleteReact cartDeleteReact=new CartDeleteReact();
        cartDeleteReact.setCartId(cartResponse.getCartId());

        APIInterface apiInterface=App3.getClient().create(APIInterface.class);
        apiInterface.deleteCart(cartDeleteReact).enqueue(new Callback<CartDeleteResponse>() {
            @Override
            public void onResponse(Call<CartDeleteResponse> call, Response<CartDeleteResponse> response) {
                if(response.body().isStatus()){
                    Toast.makeText(CartActivity.this,"sucess!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CartActivity.this,"fail!!",Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CartDeleteResponse> call, Throwable t) {
                Toast.makeText(CartActivity.this,"server error!!",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
