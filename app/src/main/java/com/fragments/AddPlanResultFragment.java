package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allemny.R;
import com.constants.Constants;
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
    double weight;

    public AddPlanResultFragment() {
        // Required empty public constructor
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

    }

}
