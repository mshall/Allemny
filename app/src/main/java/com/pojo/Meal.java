package com.pojo;

import java.io.Serializable;

/**
 * Created by elsaidel on 1/14/2017.
 */

public class Meal implements Serializable{
    double proteinGrams, carbGrams, fatGrams, fiberGrams, actualProteinGrams, actualCarbsGrams, actualFatsGrams;
    int planNumber, mealId;
    String email, proteinFoodName, carbFoodName, fatFoodName, fiberFoodName;

    public Meal() {
    }

    public Meal(double proteinGrams, double carbGrams, double fatGrams, int fiberGrams, int planNumber, String email, String proteinFoodName, String carbFoodName, String fatFoodName, String fiberFoodName) {
        this.proteinGrams = proteinGrams;
        this.carbGrams = carbGrams;
        this.fatGrams = fatGrams;
        this.fiberGrams = fiberGrams;
        this.planNumber = planNumber;
        this.email = email;
        this.proteinFoodName = proteinFoodName;
        this.carbFoodName = carbFoodName;
        this.fatFoodName = fatFoodName;
        this.fiberFoodName = fiberFoodName;
    }

    public double getProteinGrams() {
        return proteinGrams;
    }

    public void setProteinGrams(double proteinGrams) {
        this.proteinGrams = proteinGrams;
    }

    public double getCarbGrams() {
        return carbGrams;
    }

    public void setCarbGrams(double carbGrams) {
        this.carbGrams = carbGrams;
    }

    public double getFatGrams() {
        return fatGrams;
    }

    public void setFatGrams(double fatGrams) {
        this.fatGrams = fatGrams;
    }

    public int getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(int planNumber) {
        this.planNumber = planNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProteinFoodName() {
        return proteinFoodName;
    }

    public void setProteinFoodName(String proteinFoodName) {
        this.proteinFoodName = proteinFoodName;
    }

    public double getFiberGrams() {
        return fiberGrams;
    }

    public void setFiberGrams(double fiberGrams) {
        this.fiberGrams = fiberGrams;
    }

    public String getCarbFoodName() {
        return carbFoodName;
    }

    public void setCarbFoodName(String carbFoodName) {
        this.carbFoodName = carbFoodName;
    }

    public String getFatFoodName() {
        return fatFoodName;
    }

    public void setFatFoodName(String fatFoodName) {
        this.fatFoodName = fatFoodName;
    }

    public String getFiberFoodName() {
        return fiberFoodName;
    }

    public void setFiberFoodName(String fiberFoodName) {
        this.fiberFoodName = fiberFoodName;
    }

    public double getActualProteinGrams() {
        return actualProteinGrams;
    }

    public void setActualProteinGrams(double actualProteinGrams) {
        this.actualProteinGrams = actualProteinGrams;
    }

    public double getActualCarbsGrams() {
        return actualCarbsGrams;
    }

    public void setActualCarbsGrams(double actualCarbsGrams) {
        this.actualCarbsGrams = actualCarbsGrams;
    }

    public double getActualFatsGrams() {
        return actualFatsGrams;
    }

    public void setActualFatsGrams(double actualFatsGrams) {
        this.actualFatsGrams = actualFatsGrams;
    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        this.mealId = mealId;
    }
}
