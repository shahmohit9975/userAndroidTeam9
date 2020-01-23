package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pojo.UserDetails;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);

        final EditText name=findViewById(R.id.nameEditText);
        final EditText password=findViewById(R.id.passwordEditText);
        final EditText email=findViewById(R.id.emailEditText);
        final EditText address=findViewById(R.id.addressEditText);
        final EditText imageurl=findViewById(R.id.imageTextView);

        Button create=findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface= App.getClient().create(APIInterface.class);

                UserDetails userDetails=new UserDetails();
                userDetails.setUserName(name.toString());
                userDetails.setUserEmail(email.toString());
                userDetails.setUserAddress(address.toString());
                userDetails.setUserPassword(password.toString());
                userDetails.setImage(imageurl.toString());

                apiInterface.createnewuser(userDetails).enqueue(new Callback<Response<Boolean>>() {
                    @Override
                    public void onResponse(Call<Response<Boolean>> call, Response<Response<Boolean>> response) {
                        Toast.makeText(UserCreateActivity.this,"done!!! ",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(UserCreateActivity.this,HomeMerchantGeneral.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Response<Boolean>> call, Throwable t) {

                    }
                });
            }
        });

    }
}
