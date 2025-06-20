package com.example.myfood_khoa;

public class Food_Khoa {
    private int foodId;
    private String name;
    private String type;
    private String description;
    private String image;
    private int resId;
    private double price;
    public Food_Khoa(int foodId, String name, String type, String description, String image, int resId, double price) {
        this.foodId = foodId;
        this.name = name;
        this.type = type;
        this.description = description;
        this.image = image;
        this.resId = resId;
        this.price=price;
    }

    public int getFoodId() { return foodId; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public String getImage() { return image; }
    public int getResId() { return resId; }
    public double getPrice() {
        return price;
    }

}