package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.api.APIInterface;
import com.example.user.api.App;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        final EditText name=findViewById(R.id.nameEditText);
        final EditText password=findViewById(R.id.passwordEditText);
        final EditText email=findViewById(R.id.emailEditText);
        final EditText address=findViewById(R.id.addressEditText);

        Button create=findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface= App.getClient().create(APIInterface.class);

                UserDetails userDetails=new UserDetails();
                userDetails.setName(name.toString());
                userDetails.setEmail(email.toString());
                userDetails.setAddress(address.toString());
                userDetails.setPassword(password.toString());

                apiInterface.createnewuser(userDetails).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                        Toast.makeText(UserCreateActivity.this,"done!!! ",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(UserCreateActivity.this,HomeMerchantActivity.class);
                        startActivity(intent);


                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                        Toast.makeText(UserCreateActivity.this,"wrong password ",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}
