package com.example.user;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.BoxViewHolder> {
    class BoxViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        BoxViewHolder(View itemView){
            super(itemView);
            this.textView=itemView.findViewById(R.id.view_text);
            this.imageView=itemView.findViewById(R.id.view_image);
        }
    }
    List<Movie> Movies;
    IEmployeeCommunication custobj;
    public MovieAdapter(List<Movie> mov,IEmployeeCommunication customInterface){
        this.Movies=mov;
        this.custobj=customInterface;
    }
    @NonNull
    @Override
    public BoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.movie_view,parent,false);
        return new BoxViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull BoxViewHolder holder, final int position){
        holder.textView.setText(Movies.get(position).getName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custobj.onClick(Movies.get(position));
            }
        });
        Glide.with(holder.imageView.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).load(Movies.get(position).getUrl().getLarge()).into(holder.imageView);
    }
    @Override
    public int getItemCount(){
        return Movies.size();
    }

    public interface IEmployeeCommunication {
        void onClick(Movie movie);
    }

}