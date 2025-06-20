package com.example.myfood_khoa;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity_Khoa extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private Database_Khoa dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        dbHelper = new Database_Khoa(this);


        etUsername = findViewById(R.id.etUsername_Khoa);
        etPassword = findViewById(R.id.etPassword_Khoa);
        btnLogin = findViewById(R.id.btnLogin_Khoa);
        tvRegister = findViewById(R.id.tvRegister_Khoa);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();


                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity_Khoa.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {

                    if (checkLogin(username, password)) {

                        Intent intent = new Intent(LoginActivity_Khoa.this, HomeActivity_Khoa.class);
                        startActivity(intent);
                        finish();
                    } else {

                        Toast.makeText(LoginActivity_Khoa.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        tvRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Điều hướng sang activity đăng kí
                Intent intent = new Intent(LoginActivity_Khoa.this, RegisterActivity_Khoa.class);
                startActivity(intent);
            }
        });
    }


    private boolean checkLogin(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM [User] WHERE Username = ? AND Password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, password});
        boolean loginSuccess = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return loginSuccess;
    }
}