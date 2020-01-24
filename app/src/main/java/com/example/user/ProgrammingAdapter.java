package com.example.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.BoxViewHolder> {

    class BoxViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        BoxViewHolder(View itemView){
            super(itemView);
            this.textView=itemView.findViewById(R.id.product_name);
            this.imageView=itemView.findViewById(R.id.product_pic);
        }
    }
private List<ProductDTO>data;
private ProductCommunication productCommunication;
    public ProgrammingAdapter(List<ProductDTO> data, ProductCommunication productCommunication) {
        this.data=data;
        this.productCommunication=productCommunication;
    }

    @NonNull
    @Override
    public ProgrammingAdapter.BoxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View listItem=layoutInflater.inflate(R.layout.product_view,parent,false);
        return new BoxViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingAdapter.BoxViewHolder holder, final int position) {
        holder.textView.setText(data.get(position).getProductName());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productCommunication.onItemClick(data.get(position));
            }
        });
        Glide.with(holder.imageView.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).load(data.get(position).getUrl1()).into(holder.imageView);

    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ProductCommunication {
        void onItemClick(ProductDTO position);
    }
}
