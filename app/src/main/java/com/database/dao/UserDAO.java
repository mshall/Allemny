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
import com.pojo.User;

public class UserDAO {

    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public UserDAO(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    //----------------------------------------------------------------
    //  Add User
    //----------------------------------------------------------------
    public void addUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.FIRST_NAME, user.getFirstName());
        contentValues.put(Constants.LAST_NAME, user.getLastName());
        contentValues.put(Constants.EMAIL, user.getEmail());
        contentValues.put(Constants.PASSWORD, user.getPassword());
        database.insert(Constants.USERS_TABLE, null, contentValues);
    }

    //----------------------------------------------------------------
    //  Get user details given the userId
    //----------------------------------------------------------------
    public User getUserByEmail(String email) {
        User user = new User();
        Cursor c;
        String query = "Select " + Constants.FIRST_NAME + " , " +
                Constants.LAST_NAME + " , " +
                Constants.EMAIL + " , " +
                Constants.PASSWORD +
                " from " + Constants.USERS_TABLE +
                " where " + Constants.EMAIL + "='" + email + "'";

        c = database.rawQuery(query, null);
        if (c != null) {
            if (c.moveToFirst()) {
                user.setFirstName(c.getString(c.getColumnIndex(Constants.FIRST_NAME)));
                user.setLastName(c.getString(c.getColumnIndex(Constants.LAST_NAME)));
                user.setEmail(c.getString(c.getColumnIndex(Constants.EMAIL)));
                user.setPassword(c.getString(c.getColumnIndex(Constants.PASSWORD)));
            }
        } else {
            return null;
        }
        return user;
    }

    //----------------------------------------------------------------
    //  Check if the user exists
    //----------------------------------------------------------------
    public boolean isUserExist(String email) {
        Cursor c;
        String query = "Select " + Constants.FIRST_NAME + " , " +
                Constants.LAST_NAME + " , " +
                Constants.EMAIL + " , " +
                Constants.PASSWORD +
                " from " + Constants.USERS_TABLE +
                " where " + Constants.EMAIL + "='" + email + "'";

        c = database.rawQuery(query, null);
        if (c.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    //----------------------------------------------------------------
    //  Login user
    //----------------------------------------------------------------
    public boolean loginUser(String email, String password) {
        Cursor c;
        String query = "Select " + Constants.FIRST_NAME + " , " +
                Constants.LAST_NAME + " , " +
                Constants.EMAIL + " , " +
                Constants.PASSWORD +
                " from " + Constants.USERS_TABLE +
                " where " + Constants.EMAIL + "='" + email + "' AND " +
                Constants.PASSWORD + "='" + password + "'";

        c = database.rawQuery(query, null);
        if (c.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    //----------------------------------------------------------------
    //  Update user
    //----------------------------------------------------------------
    public void updateUser(User user) {
        ContentValues contentValues = new ContentValues();
        // contentValues.put(databaseHelper.medicineId,medicine.getMedicineId());
        contentValues.put(Constants.FIRST_NAME, user.getFirstName());
        contentValues.put(Constants.LAST_NAME, user.getLastName());
        contentValues.put(Constants.EMAIL, user.getEmail());
        contentValues.put(Constants.PASSWORD, user.getPassword());

        database.update(Constants.USERS_TABLE, contentValues, Constants.EMAIL + "='" + user.getEmail() + "'", null);
    }

    public boolean deleteUser(String email) {
        return database.delete(Constants.USERS_TABLE, Constants.EMAIL + "='" + email + "'", null) > 0;
    }

    //----------------------------------------------------------------
    //  Delete all users
    //----------------------------------------------------------------
    public boolean deleteAllUsers() {
        return database.delete(Constants.USERS_TABLE, null, null) > 0;
    }


}
