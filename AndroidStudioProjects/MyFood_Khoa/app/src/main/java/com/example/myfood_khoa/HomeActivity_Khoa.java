package com.example.myfood_khoa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity_Khoa extends AppCompatActivity {
    private RecyclerView rvRestaurants;
    private RestaurantAdapter_Khoa adapter;
    private ArrayList<Restaurant_Khoa> restaurantList;
    private Database_Khoa dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        dbHelper = new Database_Khoa(this);


        rvRestaurants = findViewById(R.id.rvRestaurants_Khoa);
        rvRestaurants.setLayoutManager(new LinearLayoutManager(this));


        restaurantList = getRestaurantsFromDB();

        // click item
        adapter = new RestaurantAdapter_Khoa(restaurantList, new RestaurantAdapter_Khoa.OnRestaurantClickListener() {
            @Override
            public void onRestaurantClick(Restaurant_Khoa restaurant) {

                Intent intent = new Intent(HomeActivity_Khoa.this, RestaurantFoodActivity_Khoa.class);
                intent.putExtra("ResID", restaurant.getResId());
                startActivity(intent);
            }
        });
        rvRestaurants.setAdapter(adapter);
    }

    // lấy ds từ db
    private ArrayList<Restaurant_Khoa> getRestaurantsFromDB() {
        ArrayList<Restaurant_Khoa> restaurantList = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {

            db = dbHelper.getReadableDatabase();
            String query = "SELECT ResID, Name, Address, Phone, Image FROM Restaurant";
            cursor = db.rawQuery(query, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int resId = cursor.getInt(cursor.getColumnIndexOrThrow("ResID"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
                    String address = cursor.getString(cursor.getColumnIndexOrThrow("Address"));
                    String phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
                    String image = cursor.getString(cursor.getColumnIndexOrThrow("Image"));

                    Restaurant_Khoa restaurant = new Restaurant_Khoa(resId, name, address, phone, image);
                    restaurantList.add(restaurant);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            if (db != null)
                db.close();
        }
        return restaurantList;
    }
}