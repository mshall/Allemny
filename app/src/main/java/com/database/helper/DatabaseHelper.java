package com.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.constants.Constants;

/**
 * Created by KHoloud on 4/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //-------------- Create Users table ---------------------------------------
        db.execSQL("Create table " + Constants.USERS_TABLE + "(" + Constants.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                Constants.FIRST_NAME + " varchar(250)," +
                Constants.LAST_NAME + " varchar(250), " +
                Constants.EMAIL + " varchar(250), " +
                Constants.PASSWORD + " varchar(250)" + ")");
        //-------------- Create Food table ---------------------------------------
        db.execSQL("Create table " + Constants.FOOD_TABLE + "(" + Constants.FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                Constants.FOOD_NAME + " varchar(250), " +
                Constants.FOOD_TYPE + " Int," +
                Constants.FOOD_CALORIES + " Double" + ")");
        //-------------- Create Weight table ---------------------------------------
        db.execSQL("Create table " + Constants.WEIGHT_TABLE + "(" + Constants.WEIGHT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                Constants.WEIGHT + " Doublef, " +
                Constants.DATE + " varchar(250)," +
                Constants.USER_IMAGE + " BLOB," +
                Constants.EMAIL + " varchar(250)" + ")");

        //-------------- Create Meal table ---------------------------------------
        db.execSQL("Create table " + Constants.MEALS_TABLE + "(" + Constants.MEAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                Constants.EMAIL + " varchar(250), " +
                Constants.PROTEIN_FOOD_NAME + " varchar(250), " +
                Constants.CARB_FOOD_NAME + " varchar(250), " +
                Constants.FAT_FOOD_NAME + " varchar(250), " +
                Constants.FIBER_FOOD_NAME + " varchar(250), " +
                Constants.PROTEIN_GRAMS + " Double," +
                Constants.CARB_GRAMS + " Double," +
                Constants.FAT_GRAMS + " Double," +
                Constants.FIBER_GRAMS + " Double," +
                Constants.PLAN_NUMBER + " Int" + ")");
        //-------------- Create Plans table ---------------------------------------
        db.execSQL("Create table " + Constants.PLANS_TABLE + "(" + Constants.PLAN_NUMBER + " INTEGER PRIMARY KEY NOT NULL, " +
                Constants.EMAIL + " varchar(250)" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }
}
