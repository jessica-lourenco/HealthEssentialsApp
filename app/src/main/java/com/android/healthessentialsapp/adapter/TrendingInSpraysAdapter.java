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
import com.android.healthessentialsapp.model.TrendingInSpraysModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class TrendingInSpraysAdapter extends RecyclerView.Adapter<TrendingInSpraysAdapter.ViewHolder> {

    private Context context;
    private List<TrendingInSpraysModel> trendingInSpraysModelList;

    public TrendingInSpraysAdapter(Context context, List<TrendingInSpraysModel> trendingInSpraysModelList) {
        this.context = context;
        this.trendingInSpraysModelList = trendingInSpraysModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.trending_now, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(trendingInSpraysModelList.get(position).getImage()).into(holder.productImage);
        Glide.with(context).load(trendingInSpraysModelList.get(position).getRating()).into(holder.productRating);
        holder.productName.setText(trendingInSpraysModelList.get(position).getName());
        holder.productPrice.setText(trendingInSpraysModelList.get(position).getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("detail", trendingInSpraysModelList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trendingInSpraysModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage, productRating;
        TextView productName, productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productRating= itemView.findViewById(R.id.product_overall_rating);
        }
    }
}
