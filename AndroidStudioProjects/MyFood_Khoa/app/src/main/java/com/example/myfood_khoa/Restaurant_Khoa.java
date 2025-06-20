package com.example.myfood_khoa;

public class Restaurant_Khoa {
    private int resId;
    private String name;
    private String address;
    private String phone;
    private String image; // Có thể là tên file hay URL ảnh

    public Restaurant_Khoa(int resId, String name, String address, String phone, String image) {
        this.resId = resId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.image = image;
    }

    public int getResId() {
        return resId;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getImage() {
        return image;
    }
}