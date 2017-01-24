package com.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allemny.R;
import com.constants.Constants;
import com.database.dao.MealDAO;
import com.database.dao.PlanDAO;
import com.util.MealUtils;
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
    MealDAO mealDAO;
    double weight, caloriesNumber, carbGrams, proteinGrams, fatGrams, carbGramsPerMeal, proteinGramsPerMeal, fatGramsPerMeal;

    public AddPlanResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        planDAO = new PlanDAO(getContext());
        mealDAO = new MealDAO(getContext());
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
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        carbGrams = (0.20 * caloriesNumber) / 4;
                        fatGrams = (0.45 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                    }

                } else if (bodyType == Constants.BODY_MESOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.20 * caloriesNumber) / 4;
                        fatGrams = (0.35 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.15 * caloriesNumber) / 4;
                        fatGrams = (0.40 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                    }
                } else if (bodyType == Constants.BODY_ENDOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.15 * caloriesNumber) / 4;
                        fatGrams = (0.40 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.10 * caloriesNumber) / 4;
                        fatGrams = (0.45 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
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
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.25 * caloriesNumber) / 4;
                        carbGrams = (0.45 * caloriesNumber) / 4;
                        fatGrams = (0.30 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    }

                } else if (bodyType == Constants.BODY_MESOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        carbGrams = (0.35 * caloriesNumber) / 4;
                        fatGrams = (0.30 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        carbGrams = (0.30 * caloriesNumber) / 4;
                        fatGrams = (0.35 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    }
                } else if (bodyType == Constants.BODY_ENDOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.25 * caloriesNumber) / 4;
                        fatGrams = (0.30 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        proteinGrams = (0.45 * caloriesNumber) / 4;
                        carbGrams = (0.20 * caloriesNumber) / 4;
                        fatGrams = (0.35 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
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
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        carbGrams = (0.55 * caloriesNumber) / 4;
                        proteinGrams = (0.25 * caloriesNumber) / 4;
                        fatGrams = (0.20 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    }

                } else if (bodyType == Constants.BODY_MESOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        carbGrams = (0.45 * caloriesNumber) / 4;
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        fatGrams = (0.20 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        carbGrams = (0.40 * caloriesNumber) / 4;
                        proteinGrams = (0.35 * caloriesNumber) / 4;
                        fatGrams = (0.25 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    }
                } else if (bodyType == Constants.BODY_ENDOMERPH) {
                    if (gender == Constants.GENDER_MALE) {
                        carbGrams = (0.35 * caloriesNumber) / 4;
                        proteinGrams = (0.40 * caloriesNumber) / 4;
                        fatGrams = (0.25 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    } else if (gender == Constants.GENDER_FEMALE) {
                        carbGrams = (0.30 * caloriesNumber) / 4;
                        proteinGrams = (0.40 * caloriesNumber) / 4;
                        fatGrams = (0.30 * caloriesNumber) / 8;
                        //-------------------------------------------
                        MealUtils.processAndAddMeal(planNumber, numberOfMeals, proteinGrams, carbGrams, fatGrams, mealDAO, getContext());
                        //------
                    }
                }
                break;

        }
    }

}
