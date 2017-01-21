package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allemny.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlanSelectNumberOfMealsFragment extends Fragment {


    public static String tag = "AddPlanSelectNumberOfMealsFragment";

    public AddPlanSelectNumberOfMealsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_plan_select_number_of_meals, container, false);
    }

}
