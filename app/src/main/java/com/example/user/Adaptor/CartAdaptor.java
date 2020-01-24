package com.example.user.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.user.R;
import com.example.user.pojo.CartResponse;
import com.example.user.pojo.PopularProducts;

import java.util.List;

public class CartAdaptor extends RecyclerView.Adapter<CartAdaptor.ViewHolder> {

    List<CartResponse> list;
    CartApiCall cartApiCall;


    public CartAdaptor(List<CartResponse> list, CartApiCall cartApiCall) {
        this.list = list;
        this.cartApiCall = cartApiCall;
    }


    @NonNull
    @Override
    public CartAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.cart_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdaptor.ViewHolder holder, final int position) {

        Glide.with(holder.cartImage.getContext()).load(list.get(position).getUrl1()).into(holder.cartImage);
        holder.cartProductName.setText(list.get(position).getProductName());
        holder.cartQuantity.setText(list.get(position).getCartQuantity());
        holder.cartPrice.setText(String.valueOf(list.get(position).getPrice()*list.get(position).getCartQuantity()));

        holder.cartAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(holder.cartQuantity.getText().toString());
                if(quantity<5) {
                    cartApiCall.onAdd(list.get(position));//add max value to add
                }
                else{
                    holder.cartQuantityInfo.setText("Can't add more than 5 products");
                }
            }
        });

        holder.cartMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity=Integer.parseInt(holder.cartQuantity.getText().toString());
                if(quantity==1){
                    cartApiCall.onRemove(list.get(position));

                }
                else
                    cartApiCall.onMinus(list.get(position));//add min value to minus

            }
        });

        holder.cartRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartApiCall.onRemove(list.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout cartLinear;
        ImageView cartImage;
        TextView cartProductName;
        TextView cartQuantity;
        TextView cartPrice;
        ImageButton cartMinus;
        ImageButton cartAdd;
        ImageButton cartRemove;
        TextView cartQuantityInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cartLinear=itemView.findViewById(R.id.categoryLinear);
            this.cartImage=itemView.findViewById(R.id.cartImage);
            this.cartProductName=itemView.findViewById(R.id.productName);
            this.cartQuantity=itemView.findViewById(R.id.cartQuantity);
            this.cartPrice=itemView.findViewById(R.id.cartPrice);
            this.cartAdd=itemView.findViewById(R.id.cartAdd);
            this.cartMinus=itemView.findViewById(R.id.cartMinus);
            this.cartRemove=itemView.findViewById(R.id.cartRemove);
            this.cartQuantityInfo=itemView.findViewById(R.id.cartQuantityInfo);
        }
    }
    public interface CartApiCall{
        void onAdd(CartResponse cartResponse);
        void onMinus(CartResponse cartResponse);
        void onRemove(CartResponse cartResponse);
    }

}
