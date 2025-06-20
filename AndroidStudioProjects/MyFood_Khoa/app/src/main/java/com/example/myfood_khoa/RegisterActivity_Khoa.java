package com.example.myfood_khoa;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity_Khoa extends AppCompatActivity {

    private EditText etName, etUsername, etPassword, etRePassword;
    private Button btnRegister;
    private Database_Khoa dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new Database_Khoa(this);


        etName = findViewById(R.id.etName_Khoa);
        etUsername = findViewById(R.id.etUsername_Khoa);
        etPassword = findViewById(R.id.etPassword_Khoa);
        etRePassword = findViewById(R.id.etRePassword_Khoa);
        btnRegister = findViewById(R.id.btnRegister_Khoa);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                String name = etName.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String repassword =etRePassword.getText().toString().trim();
                if(name.isEmpty() || username.isEmpty() || password.isEmpty() ||repassword.isEmpty()){
                    Toast.makeText(RegisterActivity_Khoa.this, "Vui lòng nhập đầy đủ thông tin cần thiết", Toast.LENGTH_SHORT).show();
                    return;
                }


                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.put("UserID", System.currentTimeMillis());
                values.put("Name", name);
                values.put("Username", username);
                values.put("Password", password);

                long result = db.insert("[User]", null, values);
                db.close();

                if(result != -1) {
                    Toast.makeText(RegisterActivity_Khoa.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                    finish(); // Kết thúc activity đăng kí để quay lại màn hình đăng nhập
                } else {
                    Toast.makeText(RegisterActivity_Khoa.this, "Đăng kí thất bại! Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}