package com.pojo;

import java.util.ArrayList;

/**
 * Created by elsaidel on 1/14/2017.
 */

public class Meal {

    ArrayList<Food> mealFoods;

    public Meal(ArrayList<Food> mealFoods) {
        this.mealFoods = mealFoods;
    }

    public Meal() {
        mealFoods = new ArrayList<>();
    }

    public ArrayList<Food> getMealFoods() {
        return mealFoods;
    }

    public void setMealFoods(ArrayList<Food> mealFoods) {
        this.mealFoods = mealFoods;
    }
}
