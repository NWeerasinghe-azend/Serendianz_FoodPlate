package com.example.serandianz;

public class HelaBojun {


    private String FoodId;

    private String CustomerId;

    private String FoodCategory;
    private String FoodName;
    private String FoodQuantity;


    private String location;
    private String time;

    public HelaBojun() {
    }

    public HelaBojun(String foodId, String customerId, String foodCategory, String foodName, String foodQuantity, String location, String time) {
        FoodId = foodId;
        CustomerId = customerId;
        FoodCategory = foodCategory;
        FoodName = foodName;
        FoodQuantity = foodQuantity;
        this.location = location;
        this.time = time;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getFoodId() {
        return FoodId;
    }

    public void setFoodId(String foodId) {
        FoodId = foodId;
    }

    public String getFoodCategory() {
        return FoodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        FoodCategory = foodCategory;
    }

    public String getFoodName() {
        return FoodName;
    }

    public void setFoodName(String foodName) {
        FoodName = foodName;
    }

    public String getFoodQuantity() {
        return FoodQuantity;
    }

    public void setFoodQuantity(String foodQuantity) {
        FoodQuantity = foodQuantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
