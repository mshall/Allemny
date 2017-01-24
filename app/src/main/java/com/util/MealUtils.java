package com.util;

import android.content.Context;

import com.allemny.R;
import com.constants.Constants;
import com.database.dao.MealDAO;
import com.pojo.Food;
import com.pojo.Meal;

import static com.pojo.Food.proteinFoods;

/**
 * Created by elsaidel on 1/24/2017.
 */

public class MealUtils {


    public static void processAndAddMeal(int planNumber, int numberOfMeals, double proteinGrams, double carbGrams, double fatGrams, MealDAO mealDAO, Context context) {
        double carbGramsPerMeal = carbGrams / (numberOfMeals - 1);
        double proteinGramsPerMeal = proteinGrams / numberOfMeals;
        double fatGramsPerMeal = fatGrams / numberOfMeals;
        //------ start generating meals
//                        Random r = new Random();
//                        int Low = 10;
//                        int High = 100;
//                        int Result = r.nextInt(High-Low) + Low;
        for (int i = 0; i < numberOfMeals; i++) {
            if (i == 0) {//this is the first meal
                Meal meal = new Meal();
                meal.setEmail(SharedPreferencesUtils.getStringFromSharedPreferences(context, Constants.EMAIL));
                meal.setPlanNumber(planNumber);

                //Now get the random foods
                int proteinRandomIndex = Utils.getRandomNumber(0, proteinFoods.size());
                int carbRandomIndex = Utils.getRandomNumber(0, Food.carbFoods.size());
                int fatRandomIndex = Utils.getRandomNumber(0, Food.fatFoods.size());

                Food proteinFood = proteinFoods.get(proteinRandomIndex);

                Food fatFood = Food.fatFoods.get(fatRandomIndex);

                //Oats or toast must be in the carb food
                Food carbFood = new Food();
                String carbFoodName = Food.carbFoods.get(carbRandomIndex).getFoodName();
                while (!carbFoodName.equalsIgnoreCase(context.getString(R.string.oats)) && !carbFoodName.equalsIgnoreCase(context.getString(R.string.whole_wheat_bread))) {
                    carbRandomIndex = Utils.getRandomNumber(0, Food.carbFoods.size());
                    carbFoodName = Food.carbFoods.get(carbRandomIndex).getFoodName();
                    if (carbFoodName.equalsIgnoreCase(context.getString(R.string.oats)) || carbFoodName.equalsIgnoreCase(context.getString(R.string.whole_wheat_bread))) {
                        carbFood = Food.carbFoods.get(carbRandomIndex);
                        break;
                    }
                }
                double foodProteinGrams = proteinGramsPerMeal / proteinFood.getFoodValue();
                double foodCarbGrams = carbGramsPerMeal / carbFood.getFoodValue();
                double foodFatGrams = fatGramsPerMeal / fatFood.getFoodValue();

                meal.setProteinFoodName(proteinFood.getFoodName());
                meal.setCarbFoodName(carbFood.getFoodName());
                meal.setFatFoodName(fatFood.getFoodName());

                meal.setProteinGrams(foodProteinGrams);
                meal.setCarbGrams(foodCarbGrams);
                meal.setFatGrams(foodFatGrams);

                mealDAO.addMeal(meal);

            } else if (i == numberOfMeals - 1) {//Don't add carbs to this last meal
                Meal meal = new Meal();
                meal.setEmail(SharedPreferencesUtils.getStringFromSharedPreferences(context, Constants.EMAIL));
                meal.setPlanNumber(planNumber);

                //Now get the random foods
                int proteinRandomIndex = Utils.getRandomNumber(0, proteinFoods.size());
                int fatRandomIndex = Utils.getRandomNumber(0, Food.fatFoods.size());
                int fiberRandomIndex = 7;
                Food proteinFood = new Food();
                String proteinFoodName = Food.proteinFoods.get(proteinRandomIndex).getFoodName();
                // To make sure the tuna or cottage cheese are listed in the last meal
                while (!proteinFoodName.equalsIgnoreCase(context.getString(R.string.tuna_in_water)) && !proteinFoodName.equalsIgnoreCase(context.getString(R.string.cottage_cheese))) {
                    proteinRandomIndex = Utils.getRandomNumber(0, proteinFoods.size());
                    proteinFoodName = Food.proteinFoods.get(proteinRandomIndex).getFoodName();
                    if (proteinFoodName.equalsIgnoreCase(context.getString(R.string.tuna_in_water)) || proteinFoodName.equalsIgnoreCase(context.getString(R.string.cottage_cheese))) {
                        proteinFood = Food.proteinFoods.get(proteinRandomIndex);
                        break;
                    }
                }

                Food fatFood = Food.fatFoods.get(fatRandomIndex);
                Food fiberFood = Food.fiberFoods.get(fiberRandomIndex);

                double foodProteinGrams = proteinGramsPerMeal / proteinFood.getFoodValue();
                double foodFatGrams = fatGramsPerMeal / fatFood.getFoodValue();
                double foodFiberGrams = 100;

                meal.setProteinFoodName(proteinFood.getFoodName());
                meal.setFatFoodName(fatFood.getFoodName());
                meal.setFiberFoodName(fiberFood.getFoodName());

                meal.setProteinGrams(foodProteinGrams);
                meal.setFatGrams(foodFatGrams);
                meal.setFiberGrams(foodFiberGrams);

                mealDAO.addMeal(meal);

            } else {//2nd : pre-last meal
                Meal meal = new Meal();
                meal.setEmail(SharedPreferencesUtils.getStringFromSharedPreferences(context, Constants.EMAIL));
                meal.setPlanNumber(planNumber);

                //Now get the random foods
                int proteinRandomIndex = Utils.getRandomNumber(0, proteinFoods.size());
                int carbRandomIndex = Utils.getRandomNumber(0, Food.carbFoods.size());
                int fatRandomIndex = Utils.getRandomNumber(0, Food.fatFoods.size());
                int fiberRandomIndex = Utils.getRandomNumber(0, Food.fiberFoods.size());

                Food proteinFood = proteinFoods.get(proteinRandomIndex);
                Food carbFood = Food.carbFoods.get(carbRandomIndex);
                Food fatFood = Food.fatFoods.get(fatRandomIndex);
                Food fiberFood = Food.fiberFoods.get(fiberRandomIndex);

                double foodProteinGrams = proteinGramsPerMeal / proteinFood.getFoodValue();
                double foodCarbGrams = carbGramsPerMeal / carbFood.getFoodValue();
                double foodFatGrams = fatGramsPerMeal / fatFood.getFoodValue();
                double foodFiberGrams = 100;

                meal.setProteinFoodName(proteinFood.getFoodName());
                meal.setCarbFoodName(carbFood.getFoodName());
                meal.setFatFoodName(fatFood.getFoodName());
                meal.setFiberFoodName(fiberFood.getFoodName());

                meal.setProteinGrams(foodProteinGrams);
                meal.setCarbGrams(foodCarbGrams);
                meal.setFatGrams(foodFatGrams);
                meal.setFiberGrams(foodFiberGrams);

                mealDAO.addMeal(meal);
            }
        }
    }
}
