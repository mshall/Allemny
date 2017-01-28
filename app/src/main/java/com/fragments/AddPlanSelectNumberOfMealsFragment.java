package com.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.allemny.R;
import com.anton46.stepsview.StepsView;
import com.anton46.stepsview.StepsViewIndicator;
import com.constants.Constants;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.BubbleThumbSeekbar;
import com.database.dao.MealDAO;
import com.database.dao.PlanDAO;
import com.util.FragmentUtils;
import com.util.MealUtils;
import com.util.SharedPreferencesUtils;
import com.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.allemny.R.array.steps;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlanSelectNumberOfMealsFragment extends Fragment implements View.OnClickListener {


    public static String tag = "AddPlanSelectNumberOfMealsFragment";
    @BindView(R.id.stepsView)
    StepsView stepsView;
    @BindView(R.id.llNavigationNext)
    LinearLayout llNavigationNext;
    @BindView(R.id.llNavigationBack)
    LinearLayout llNavigationBack;
    @BindView(R.id.btsbNumberOfMeals)
    BubbleThumbSeekbar btsbNumberOfMeals;

    @BindView(R.id.tvFragmentAddPlanSelectNumberOfMeals)
    phelat.TextView.Plus tvNumberOfMeals;
    View view;
    int gender, target, numberOfMeals = 3;
    int bodyType;
    PlanDAO planDAO;
    MealDAO mealDAO;
    double weight, caloriesNumber, carbGrams, proteinGrams, fatGrams, carbGramsPerMeal, proteinGramsPerMeal, fatGramsPerMeal;
    private StepsViewIndicator mStepsViewIndicator;

    public AddPlanSelectNumberOfMealsFragment() {
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
        view = inflater.inflate(R.layout.fragment_add_plan_select_number_of_meals, container, false);
        ButterKnife.bind(this, view);
        Utils.hideKeyboard(getContext());
        processBundleData(getArguments());
        initializeViews();

        return view;
    }

    private void initializeViews() {
        llNavigationBack.setOnClickListener(this);
        llNavigationNext.setOnClickListener(this);
        stepsView.setLabels(getResources().getStringArray(steps))
                .setBarColorIndicator(getContext().getResources().getColor(R.color.material_blue_grey_800))
                .setProgressColorIndicator(getContext().getResources().getColor(R.color.primary))
                .setLabelColorIndicator(getContext().getResources().getColor(R.color.teal_background))
                .setCompletedPosition(4)
                .drawView();
        btsbNumberOfMeals.setOnSeekbarChangeListener(new OnSeekbarChangeListener() {
            @Override
            public void valueChanged(Number value) {
                tvNumberOfMeals.setText(value + "");
                numberOfMeals = value.intValue();
            }
        });

        btsbNumberOfMeals.setOnSeekbarFinalValueListener(new OnSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number value) {
                Log.d("CRS=>", String.valueOf(value));
                numberOfMeals = value.intValue();
            }
        });
    }

    private void processBundleData(Bundle bundle) {
        gender = bundle.getInt(Constants.GENDER);
        weight = bundle.getDouble(Constants.WEIGHT);
        target = bundle.getInt(Constants.TARGET);
        bodyType = bundle.getInt(Constants.BODY);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llNavigationNext:
                generateNutritionPlan();
                MyPlansFragment fragment = new MyPlansFragment();
                //fragment.setArguments(fillBundleWithData());
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, fragment, MyPlansFragment.TAG);
                break;
            case R.id.llNavigationBack:
                new FragmentUtils(getActivity()).popFragmentFromBackStack(AddPlanSelectNumberOfMealsFragment.tag);
                break;
        }
    }

    private Bundle fillBundleWithData() {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.GENDER, gender);
        bundle.putDouble(Constants.WEIGHT, weight);
        bundle.putInt(Constants.TARGET, target);
        bundle.putInt(Constants.BODY, bodyType);
        bundle.putInt(Constants.NUMBER_OF_MEALS, numberOfMeals);
        return bundle;
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
        Snackbar.make(stepsView, getString(R.string.plan_generated), Snackbar.LENGTH_LONG).show();
    }
}
