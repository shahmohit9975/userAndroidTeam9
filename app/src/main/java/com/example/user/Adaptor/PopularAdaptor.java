package com.example.user.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.user.R;
import com.example.user.pojo.PopularProducts;

import java.util.List;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {


    static List<PopularProducts> list;
    PopularCommunication popularCommunication;

    public PopularAdaptor(List<PopularProducts> list, PopularCommunication popularCommunication) {
        this.list=list;
        this.popularCommunication=popularCommunication;
    }

    @NonNull
    @Override
    public PopularAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.popular_view, parent, false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdaptor.ViewHolder holder, final int position) {

        holder.name.setText(list.get(position).getProductName());
        int pr=list.get(position).getPrice();
        int sr=list.get(position).getSellingPrice();
        holder.price.setText(" "+pr+" ");
        holder.sellPrice.setText(" "+sr+" ");
        holder.ratingBar.setRating((float)list.get(position).getProductRating());
        Glide.with(holder.image.getContext()).load(list.get(position).getUrl1()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popularCommunication.onClick(list.get(position));

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView price;
        public TextView sellPrice;
        public RatingBar ratingBar;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name=itemView.findViewById(R.id.popularName);
            this.image=itemView.findViewById(R.id.popularImage);
            this.price=itemView.findViewById(R.id.popularPrice);
            this.sellPrice=itemView.findViewById(R.id.sellPrice);
            this.ratingBar=itemView.findViewById(R.id.ratingView);
            this.linearLayout=itemView.findViewById(R.id.orderLinear);


        }
    }
    public interface PopularCommunication{
        void onClick(PopularProducts popularProducts);
    }
}
