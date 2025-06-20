package com.example.myfood_khoa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database_Khoa extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "Database_Khoa.db";
    private static final int DATABASE_VERSION = 1;


    private static final String CREATE_USER_TABLE = "CREATE TABLE [User] (" +
            "UserID INTEGER PRIMARY KEY, " +
            "Name TEXT, " +
            "Gender TEXT, " +
            "DateOfBirth DATE, " +
            "Phone TEXT, " +
            "Username TEXT, " +
            "Password TEXT" +
            ");";


    private static final String CREATE_RESTAURANT_TABLE = "CREATE TABLE Restaurant (" +
            "ResID INTEGER PRIMARY KEY, " +
            "Name TEXT, " +
            "Address TEXT, " +
            "Phone TEXT, " +
            "Image TEXT" +
            ");";


    private static final String CREATE_FOOD_TABLE = "CREATE TABLE Food (" +
            "FoodID INTEGER PRIMARY KEY, " +
            "Name TEXT, " +
            "Type TEXT, " +
            "Description TEXT, " +
            "Image TEXT, " +
            "Price REAL, " +
            "ResID INTEGER, " +
            "FOREIGN KEY (ResID) REFERENCES Restaurant(ResID)" +
            ");";


    private static final String CREATE_ORDER_TABLE = "CREATE TABLE [Order] (" +
            "OrderID INTEGER PRIMARY KEY, " +
            "Address TEXT, " +
            "Date_Order DATE, " +
            "Total_Value REAL, " +
            "Status TEXT, " +
            "UserID INTEGER, " +
            "FOREIGN KEY (UserID) REFERENCES [User](UserID)" +
            ");";


    private static final String CREATE_ORDER_DETAIL_TABLE = "CREATE TABLE OrderDetail (" +
            "OrderID INTEGER, " +
            "FoodID INTEGER, " +
            "Size TEXT, " +
            "Food TEXT, " +
            "Quantity INTEGER, " +
            "PRIMARY KEY (OrderID, FoodID), " +
            "FOREIGN KEY (OrderID) REFERENCES [Order](OrderID), " +
            "FOREIGN KEY (FoodID) REFERENCES Food(FoodID)" +
            ");";

    public Database_Khoa(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_RESTAURANT_TABLE);
        db.execSQL(CREATE_FOOD_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
        db.execSQL(CREATE_ORDER_DETAIL_TABLE);


        db.execSQL("INSERT INTO [User] (UserID, Name, Gender, DateOfBirth, Phone, Username, Password) " +
                "VALUES (1, 'Nguyễn Văn A', 'Nam', '1990-01-01', '0123456789', 'user1', 'pass1');");
        db.execSQL("INSERT INTO [User] (UserID, Name, Gender, DateOfBirth, Phone, Username, Password) " +
                "VALUES (2, 'Trần Thị B', 'Nữ', '1992-02-02', '0987654321', 'user2', 'pass2');");
        db.execSQL("INSERT INTO [User] (UserID, Name, Gender, DateOfBirth, Phone, Username, Password) " +
                "VALUES (3, 'Lê Văn C', 'Nam', '1988-03-03', '0912345678', 'user3', 'pass3');");
        db.execSQL("INSERT INTO [User] (UserID, Name, Gender, DateOfBirth, Phone, Username, Password) " +
                "VALUES (4, 'Phạm Thị D', 'Nữ', '1995-04-04', '0901234567', 'user4', 'pass4');");
        db.execSQL("INSERT INTO [User] (UserID, Name, Gender, DateOfBirth, Phone, Username, Password) " +
                "VALUES (5, 'Hoàng Văn E', 'Nam', '1991-05-05', '0934567890', 'user5', 'pass5');");


        db.execSQL("INSERT INTO Restaurant (ResID, Name, Address, Phone, Image) " +
                "VALUES (1, 'Quán Ăn A', '123 Đường A, TP A', '0123456789', 'restaurant_a.jpg');");
        db.execSQL("INSERT INTO Restaurant (ResID, Name, Address, Phone, Image) " +
                "VALUES (2, 'Quán Ăn B', '456 Đường B, TP B', '0987654321', 'restaurant_b.jpg');");
        db.execSQL("INSERT INTO Restaurant (ResID, Name, Address, Phone, Image) " +
                "VALUES (3, 'Quán Ăn C', '789 Đường C, TP C', '0911122233', 'restaurant_c.jpg');");
        db.execSQL("INSERT INTO Restaurant (ResID, Name, Address, Phone, Image) " +
                "VALUES (4, 'Quán Ăn D', '321 Đường D, TP D', '0933344556', 'restaurant_d.jpg');");
        db.execSQL("INSERT INTO Restaurant (ResID, Name, Address, Phone, Image) " +
                "VALUES (5, 'Quán Ăn E', '654 Đường E, TP E', '0909988776', 'restaurant_e.jpg');");



        db.execSQL("INSERT INTO Food (FoodID, Name, Type, Description, Image, Price, ResID) " +
                "VALUES (1, 'Phở bò', 'Main', 'Phở bò truyền thống', 'pho_bo.jpg', 50000, 1);");
        db.execSQL("INSERT INTO Food (FoodID, Name, Type, Description, Image, Price, ResID) " +
                "VALUES (2, 'Bún chả', 'Main', 'Bún chả Hà Nội', 'bun_cha.jpg', 45000, 1);");


        db.execSQL("INSERT INTO Food (FoodID, Name, Type, Description, Image, Price, ResID) " +
                "VALUES (3, 'Cơm tấm', 'Main', 'Cơm tấm Sài Gòn', 'com_tam.jpg', 60000, 2);");
        db.execSQL("INSERT INTO Food (FoodID, Name, Type, Description, Image, Price, ResID) " +
                "VALUES (4, 'Hủ tiếu', 'Main', 'Hủ tiếu Nam Vang', 'hu_tieu.jpg', 55000, 2);");


        db.execSQL("INSERT INTO Food (FoodID, Name, Type, Description, Image, Price, ResID) " +
                "VALUES (5, 'Gỏi cuốn', 'Appetizer', 'Gỏi cuốn tươi ngon', 'goi_cuon.jpg', 30000, 3);");
        db.execSQL("INSERT INTO Food (FoodID, Name, Type, Description, Image, Price, ResID) " +
                "VALUES (6, 'Chả giò', 'Appetizer', 'Chả giò giòn tan', 'cha_gio.jpg', 35000, 3);");


        db.execSQL("INSERT INTO Food (FoodID, Name, Type, Description, Image, Price, ResID) " +
                "VALUES (7, 'Bánh mì', 'Snack', 'Bánh mì Việt Nam', 'banh_mi.jpg', 20000, 4);");
        db.execSQL("INSERT INTO Food (FoodID, Name, Type, Description, Image, Price, ResID) " +
                "VALUES (8, 'Xôi gà', 'Snack', 'Xôi gà thơm ngon', 'xoi_ga.jpg', 25000, 4);");


        db.execSQL("INSERT INTO Food (FoodID, Name, Type, Description, Image, Price, ResID) " +
                "VALUES (9, 'Chè ba màu', 'Dessert', 'Chè ba màu mát lạnh', 'che_ba_mau.jpg', 15000, 5);");
        db.execSQL("INSERT INTO Food (FoodID, Name, Type, Description, Image, Price, ResID) " +
                "VALUES (10, 'Bánh flan', 'Dessert', 'Bánh flan kem béo', 'banh_flan.jpg', 18000, 5);");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS OrderDetail");
        db.execSQL("DROP TABLE IF EXISTS [Order]");
        db.execSQL("DROP TABLE IF EXISTS Food");
        db.execSQL("DROP TABLE IF EXISTS Restaurant");
        db.execSQL("DROP TABLE IF EXISTS [User]");
        // Tạo lại các bảng và dữ liệu mẫu
        onCreate(db);
    }
}