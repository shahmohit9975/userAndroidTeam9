package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeMerchantActivity extends AppCompatActivity {
    SearchView searchView;
    Button button;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_merchant);
        button = findViewById(R.id.clicksearch);
        recyclerView = findViewById(R.id.rl_product_list);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeMerchantActivity.this, MovieActivity.class);
                startActivity(intent);
            }
        });
    }
}
