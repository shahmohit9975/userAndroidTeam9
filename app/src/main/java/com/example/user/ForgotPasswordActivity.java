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
import com.example.user.pogo.LoginDetails;
import com.example.user.pogo.VerifyOtp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final EditText email=findViewById(R.id.emailEditText);
        final EditText password=findViewById(R.id.passwordEditText);
        final EditText repassword=findViewById(R.id.repasswordEditText);
        final TextView passwordText=findViewById(R.id.passwordTextView);
        final TextView repasswordText=findViewById(R.id.repasswordTextView);
        final EditText otp=findViewById(R.id.OtpEditText);
        final Button otpButton=findViewById(R.id.otpButtton);
        final Button verButton=findViewById(R.id.verifyOtp);
        final Button create=findViewById(R.id.createButton);

        otpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                APIInterface apiInterface= App.getClient().create(APIInterface.class);
                LoginDetails loginDetails=new LoginDetails();
                loginDetails.setEmail(email.toString());
                apiInterface.sendotp(loginDetails).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.body()) {
                            Toast.makeText(ForgotPasswordActivity.this, "server call success", Toast.LENGTH_LONG).show();
                            otpButton.setVisibility(View.INVISIBLE);
                            verButton.setVisibility(View.VISIBLE);
                            otp.setVisibility(View.VISIBLE);
                        }
                        else
                            Toast.makeText(ForgotPasswordActivity.this,"server error",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(ForgotPasswordActivity.this,"server error",Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        verButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface=App.getClient().create(APIInterface.class);
                VerifyOtp verifyOtp=new VerifyOtp();
                verifyOtp.setOtp(otp.toString());

                apiInterface.verifyOtp(verifyOtp).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        if(response.body()){
                            verButton.setVisibility(View.INVISIBLE);
                            otp.setVisibility(View.INVISIBLE);
                            password.setVisibility(View.VISIBLE);
                            repassword.setVisibility(View.VISIBLE);
                            create.setVisibility(View.VISIBLE);
                            passwordText.setVisibility(View.VISIBLE);
                            repasswordText.setVisibility(View.VISIBLE);
                            email.setFocusable(false);
                        }
                        else {
                            Toast.makeText(ForgotPasswordActivity.this,"wrong otp",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(ForgotPasswordActivity.this,"server error",Toast.LENGTH_LONG).show();

                    }
                });


            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.toString().equals(repassword.toString())){
                    APIInterface apiInterface=App.getClient().create(APIInterface.class);
                    LoginDetails loginDetails=new LoginDetails();
                    loginDetails.setEmail(email.toString());
                    loginDetails.setPasssword(password.toString());
                    apiInterface.create(loginDetails).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            if(response.body()){
                                Toast.makeText(ForgotPasswordActivity.this,"new user created",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(ForgotPasswordActivity.this,HomeMerchantActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(ForgotPasswordActivity.this,"server error",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(ForgotPasswordActivity.this,"server error",Toast.LENGTH_LONG).show();

                        }
                    });
                }

            }
        });
    }
}
