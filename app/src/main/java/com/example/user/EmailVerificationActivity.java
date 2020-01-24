package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.api.APIInterface;
import com.example.user.api.App3;
import com.example.user.pojo.GetOtp;
import com.example.user.pojo.VerifyOtp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailVerificationActivity extends AppCompatActivity {

    String otp=new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification);

        final EditText email=findViewById(R.id.emailEditText);
        final Button sendOtp=findViewById(R.id.otpButtton);
        final Button verifyOtp=findViewById(R.id.verifyOtp);
        final EditText otpEditText=findViewById(R.id.OtpEditText);




        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetOtp getOtp=new GetOtp();
                getOtp.setEmail(email.getText().toString());
//                sendOtp.setVisibility(View.INVISIBLE);
//                otpEditText.setVisibility(View.VISIBLE);
//                verifyotp.setVisibility(View.VISIBLE);

                APIInterface apiInterface= App3.getClient().create(APIInterface.class);
                SharedPreferences sharedPreferences=getSharedPreferences("emailAccess", Context.MODE_PRIVATE);
                apiInterface.sendotp(sharedPreferences.getString("email",null),getOtp).enqueue(new Callback<VerifyOtp>() {
                    @Override
                    public void onResponse(Call<VerifyOtp> call, Response<VerifyOtp> response) {
                        sendOtp.setVisibility(View.INVISIBLE);
                        otpEditText.setVisibility(View.VISIBLE);
                        verifyOtp.setVisibility(View.VISIBLE);
                        if (response.body() == null) throw new AssertionError();
                        otp=response.body().getOtp();


                        Toast.makeText(EmailVerificationActivity.this," "+otp+" ",Toast.LENGTH_LONG);

                    }

                    @Override
                    public void onFailure(Call<VerifyOtp> call, Throwable t) {

                        Toast.makeText(EmailVerificationActivity.this,"server fail!!! ",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(otpEditText.getText().toString().equals(otp)){
                    Intent intent=new Intent(EmailVerificationActivity.this,UserCreateActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });



    }
}
