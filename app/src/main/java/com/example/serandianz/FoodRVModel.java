package com.example.serandianz;


import android.os.Parcel;
import android.os.Parcelable;

public class FoodRVModel implements Parcelable   {
//rv model

    private String foodName;
    private String foodPrice;
    private String foodImg;
    private String foodLink;
    private String foodDescription;
    private String foodId;

    public FoodRVModel(){

    }

    protected FoodRVModel(Parcel in) {
        foodName = in.readString();
        foodPrice = in.readString();
        foodImg = in.readString();
        foodLink = in.readString();
        foodDescription = in.readString();
        foodId = in.readString();
    }

    public static final Creator<FoodRVModel> CREATOR = new Creator<FoodRVModel>() {
        @Override
        public FoodRVModel createFromParcel(Parcel in) {
            return new FoodRVModel(in);
        }

        @Override
        public FoodRVModel[] newArray(int size) {
            return new FoodRVModel[size];
        }
    };

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public String getFoodLink() {
        return foodLink;
    }

    public void setFoodLink(String foodLink) {
        this.foodLink = foodLink;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public FoodRVModel(String foodName, String foodPrice, String foodImg, String foodLink, String foodDescription, String foodId) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodImg = foodImg;
        this.foodLink = foodLink;
        this.foodDescription = foodDescription;
        this.foodId = foodId;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(foodName);
        parcel.writeString(foodPrice);
        parcel.writeString(foodImg);
        parcel.writeString(foodLink);
        parcel.writeString(foodDescription);
        parcel.writeString(foodId);
    }
}
