package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pojo.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        final TextView name=findViewById(R.id.nameTextView);
        final TextView email=findViewById(R.id.emailTextView);
        final TextView address=findViewById(R.id.addressTextView);
        final Button signout=findViewById(R.id.logoutButton);



        APIInterface apiInterface= App.getClient().create(APIInterface.class);

        apiInterface.userdetails().enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                UserDetails userDetails=response.body();
                name.setText(userDetails.getUserName().toString());
                email.setText(userDetails.getUserEmail().toString());
                address.setText(userDetails.getUserAddress().toString());


            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {

            }
        });

        //logout button

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences=getSharedPreferences("email access", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putBoolean("flag",true);
                editor.commit();
                editor.apply();
                Intent intent=new Intent(UserDetailsActivity.this,HomeMerchantGeneral.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
