package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.user.api.APIInterface;
import com.example.user.api.App;
import com.example.user.pogo.SearchString;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductList extends AppCompatActivity implements ProgrammingAdapter.ProductCommunication {
    private RecyclerView recyclerView;
    private ProgrammingAdapter programmingAdapter;
    private String searchName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Intent intent=getIntent();
        searchName=intent.getStringExtra("searchName");
        SearchString searchString=new SearchString(searchName);




        App.getClient().create(APIInterface.class).getSearchResults(searchString).enqueue(new Callback<List<ProductDTO>>() {
            @Override

            public void onResponse(Call<List<ProductDTO>> call, Response<List<ProductDTO>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductDTO>> call, Throwable t) {
                Toast.makeText(ProductList.this,"Sorry Not Found...Please try later!",Toast.LENGTH_SHORT).show();
            }
        });

    }
    void generateDataList(List<ProductDTO> products){
        recyclerView=findViewById(R.id.recyclerView);
        programmingAdapter=new ProgrammingAdapter(products, ProductList.this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ProductList.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(programmingAdapter);
    }
    @Override
    public void onItemClick(ProductDTO productDTO){
        Intent intent=new Intent(ProductList.this,ProductDescriptionActivity.class);
        intent.putExtra("searchName",searchName);
        //intent.putExtra("movie",movie);
        startActivity(intent);
    }
}
