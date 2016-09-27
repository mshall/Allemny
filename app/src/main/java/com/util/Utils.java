package com.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.activities.SplashActivity;
import com.constants.Constants;

import java.util.Locale;

/**
 * Created by Shall on 29/6/2016.
 */
public class Utils extends Activity {

    //----------------------------------------------------------------------------------------------
    //Save boolean value to shared preferences
    //----------------------------------------------------------------------------------------------
    public static void saveBooleanToPreferences(Context context, String key, boolean bool) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, bool);
        editor.apply();
    }

    //----------------------------------------------------------------------------------------------
    //Get boolean value from shared preferences
    //----------------------------------------------------------------------------------------------
    public static boolean getBooleanFromPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        boolean bool = preferences.getBoolean(key, false);
        return bool;
    }

    //----------------------------------------------------------------------------------------------
    //Save String value to shared preferences
    //----------------------------------------------------------------------------------------------
    public static void saveStringToPreferences(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //----------------------------------------------------------------------------------------------
    //Get String value from shared preferences
    //----------------------------------------------------------------------------------------------
    public static String getStringFromPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        String value = preferences.getString(key, "");
        return value;
    }

    //----------------------------------------------------------------------------------------------
    //Save long value to shared preferences
    //----------------------------------------------------------------------------------------------
    public static void saveLongToPreferences(Context context, String key, long number) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, number);
        editor.apply();
    }

    //----------------------------------------------------------------------------------------------
    //Get long value from shared preferences
    //----------------------------------------------------------------------------------------------
    public static long getLongFromPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        long number = preferences.getLong(key, 0);
        return number;
    }


    //----------------------------------------------------------------------------------------------
    //Save int value to shared preferences
    //----------------------------------------------------------------------------------------------
    public static void saveIntToPreferences(Context context, String key, int number) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, number);
        editor.apply();
    }


    //----------------------------------------------------------------------------------------------
    //Get int value from shared preferences
    //----------------------------------------------------------------------------------------------
    public static int getIntFromPreferences(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHRED_PREFS_KEY, Context.MODE_PRIVATE);
        int number = preferences.getInt(key, 0);
        return number;
    }


    //----------------------------------------------------------------------------------------------
    //Set Language locale
    //----------------------------------------------------------------------------------------------
    public static void setLocale(Context context, String lang) {

        Locale locale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(context, SplashActivity.class);
        context.startActivity(refresh);
    }

}
