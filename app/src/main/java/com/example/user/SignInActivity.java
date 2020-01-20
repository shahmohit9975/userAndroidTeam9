package com.example.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pogo.LoginDetails;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignInActivity extends AppCompatActivity {

    private int RC_SIGN_IN=9022;
    private String TAG="coviam";
    GoogleSignInClient mGoogleSignInClient;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    SignInButton googleSignIn;
    LoginButton facebookSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        googleSignIn =findViewById(R.id.googleSignIn);
        facebookSignIn=findViewById(R.id.facebookSignIn);

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



        //login button
        final EditText email=findViewById(R.id.emailEditText);
        final EditText password=findViewById(R.id.passwordEditText);
        final Button login=findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                APIInterface apiInterface= App.getClient().create(APIInterface.class);
                LoginDetails loginDetails=new LoginDetails();
                loginDetails.setEmail(email.toString());
                loginDetails.setPasssword(email.toString());
                apiInterface.login(loginDetails).enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                        Boolean flag=response.body();

                        if(flag==true){
                            Intent intent=new Intent(SignInActivity.this,HomeMerchantActivity.class);
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(SignInActivity.this,"wrong password ",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(SignInActivity.this,"server error",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        //create new user
        Button createNewUser=findViewById(R.id.newUser);
        createNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignInActivity.this, UserCreateActivity.class);
                startActivity(intent);
            }
        });
        //forgot password
        TextView forgotPassword=findViewById(R.id.forotPasswordTextView);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignInActivity.this, ForgotPasswordActivity.class);
            }
        });

    }
    private void signIn(){
        Intent signInIntent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
            callbackManager.onActivityResult(requestCode,resultCode,data);

        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Intent intent=new Intent(SignInActivity.this,DummyActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

}

