package com.example.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.api.App2;
import com.example.user.pojo.LoginDetails;
import com.example.user.pojo.LoginRes;
import com.facebook.CallbackManager;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


import java.util.List;

import okhttp3.Headers;
import okhttp3.internal.http.HttpHeaders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;


public class SignInActivity extends AppCompatActivity {

    private int RC_SIGN_IN = 9022;
    private String TAG = "coviam";
    GoogleSignInClient mGoogleSignInClient;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    SignInButton googleSignIn;
    LoginButton facebookSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        googleSignIn = findViewById(R.id.googleSignIn);
        facebookSignIn = findViewById(R.id.facebookSignIn);

        googleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.googleSignIn:
                        signIn();
                        break;
                    // ...
                }

            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();

        //facebook
        /*

        facebookSignIn = (LoginButton) findViewById(R.id.facebookSignIn);
        facebookSignIn.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        facebookSignIn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent=new Intent(SignInActivity.this,DummyActivity.class);
                startActivity(intent);
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        */


        //login button
        final EditText email = findViewById(R.id.emailEditText);
        final EditText password = findViewById(R.id.passwordEditText);
        final Button login = findViewById(R.id.loginButton);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LoginDetails loginDetails = new LoginDetails();
                loginDetails.setUserEmail(email.getText().toString());
                loginDetails.setUserPasssword(password.getText().toString());
                APIInterface apiInterface = App2.getClient().create(APIInterface.class);

                apiInterface.login(loginDetails).enqueue(new Callback<LoginRes>() {
                    @Override
                    public void onResponse(Call<LoginRes> call, Response<LoginRes> response) {

                        LoginRes loginRes=response.body();
                        Headers headers=response.headers();

                        Log.d(TAG, "onResponse: "+response.headers());
                        Log.d(TAG, "onResponse: "+response.code());
                        String cookie=response.headers().get("Set-Cookie");
                        Log.d(TAG, "onResponse: "+cookie);


                        Toast.makeText(SignInActivity.this,cookie,Toast.LENGTH_LONG).show();
                        if(loginRes.isLoginStatus()){
                            Intent intent=new Intent(SignInActivity.this,HomeMerchantGeneral.class);
                            Toast.makeText(SignInActivity.this,"done!!! ",Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences=getSharedPreferences("emailAccess",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putBoolean("flag",true);
                            editor.putString("email",email.getText().toString());
                            editor.commit();
                            editor.apply();
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(SignInActivity.this,"wrong password!!! ",Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<LoginRes> call, Throwable t) {

                        Toast.makeText(SignInActivity.this,"fail!!! ",Toast.LENGTH_SHORT).show();


                    }
                });



            }
        });

        //create new user
        Button createNewUser = findViewById(R.id.newUser);
        createNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, EmailVerificationActivity.class);
                startActivity(intent);
            }
        });
        //forgot password
        TextView forgotPassword = findViewById(R.id.forgotPasswordTextView);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent = new Intent(SignInActivity.this, HomeMerchantGeneral.class);
            startActivity(intent);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

}

