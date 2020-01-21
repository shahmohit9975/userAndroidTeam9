package com.example.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        String name=movie.getName();
        String timestamp=movie.getTimestamp();
        Url imgurl=movie.getUrl();
        ImageView image=(ImageView) findViewById(R.id.images);
        Glide.with(this).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_foreground)).load(imgurl.getMedium()).into(image);
        TextView moviename=(TextView) findViewById(R.id.name);
        moviename.setText(name);
        TextView releasedate=(TextView) findViewById(R.id.timestamp);
        releasedate.setText(timestamp);
    }
}
