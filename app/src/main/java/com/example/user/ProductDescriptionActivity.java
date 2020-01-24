package com.example.user;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class ProductDescriptionActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
        ImageSlider imageSlider=findViewById(R.id.product_image);

        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("\"https://1.bp.blogspot.com/-GUZsgr8my50/XJUWOhyHyaI/AAAAAAAABUo/bljp3LCS3SUtj-judzlntiETt7G294WcgCLcBGAs/s1600/fox.jpg","image1"));
        slideModels.add(new SlideModel("\"https://1.bp.blogspot.com/-GUZsgr8my50/XJUWOhyHyaI/AAAAAAAABUo/bljp3LCS3SUtj-judzlntiETt7G294WcgCLcBGAs/s1600/fox.jpg","image2"));
        slideModels.add(new SlideModel("\"https://1.bp.blogspot.com/-GUZsgr8my50/XJUWOhyHyaI/AAAAAAAABUo/bljp3LCS3SUtj-judzlntiETt7G294WcgCLcBGAs/s1600/fox.jpg","image1"));

        imageSlider.setImageList(slideModels,true);

    }
}
