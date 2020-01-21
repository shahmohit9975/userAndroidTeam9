package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.pogo.LoginResponse;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        Button proceedToPay =findViewById(R.id.proceedToPay);

        proceedToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("com.user.application", Context.MODE_PRIVATE);
                if(sharedPreferences!=null) {
                    if (!sharedPreferences.getString("email",null).isEmpty()){
                        Intent intent=new Intent(CartActivity.this, PaymentActivity.class);
                        startActivity(intent);
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
}
