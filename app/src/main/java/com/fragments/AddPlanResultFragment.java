package com.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allemny.R;
import com.constants.Constants;
import com.database.dao.PlanDAO;
import com.pojo.Meal;
import com.util.SharedPreferencesUtils;
import com.util.Utils;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlanResultFragment extends Fragment {

    public static final String TAG = "AddPlanResultFragment";
    View view;
    int gender, target, numberOfMeals = 3;
    int bodyType;
    PlanDAO planDAO;
    double weight, caloriesNumber, carbGrams, proteinGrams, fatGrams, carbGramsPerMeal, proteinGramsPerMeal, fatGramsPerMeal;

    public AddPlanResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        planDAO = new PlanDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_plan_result, container, false);
        ButterKnife.bind(this, view);
        Utils.hideKeyboard(getContext());
        processBundleData(getArguments());
        initializeViews();
        generateNutritionPlan();

        return view;
    }


    private void initializeViews() {
    }

    private void processBundleData(Bundle bundle) {
        gender = bundle.getInt(Constants.GENDER);
        weight = bundle.getDouble(Constants.WEIGHT);
        target = bundle.getInt(Constants.TARGET);
        bodyType = bundle.getInt(Constants.BODY);
        numberOfMeals = bundle.getInt(Constants.NUMBER_OF_MEALS);
    }

    //------------------------------------------------------------
    // This method is to generate the nutrition plan
    //------------------------------------------------------------
    private void generateNutritionPlan() {
        int planNumber = planDAO.getLastPlanNumber() + 1;
        planDAO.addPlan(planNumber, SharedPreferencesUtils.getStringFromSharedPreferences(getContext(), Constants.EMAIL));
        switch (target) {
            case Constants.NUTRITION_CUT: //X11
                caloriesNumber = weight * 11;
                if (bodyType == Constants.BODY_ECTOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        carbGrams = (0.25 * caloriesNumber) / 4;
                        fatGrams = (0.40 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------ start generating meals
//                        Random r = new Random();
//                        int Low = 10;
//                        int High = 100;
//                        int Result = r.nextInt(High-Low) + Low;
                        for (int i = 0; i < numberOfMeals; i++) {
                            if (i == 0) {//this is the first meal
                                Meal meal = new Meal();
                                meal.setEmail(SharedPreferencesUtils.getStringFromSharedPreferences(getContext(),Constants.EMAIL));
                                meal.setPlanNumber(planNumber);

                                //To generate number of grams that should be added to the database
                                // proteinGramsPerMeal/foodValue(The one within each food)

//                                meal.setProteinGrams(proteinGramsPerMeal);
//                                meal.setCarbGrams(carbGramsPerMeal);
//                                meal.setFatGrams(c.getInt(c.getColumnIndex(Constants.FAT_GRAMS)));
//                                meal.setProteinFoodName(c.getString(c.getColumnIndex(Constants.PROTEIN_FOOD_NAME)));
//                                meal.setCarbFoodName(c.getString(c.getColumnIndex(Constants.CARB_FOOD_NAME)));
//                                meal.setFatFoodName(c.getString(c.getColumnIndex(Constants.FAT_FOOD_NAME)));
//                                meal.setFiberFoodName(c.getString(c.getColumnIndex(Constants.FIBER_FOOD_NAME)));

                            }
                        }
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        carbGrams = (0.20 * caloriesNumber) / 4;
                        fatGrams = (0.45 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    }

                } else if (bodyType == Constants.BODY_MESOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.20 * caloriesNumber) / 4;
                        fatGrams = (0.35 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.15 * caloriesNumber) / 4;
                        fatGrams = (0.40 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    }
                } else if (bodyType == Constants.BODY_ENDOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.15 * caloriesNumber) / 4;
                        fatGrams = (0.40 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.10 * caloriesNumber) / 4;
                        fatGrams = (0.45 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    }
                }
                break;
            //------------------------ Maintain target --------------
            case Constants.NUTRITION_MAINTAIN:// X 15
                caloriesNumber = weight * 15;
                if (bodyType == Constants.BODY_ECTOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        proteinGrams = (0.25 * caloriesNumber) / 4;
                        carbGrams = (0.50 * caloriesNumber) / 4;
                        fatGrams = (0.25 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.25 * caloriesNumber) / 4;
                        carbGrams = (0.45 * caloriesNumber) / 4;
                        fatGrams = (0.30 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    }

                } else if (bodyType == Constants.BODY_MESOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        carbGrams = (0.35 * caloriesNumber) / 4;
                        fatGrams = (0.30 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        carbGrams = (0.30 * caloriesNumber) / 4;
                        fatGrams = (0.35 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    }
                } else if (bodyType == Constants.BODY_ENDOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.25 * caloriesNumber) / 4;
                        fatGrams = (0.30 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.20 * caloriesNumber) / 4;
                        fatGrams = (0.35 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    }
                }
                break;
            case Constants.NUTRITION_BULK:// X20
                caloriesNumber = weight * 20;
                if (bodyType == Constants.BODY_ECTOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        carbGrams = (0.60 * caloriesNumber) / 4;
                        proteinGrams = (0.25 * caloriesNumber) / 4;
                        fatGrams = (0.15 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        carbGrams = (0.55 * caloriesNumber) / 4;
                        proteinGrams = (0.25 * caloriesNumber) / 4;
                        fatGrams = (0.20 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    }

                } else if (bodyType == Constants.BODY_MESOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        carbGrams = (0.45 * caloriesNumber) / 4;
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        fatGrams = (0.20 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        carbGrams = (0.40 * caloriesNumber) / 4;
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        fatGrams = (0.25 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    }
                } else if (bodyType == Constants.BODY_ENDOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        carbGrams = (0.35 * caloriesNumber) / 4;
                        proteinGrams = (0.40 * caloriesNumber) / 4;
                        fatGrams = (0.25 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        carbGrams = (0.30 * caloriesNumber) / 4;
                        proteinGrams = (0.40 * caloriesNumber) / 4;
                        fatGrams = (0.30 * caloriesNumber) / 8;
                        //-------------------------------------------
                        carbGramsPerMeal = carbGrams / numberOfMeals;
                        proteinGramsPerMeal = proteinGrams / numberOfMeals;
                        fatGramsPerMeal = fatGrams / numberOfMeals;
                        //------
                    }
                }
                break;

        }
    }

}
