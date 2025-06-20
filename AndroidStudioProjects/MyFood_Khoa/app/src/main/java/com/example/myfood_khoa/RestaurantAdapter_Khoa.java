package com.example.myfood_khoa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantAdapter_Khoa extends RecyclerView.Adapter<RestaurantAdapter_Khoa.RestaurantViewHolder> {

    public interface OnRestaurantClickListener {
        void onRestaurantClick(Restaurant_Khoa restaurant);
    }

    private List<Restaurant_Khoa> restaurantList;
    private OnRestaurantClickListener listener;

    public RestaurantAdapter_Khoa(List<Restaurant_Khoa> restaurantList, OnRestaurantClickListener listener) {
        this.restaurantList = restaurantList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant_Khoa restaurant = restaurantList.get(position);
        holder.tvRestaurantName.setText(restaurant.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRestaurantClick(restaurant);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRestaurant;
        TextView tvRestaurantName;
        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRestaurant = itemView.findViewById(R.id.ivRestaurant_Khoa);
            tvRestaurantName = itemView.findViewById(R.id.tvRestaurantName_Khoa);
        }
    }
}
