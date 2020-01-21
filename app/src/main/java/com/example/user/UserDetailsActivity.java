package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pogo.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        final TextView name=findViewById(R.id.nameTextView);
        final TextView email=findViewById(R.id.emailTextView);
        final TextView address=findViewById(R.id.addressTextView);



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
    }
}
