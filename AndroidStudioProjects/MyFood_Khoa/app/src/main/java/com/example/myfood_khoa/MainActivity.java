package com.example.myfood_khoa;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends AppCompatActivity {

    Database_Khoa dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new Database_Khoa(this);
        // Khi gọi getWritableDatabase(), phương thức onCreate() sẽ thực thi nếu database chưa có.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Các thao tác CRUD khác có thể thực hiện sau đây...
    }
}