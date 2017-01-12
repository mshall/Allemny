package com.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.constants.Constants;

/**
 * Created by KHoloud on 4/12/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String tableName = "Medicine";
    public final static  String databaseName = "MedicalReminder";
    public final static  int databaseVersion = 1;
    public String medicineId = "medicineId";
    public String medicineName  = "medicineName";
    public String medicineImage = "medicineImage";
    public String medicineDose = "medicineDose";
    public String medicineType = "medicineType";
    public String medicineDate = "medicineDate";
    public String medicineTime = "medicineTime";
    public String medicineRepition = "medicineRepition";

	public String medicineIdForAlarm = "medicineIdForAlarm";
    public String medicineTimeForAlarm = "medicineTimeForAlarm";
    public String medicineDateForAlarm = "medicineDateForAlarm";
    public String snoozeTable = "snoozeTable";
	
    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //-------------- Create Users table
        db.execSQL("Create table "+Constants.USERS_TABLE+"(" + Constants.USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                Constants.FIRST_NAME + " varchar(60),"+
                Constants.LAST_NAME + " blob, "+
                Constants.EMAIL + " Int, "+
                Constants.PASSWORD + " varchar(45), "+
                medicineDate + " varchar(45), "+
                medicineTime + " varchar(45), "+
                medicineRepition + " Int"+")");
		
		db.execSQL("Create table "+snoozeTable+"(" + medicineIdForAlarm +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                medicineDateForAlarm + " varchar(45), "+
                medicineRepition + " Int,"+
                medicineTimeForAlarm + " varchar(45)"+")");
		
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }
}
