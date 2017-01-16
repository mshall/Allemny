package com.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.constants.Constants;
import com.google.gson.Gson;
import com.pojo.User;

/**
 * Created by elsaidel on 1/16/2017.
 */

public class SharedPreferencesUtils {
    //----------------------------------------------------------------------------------------------
    //Save boolean value to shared preferences
    //----------------------------------------------------------------------------------------------
    public static void saveBooleanToSharedPreferences(Context context, String key, boolean bool) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, bool);
        editor.apply();
    }

    //----------------------------------------------------------------------------------------------
    //Get boolean value from shared preferences
    //----------------------------------------------------------------------------------------------
    public static boolean getBooleanFromSharedPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        boolean bool = preferences.getBoolean(key, false);
        return bool;
    }

    //----------------------------------------------------------------------------------------------
    //Save String value to shared preferences
    //----------------------------------------------------------------------------------------------
    public static void saveStringToSharedPreferences(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //----------------------------------------------------------------------------------------------
    //Get String value from shared preferences
    //----------------------------------------------------------------------------------------------
    public static String getStringFromSharedPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        String value = preferences.getString(key, "");
        return value;
    }

    //----------------------------------------------------------------------------------------------
    //Save long value to shared preferences
    //----------------------------------------------------------------------------------------------
    public static void saveLongToSharedPreferences(Context context, String key, long number) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, number);
        editor.apply();
    }

    //----------------------------------------------------------------------------------------------
    //Get long value from shared preferences
    //----------------------------------------------------------------------------------------------
    public static long getLongFromSharedPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        long number = preferences.getLong(key, 0);
        return number;
    }


    //----------------------------------------------------------------------------------------------
    //Save int value to shared preferences
    //----------------------------------------------------------------------------------------------
    public static void saveIntToSharedPreferences(Context context, String key, int number) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, number);
        editor.apply();
    }


    //----------------------------------------------------------------------------------------------
    //Get int value from shared preferences
    //----------------------------------------------------------------------------------------------
    public static int getIntFromSharedPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        int number = preferences.getInt(key, 0);
        return number;
    }

    //----------------------------------------------------------------------------------------------
    //Get User data from shared preferences
    //----------------------------------------------------------------------------------------------
    public static User getUserDataFromSharedPreferences(Context context) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(Constants.USER, " ");
        return gson.fromJson(json, User.class);
    }

    //----------------------------------------------------------------------------------------------
    //Get User data from shared preferences
    //----------------------------------------------------------------------------------------------
    public static void saveUserDataToSharedPreferences(Context context, User user) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(Constants.USER, gson.toJson(user)).commit();
    }
}
