package com.pojo;

/**
 * Created by elsaidel on 1/12/2017.
 */

public class Food {

    String foodName;
    int foodType,foodCalories;

    public Food() {
    }

    public Food(String foodName, int foodType, int foodCalories) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodCalories = foodCalories;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodType() {
        return foodType;
    }

    public void setFoodType(int foodType) {
        this.foodType = foodType;
    }

    public int getFoodCalories() {
        return foodCalories;
    }

    public void setFoodCalories(int foodCalories) {
        this.foodCalories = foodCalories;
    }
}
