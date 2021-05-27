package com.android.healthessentialsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.healthessentialsapp.DetailedActivity;
import com.android.healthessentialsapp.R;
import com.android.healthessentialsapp.model.ViewAllModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    List<ViewAllModel> viewAllModelList;

    public ViewAllAdapter(Context context, List<ViewAllModel> viewAllMasksModelList) {
        this.context = context;
        this.viewAllModelList = viewAllMasksModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(viewAllModelList.get(position).getImage()).into(holder.productImage);
        Glide.with(context).load(viewAllModelList.get(position).getRating()).into(holder.productRating);
        holder.productName.setText(viewAllModelList.get(position).getName());
        holder.productPrice.setText(viewAllModelList.get(position).getPrice());
        holder.productDescription.setText(viewAllModelList.get(position).getDescription());
        holder.productLink.setText(viewAllModelList.get(position).getLink());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail", viewAllModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, productRating;
        TextView productName, productPrice, productDescription, productLink ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productRating= itemView.findViewById(R.id.product_overall_rating);
            productDescription = itemView.findViewById(R.id.description);
            productLink = itemView.findViewById(R.id.amazon_link);
        }
    }
}
