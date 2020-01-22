package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.Adaptor.CategoryDisplayAdaptor;
import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pogo.GetCategories;
import com.example.user.pogo.PopularProducts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryDisplayActivity extends AppCompatActivity implements CategoryDisplayAdaptor.CategoryDisplayCommunication {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_display);

        layoutManager=new LinearLayoutManager(this);
        recyclerView=findViewById(R.id.categoryRecycler);
        recyclerView.setLayoutManager(layoutManager);


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String categoryName=bundle.getString("name");
        String categoryId=bundle.getString("id");
        TextView textView =findViewById(R.id.categoriesEdit);
        textView.setText(categoryName);

        //create object for category Response

        APIInterface apiInterface= App.getClient().create(APIInterface.class);
        apiInterface.getCategoryProducts().enqueue(new Callback<List<PopularProducts>>() {
            @Override
            public void onResponse(Call<List<PopularProducts>> call, Response<List<PopularProducts>> response) {

                List<PopularProducts> list=response.body();
                mAdapter=new CategoryDisplayAdaptor(list,CategoryDisplayActivity.this);
                recyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<List<PopularProducts>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(PopularProducts popularProducts) {
        Intent intent=new Intent(CategoryDisplayActivity.this,ProductActivity.class);
        startActivity(intent);
        Bundle b=new Bundle();
        b.putString("catName",popularProducts.getCategoryName());
        b.putString("pmid",popularProducts.getMerchantAndProductId());
        b.putString("pid",popularProducts.getProductId());
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }
}
