package com.example.myfood_khoa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RestaurantFoodActivity_Khoa extends AppCompatActivity {

    private ImageView ivRestaurantDetail;
    private TextView tvRestaurantNameDetail, tvRestaurantAddressDetail, tvRestaurantPhoneDetail;
    private RecyclerView rvFoods;

    private Database_Khoa dbHelper;
    private int resId;
    private ArrayList<Food_Khoa> foodList;
    private FoodAdapter_Khoa foodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_food_khoa);


        ivRestaurantDetail = findViewById(R.id.ivRestaurantDetail);
        tvRestaurantNameDetail = findViewById(R.id.tvRestaurantNameDetail);
        tvRestaurantAddressDetail = findViewById(R.id.tvRestaurantAddressDetail);
        tvRestaurantPhoneDetail = findViewById(R.id.tvRestaurantPhoneDetail);
        rvFoods = findViewById(R.id.rvFoods);


        dbHelper = new Database_Khoa(this);


        Intent intent = getIntent();
        resId = intent.getIntExtra("ResID", -1);

        // Load chi tiết quán ăn và danh sách món ăn
        loadRestaurantDetails();
        setupFoodList();
    }


    private void loadRestaurantDetails() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT Name, Address, Phone, Image FROM Restaurant WHERE ResID=?";
        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(resId) });

        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("Address"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
            String image = cursor.getString(cursor.getColumnIndexOrThrow("Image"));

            tvRestaurantNameDetail.setText(name);
            tvRestaurantAddressDetail.setText(address);
            tvRestaurantPhoneDetail.setText(phone);


        }

        if (cursor != null) cursor.close();
        db.close();
    }
    private void setupFoodList() {
        foodList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT FoodID, Name, Type, Description, Image, Price FROM Food WHERE ResID=?";
        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(resId) });

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int foodId = cursor.getInt(cursor.getColumnIndexOrThrow("FoodID"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
                String type = cursor.getString(cursor.getColumnIndexOrThrow("Type"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("Description"));
                String image = cursor.getString(cursor.getColumnIndexOrThrow("Image"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("Price"));
                Food_Khoa food = new Food_Khoa(foodId, name, type, description, image, resId, price);
                foodList.add(food);
            } while (cursor.moveToNext());
        }

        if (cursor != null) cursor.close();
        db.close();

        // Thiết lập RecyclerView cho danh sách món ăn
        rvFoods.setLayoutManager(new LinearLayoutManager(this));
        foodAdapter = new FoodAdapter_Khoa(foodList, new FoodAdapter_Khoa.OnFoodClickListener() {
            @Override
            public void onFoodClick(Food_Khoa food) {

                Intent intent = new Intent(RestaurantFoodActivity_Khoa.this, FoodDetailActivity_Khoa.class);
                intent.putExtra("FoodID", food.getFoodId());
                intent.putExtra("FoodName", food.getName());
                intent.putExtra("FoodType", food.getType());
                intent.putExtra("FoodDescription", food.getDescription());
                intent.putExtra("FoodImage", food.getImage());
                intent.putExtra("FoodPrice", food.getPrice());

                startActivity(intent);
            }
        });
        rvFoods.setAdapter(foodAdapter);

    }
}