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
import com.example.user.pogo.BooleanResponse;
import com.example.user.pogo.GetOtp;
import com.example.user.pogo.VerifyOtp;

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
        final Button verifyotp=findViewById(R.id.verifyOtp);
        final EditText otpEditText=findViewById(R.id.OtpEditText);




        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetOtp getOtp=new GetOtp();
                getOtp.setEmail(email.getText().toString());

                APIInterface apiInterface= App.getClient().create(APIInterface.class);
                apiInterface.sendotp(getOtp).enqueue(new Callback<VerifyOtp>() {
                    @Override
                    public void onResponse(Call<VerifyOtp> call, Response<VerifyOtp> response) {
                        sendOtp.setVisibility(View.INVISIBLE);
                        otpEditText.setVisibility(View.VISIBLE);
                        verifyotp.setVisibility(View.VISIBLE);
                        otp=response.body().getOtp();


                        Toast.makeText(EmailVerificationActivity.this," "+otp+" ",Toast.LENGTH_SHORT);

                    }

                    @Override
                    public void onFailure(Call<VerifyOtp> call, Throwable t) {

                        Toast.makeText(EmailVerificationActivity.this,"server fail!!! ",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        verifyotp.setOnClickListener(new View.OnClickListener() {
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
