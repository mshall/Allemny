package com.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.constants.Constants;
import com.database.helper.DatabaseHelper;
import com.pojo.Weight;

import java.util.ArrayList;

/**
 * Created by elsaidel on 1/20/2017.
 */

public class WeightDAO {
    Context context;
    DatabaseHelper databaseHelper;
    SQLiteDatabase database;

    public WeightDAO(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    //----------------------------------------------------------------
    //  Add weight of a specific user
    //----------------------------------------------------------------
    public void addUserWeight(Weight weight) {
        ContentValues contentValues = new ContentValues();
        // contentValues.put(dbHelper.medicineId,medicine.getMedicineId());
        contentValues.put(Constants.WEIGHT, weight.getWeight());
        contentValues.put(Constants.DATE, weight.getDate());
        contentValues.put(Constants.USER_IMAGE, weight.getUserImage());
        contentValues.put(Constants.EMAIL, weight.getEmail());
        database.insert(Constants.WEIGHT_TABLE, null, contentValues);
    }

    //----------------------------------------------------------------
    //  check is specific user weight exists
    //----------------------------------------------------------------
    public boolean isUserWeightExist(String email, String date) {
        Cursor c;
        String query = "Select *" +
                " from " + Constants.WEIGHT_TABLE +
                " where " + Constants.EMAIL + "='" + email + "' AND " +
                Constants.DATE + "='" + date + "'";

        c = database.rawQuery(query, null);
        if (c.moveToNext()) {
            return true;
        } else {
            return false;
        }
    }

    //----------------------------------------------------------------
    //  Get all weights related to specific user
    //----------------------------------------------------------------
    public ArrayList<Weight> getUserWeightsHistory(String email) {
        ArrayList<Weight> weights = new ArrayList<>();

        Cursor c;
        String query = "Select * " +
                " from " + Constants.WEIGHT_TABLE +
                " where " + Constants.EMAIL + "='" + email + "'";

        c = database.rawQuery(query, null);
        while (c.moveToNext()) {
            Weight weight = new Weight();
            weight.setWeightId(c.getInt(c.getColumnIndex(Constants.WEIGHT_ID)));
            weight.setWeight(c.getDouble(c.getColumnIndex(Constants.WEIGHT)));
            weight.setDate(c.getString(c.getColumnIndex(Constants.DATE)));
            weight.setUserImage(c.getBlob(c.getColumnIndex(Constants.USER_IMAGE)));
            weight.setEmail(c.getString(c.getColumnIndex(Constants.EMAIL)));
            weights.add(weight);
        }
        return weights;
    }

    //----------------------------------------------------------------
    //  clear user weights history
    //----------------------------------------------------------------
    public void clearUserWeightsHistory() {
        database.delete(Constants.WEIGHT_TABLE, null, null);
    }

    //----------------------------------------------------------------
    //  Delete specific history
    //----------------------------------------------------------------
    public boolean deleteSpecificWeight(int weightId) {
        return database.delete(Constants.WEIGHT_TABLE, Constants.WEIGHT_ID + "=" + weightId, null) > 0;
    }

}
