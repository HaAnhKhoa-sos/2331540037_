package com.example.myfood_khoa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FoodAdapter_Khoa extends RecyclerView.Adapter<FoodAdapter_Khoa.FoodViewHolder> {


    public interface OnFoodClickListener {
        void onFoodClick(Food_Khoa food);
    }

    private ArrayList<Food_Khoa> foodList;
    private OnFoodClickListener listener;

    public FoodAdapter_Khoa(ArrayList<Food_Khoa> foodList, OnFoodClickListener listener) {
        this.foodList = foodList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_item, parent, false);
        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        final Food_Khoa food = foodList.get(position);
        holder.tvFoodName.setText(food.getName());
        holder.tvFoodPrice.setText(String.format("Giá: %.2f", food.getPrice()));
        // click món ăn
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onFoodClick(food);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView tvFoodName;
        TextView tvFoodPrice;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFoodName = itemView.findViewById(R.id.tvFoodName_Khoa);
            tvFoodPrice = itemView.findViewById(R.id.tvFoodPrice_Khoa);
        }
    }
}