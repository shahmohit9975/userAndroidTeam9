package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pogo.BooleanResponse;
import com.example.user.pogo.GetOtp;
import com.example.user.pogo.VerifyOtp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailVerificationActivity extends AppCompatActivity {

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
                getOtp.setEmail(email.toString());

                APIInterface apiInterface= App.getClient().create(APIInterface.class);
                apiInterface.sendotp(getOtp).enqueue(new Callback<BooleanResponse>() {
                    @Override
                    public void onResponse(Call<BooleanResponse> call, Response<BooleanResponse> response) {
                        if(response.body().getFlag().equals("true")){
                            sendOtp.setVisibility(View.INVISIBLE);
                            verifyotp.setVisibility(View.VISIBLE);
                            otpEditText.setVisibility(View.VISIBLE);

                        }
                    }

                    @Override
                    public void onFailure(Call<BooleanResponse> call, Throwable t) {

                    }
                });
            }
        });

        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //


            }
        });



    }
}
