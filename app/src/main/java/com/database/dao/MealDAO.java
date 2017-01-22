package com.database.dao;

/**
 * Created by KHoloud on 4/12/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.constants.Constants;
import com.database.helper.DatabaseHelper;
import com.pojo.Meal;

import java.util.ArrayList;

public class MealDAO {

    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public MealDAO(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    //----------------------------------------------------------------
    //  Create new plan
    //----------------------------------------------------------------
    public void addMeal(Meal meal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.EMAIL, meal.getEmail());
        contentValues.put(Constants.PROTEIN_FOOD_NAME, meal.getProteinFoodName());
        contentValues.put(Constants.CARB_FOOD_NAME, meal.getCarbFoodName());
        contentValues.put(Constants.FAT_FOOD_NAME, meal.getFatFoodName());
        contentValues.put(Constants.FIBER_FOOD_NAME, meal.getFiberFoodName());
        contentValues.put(Constants.PROTEIN_GRAMS, meal.getProteinGrams());
        contentValues.put(Constants.CARB_GRAMS, meal.getCarbGrams());
        contentValues.put(Constants.FAT_GRAMS, meal.getFatGrams());
        contentValues.put(Constants.PLAN_NUMBER, meal.getPlanNumber());
        database.insert(Constants.MEALS_TABLE, null, contentValues);
    }


    //----------------------------------------------------------------
    //  Create new plan given array list of meals
    //----------------------------------------------------------------
    public void addMeal(ArrayList<Meal> meals) {
        for (Meal meal : meals) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.EMAIL, meal.getEmail());
            contentValues.put(Constants.PROTEIN_FOOD_NAME, meal.getProteinFoodName());
            contentValues.put(Constants.CARB_FOOD_NAME, meal.getCarbFoodName());
            contentValues.put(Constants.FAT_FOOD_NAME, meal.getFatFoodName());
            contentValues.put(Constants.FIBER_FOOD_NAME, meal.getFiberFoodName());
            contentValues.put(Constants.PROTEIN_GRAMS, meal.getProteinGrams());
            contentValues.put(Constants.CARB_GRAMS, meal.getCarbGrams());
            contentValues.put(Constants.FAT_GRAMS, meal.getFatGrams());
            contentValues.put(Constants.PLAN_NUMBER, meal.getPlanNumber());
            database.insert(Constants.MEALS_TABLE, null, contentValues);
        }
    }

    //----------------------------------------------------------------
    //  Get plan meals given plan number
    //----------------------------------------------------------------
    public ArrayList<Meal> getPlanMeals(int planNumber) {
        ArrayList<Meal> meals = new ArrayList<>();
        Cursor c;
        String query = "Select *" +
                " from " + Constants.MEALS_TABLE +
                " where " + Constants.PLAN_NUMBER + "=" + planNumber + "";

        c = database.rawQuery(query, null);
        while (c.moveToNext()) {
            Meal meal = new Meal();
            meal.setEmail(c.getString(c.getColumnIndex(Constants.EMAIL)));
            meal.setPlanNumber(c.getInt(c.getColumnIndex(Constants.PLAN_NUMBER)));
            meal.setProteinFoodName(c.getString(c.getColumnIndex(Constants.PROTEIN_FOOD_NAME)));
            meal.setCarbFoodName(c.getString(c.getColumnIndex(Constants.CARB_FOOD_NAME)));
            meal.setFatFoodName(c.getString(c.getColumnIndex(Constants.FAT_FOOD_NAME)));
            meal.setFiberFoodName(c.getString(c.getColumnIndex(Constants.FIBER_FOOD_NAME)));
            meal.setProteinGrams(c.getInt(c.getColumnIndex(Constants.PROTEIN_GRAMS)));
            meal.setCarbGrams(c.getInt(c.getColumnIndex(Constants.CARB_GRAMS)));
            meal.setFatGrams(c.getInt(c.getColumnIndex(Constants.FAT_GRAMS)));
            meals.add(meal);
        }
        return meals;
    }


    //----------------------------------------------------------------
    //  Delete all meals
    //----------------------------------------------------------------
    public boolean deleteAllMeals() {
        return database.delete(Constants.MEALS_TABLE, null, null) > 0;
    }
}
