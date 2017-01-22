package com.pojo;

import java.util.ArrayList;

/**
 * Created by elsaidel on 1/12/2017.
 */

public class Food {
    public static ArrayList<Food> proteinFoods = new ArrayList<>();
    public static ArrayList<Food> carbFoods = new ArrayList<>();
    public static ArrayList<Food> fatFoods = new ArrayList<>();
    public static ArrayList<Food> fiberFoods = new ArrayList<>();
    String foodName;
    int foodType;
    double foodCalories, foodValue;

    public Food() {
    }

    public Food(String foodName, int foodType, double foodValue, double foodCalories) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodValue = foodValue;
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

    public double getFoodCalories() {
        return foodCalories;
    }

    public void setFoodCalories(double foodCalories) {
        this.foodCalories = foodCalories;
    }

    public double getFoodValue() {
        return foodValue;
    }

    public void setFoodValue(double foodValue) {
        this.foodValue = foodValue;
    }

    public void fillProteinFoods() {

    }

    public void fillCarbFoods() {

    }

    public void fillFatFoods() {

    }
}
