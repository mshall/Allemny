package com.database.dao;

/**
 * Created by KHoloud on 4/12/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.allemny.R;
import com.constants.Constants;
import com.database.helper.DatabaseHelper;
import com.pojo.Food;

import java.util.ArrayList;

public class FoodDAO {

    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public FoodDAO(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    //----------------------------------------------------------------
    //  Add all foods to the database
    //----------------------------------------------------------------
    public void addAllFoods(ArrayList<Food> foods) {
        for (Food food : foods) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.FOOD_NAME, food.getFoodName());
            contentValues.put(Constants.FOOD_TYPE, food.getFoodType());
            contentValues.put(Constants.FOOD_CALORIES, food.getFoodCalories());
            database.insert(Constants.FOOD_TABLE, null, contentValues);
        }

    }


    //----------------------------------------------------------------
    //  Get food details given foodId
    //----------------------------------------------------------------
    public Food getFoodDetails(int foodId) {
        Food food = new Food();
        Cursor c;
        String query = "Select *" +
                " from " + Constants.FOOD_TABLE +
                " where " + Constants.FOOD_ID + "=" + foodId + "";

        c = database.rawQuery(query, null);
        if (c != null) {
            if (c.moveToFirst()) {
                food.setFoodCalories(c.getInt(c.getColumnIndex(Constants.FOOD_CALORIES)));
                food.setFoodName(c.getString(c.getColumnIndex(Constants.FOOD_NAME)));
                food.setFoodType(c.getInt(c.getColumnIndex(Constants.FOOD_TYPE)));
            }
        } else {
            return null;
        }
        return food;
    }

    //----------------------------------------------------------------
    //  Delete all foods
    //----------------------------------------------------------------
    public boolean deleteAllFoods() {
        return database.delete(Constants.FOOD_TABLE, null, null) > 0;
    }

    //----------------------------------------------------------------
    //  Initialize foods list
    //----------------------------------------------------------------
    public void initializeAllFoods() {
        Food.proteinFoods.clear();
        Food.carbFoods.clear();
        Food.fatFoods.clear();
        Food.fiberFoods.clear();
        //---------- Protein foods
        Food food = new Food(context.getString(R.string.tuna_in_water), Constants.FOOD_PROTEIN, .26, 1.16);
        Food food1 = new Food(context.getString(R.string.grilled_chicken_breast), Constants.FOOD_PROTEIN, .23, 1.1);
        Food food2 = new Food(context.getString(R.string.grilled_turkey_beast), Constants.FOOD_PROTEIN, .17, 1.04);
        Food food3 = new Food(context.getString(R.string.lean_ground_beef), Constants.FOOD_PROTEIN, .27, 2.14);
        Food food4 = new Food(context.getString(R.string.grilled_lean_steak), Constants.FOOD_PROTEIN, .26, 2.12);
        Food food5 = new Food(context.getString(R.string.eggs), Constants.FOOD_PROTEIN, .12, 1.13);
        Food food6 = new Food(context.getString(R.string.egg_white), Constants.FOOD_PROTEIN, .127, 1);
        Food food7 = new Food(context.getString(R.string.cottage_cheese), Constants.FOOD_PROTEIN, .12, .72);
        Food food8 = new Food(context.getString(R.string.fish_fillet), Constants.FOOD_PROTEIN, .18, .82);
        Food food9 = new Food(context.getString(R.string.whey_protein_low_carb), Constants.FOOD_PROTEIN, .2, .9);
        Food food10 = new Food(context.getString(R.string.salmon), Constants.FOOD_PROTEIN, .22, 2.06);
        Food food11 = new Food(context.getString(R.string.turkey_sausage_low_fat), Constants.FOOD_PROTEIN, .24, 1.96);
        Food food12 = new Food(context.getString(R.string.greek_yogurt_low_fat), Constants.FOOD_PROTEIN, .11, .59);
        if (Food.proteinFoods.isEmpty()) {
            Food.proteinFoods.add(food);
            Food.proteinFoods.add(food1);
            Food.proteinFoods.add(food2);
            Food.proteinFoods.add(food3);
            Food.proteinFoods.add(food4);
            Food.proteinFoods.add(food5);
            Food.proteinFoods.add(food6);
            Food.proteinFoods.add(food7);
            Food.proteinFoods.add(food8);
            Food.proteinFoods.add(food9);
            Food.proteinFoods.add(food10);
            Food.proteinFoods.add(food11);
            Food.proteinFoods.add(food12);
        }
        //---------- Carb foods
        Food carbFood = new Food(context.getString(R.string.oats), Constants.FOOD_CARB, .66, 3.89);
        Food carbFood1 = new Food(context.getString(R.string.sweet_potato), Constants.FOOD_CARB, .21, .9);
        Food carbFood2 = new Food(context.getString(R.string.rice), Constants.FOOD_CARB, .21, 1.11);
        Food carbFood3 = new Food(context.getString(R.string.whole_wheat_bread), Constants.FOOD_CARB, .275, 2.3);
        Food carbFood4 = new Food(context.getString(R.string.whole_grain_cereals), Constants.FOOD_CARB, .77, 3.33);
        Food carbFood5 = new Food(context.getString(R.string.baked_potatoes), Constants.FOOD_CARB, .21, .93);
        Food carbFood6 = new Food(context.getString(R.string.red_kidney_beans), Constants.FOOD_CARB, .22, 1.24);
        Food carbFood7 = new Food(context.getString(R.string.whole_wheat_pasta), Constants.FOOD_CARB, .27, 1.24);
        Food carbFood8 = new Food(context.getString(R.string.apple), Constants.FOOD_CARB, .247, .905);
        Food carbFood9 = new Food(context.getString(R.string.banana), Constants.FOOD_CARB, .198, .775);
        Food carbFood10 = new Food(context.getString(R.string.orange), Constants.FOOD_CARB, .178, .65);
        Food carbFood11 = new Food(context.getString(R.string.quinoa), Constants.FOOD_CARB, .21, 1.2);
        Food carbFood12 = new Food(context.getString(R.string.whole_bulgur), Constants.FOOD_CARB, .19, .83);
        if (Food.carbFoods.isEmpty()) {
            Food.carbFoods.add(carbFood);
            Food.carbFoods.add(carbFood1);
            Food.carbFoods.add(carbFood2);
            Food.carbFoods.add(carbFood3);
            Food.carbFoods.add(carbFood4);
            Food.carbFoods.add(carbFood5);
            Food.carbFoods.add(carbFood6);
            Food.carbFoods.add(carbFood7);
            Food.carbFoods.add(carbFood8);
            Food.carbFoods.add(carbFood9);
            Food.carbFoods.add(carbFood10);
            Food.carbFoods.add(carbFood11);
            Food.carbFoods.add(carbFood12);
        }
        //---------- Fat foods
        Food fatFood = new Food(context.getString(R.string.olive_oil), Constants.FOOD_FAT, 1, 40);
        Food fatFood1 = new Food(context.getString(R.string.almonds), Constants.FOOD_FAT, .6, 7.4);
        Food fatFood2 = new Food(context.getString(R.string.peanut_butter), Constants.FOOD_FAT, .5, 94);
        Food fatFood3 = new Food(context.getString(R.string.flaxseed_oil), Constants.FOOD_FAT, 1, 40);
        Food fatFood4 = new Food(context.getString(R.string.peanuts), Constants.FOOD_FAT, .6, 7.04);
        Food fatFood5 = new Food(context.getString(R.string.avocado), Constants.FOOD_FAT, .15, 1.6);
        if (Food.fatFoods.isEmpty()) {
            Food.fatFoods.add(fatFood);
            Food.fatFoods.add(fatFood1);
            Food.fatFoods.add(fatFood2);
            Food.fatFoods.add(fatFood3);
            Food.fatFoods.add(fatFood4);
            Food.fatFoods.add(fatFood5);
        }
        //---------- Fiber foods
        Food fiberFood = new Food(context.getString(R.string.green_beans), Constants.FOOD_FIBER, 0, 0);
        Food fiberFood1 = new Food(context.getString(R.string.green_peas), Constants.FOOD_FIBER, 0, 0);
        Food fiberFood2 = new Food(context.getString(R.string.broccoli), Constants.FOOD_FIBER, 0, 0);
        Food fiberFood3 = new Food(context.getString(R.string.cauliflower), Constants.FOOD_FIBER, 0, 0);
        Food fiberFood4 = new Food(context.getString(R.string.cucumber), Constants.FOOD_FIBER, 0, 0);
        Food fiberFood5 = new Food(context.getString(R.string.zucchini), Constants.FOOD_FIBER, 0, 0);
        Food fiberFood6 = new Food(context.getString(R.string.spinach), Constants.FOOD_FIBER, 0, 0);
        Food fiberFood7 = new Food(context.getString(R.string.green_salad), Constants.FOOD_FIBER, 0, 0);
        Food fiberFood8 = new Food(context.getString(R.string.eggplant), Constants.FOOD_FIBER, 0, 0);
        if (Food.fiberFoods.isEmpty()) {
            Food.fiberFoods.add(fiberFood);
            Food.fiberFoods.add(fiberFood1);
            Food.fiberFoods.add(fiberFood2);
            Food.fiberFoods.add(fiberFood3);
            Food.fiberFoods.add(fiberFood4);
            Food.fiberFoods.add(fiberFood5);
            Food.fiberFoods.add(fiberFood6);
            Food.fiberFoods.add(fiberFood7);
            Food.fiberFoods.add(fiberFood8);
        }
    }
}
