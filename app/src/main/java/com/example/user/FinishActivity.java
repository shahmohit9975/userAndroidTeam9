package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Intent intent=getIntent();
        Bundle b=intent.getExtras();
        int finalPrice=b.getInt("quantity")*b.getInt("cost");
        TextView finalDisplay=findViewById(R.id.money);
        finalDisplay.setText(String.valueOf(finalPrice));


        Button goHome=findViewById(R.id.goHome);

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(FinishActivity.this,HomeMerchantGeneral.class);
                startActivity(intent);
                finish();
            }
        });


    }




}
