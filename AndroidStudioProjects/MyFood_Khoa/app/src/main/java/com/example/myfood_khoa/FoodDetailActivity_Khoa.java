package com.example.myfood_khoa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FoodDetailActivity_Khoa extends AppCompatActivity {

    private ImageView ivFoodDetail;
    private TextView tvFoodNameDetail, tvFoodTypeDetail, tvFoodDescriptionDetail, tvFoodPriceDetail;
    private Button btnPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail_khoa);


        ivFoodDetail = findViewById(R.id.ivFoodDetail);
        tvFoodNameDetail = findViewById(R.id.tvFoodNameDetail_Khoa);
        tvFoodTypeDetail = findViewById(R.id.tvFoodTypeDetail_Khoa);
        tvFoodDescriptionDetail = findViewById(R.id.tvFoodDescriptionDetail_Khoa);
        tvFoodPriceDetail = findViewById(R.id.tvFoodPriceDetail_Khoa);
        btnPurchase = findViewById(R.id.btnPurchase_Khoa);


        Intent intent = getIntent();
        String foodName = intent.getStringExtra("FoodName");
        String foodType = intent.getStringExtra("FoodType");
        String foodDescription = intent.getStringExtra("FoodDescription");
        String foodImage = intent.getStringExtra("FoodImage");
        double foodPrice = intent.getDoubleExtra("FoodPrice", 0.0);


        tvFoodNameDetail.setText(foodName);
        tvFoodTypeDetail.setText(foodType);
        tvFoodDescriptionDetail.setText(foodDescription);
        tvFoodPriceDetail.setText(String.format("Giá: %.2f", foodPrice));


        btnPurchase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Toast.makeText(FoodDetailActivity_Khoa.this, "Mua hàng thành công!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}