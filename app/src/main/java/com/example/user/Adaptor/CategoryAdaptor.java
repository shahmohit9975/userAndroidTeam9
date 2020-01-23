package com.example.user.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.R;
import com.example.user.pojo.GetCategories;

import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {

    CategoryCommunication categoryCommunication;

    static List<GetCategories> responseList;

    public CategoryAdaptor(List<GetCategories> list,CategoryCommunication categoryCommunication) {
        this.responseList=list;
        this.categoryCommunication=categoryCommunication;
    }

    @NonNull
    @Override
    public CategoryAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.category_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdaptor.ViewHolder holder, final int position) {

        holder.categoryName.getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryCommunication.onClick(responseList.get(position));
            }
        });

        holder.categoryName.setText(responseList.get(position).getCategoryName());

    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        TextView categoryName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.categoryLinear);
            categoryName=itemView.findViewById(R.id.merchantEdit);
        }
    }

    public interface CategoryCommunication{
        void onClick(GetCategories getCategories);
    }
}
