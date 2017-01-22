package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.allemny.R;
import com.anton46.stepsview.StepsView;
import com.constants.Constants;
import com.util.FragmentUtils;
import com.util.Utils;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.sbNumberOfMeals)
    DiscreteSeekBar sbNumberOfMeals;
    View view;
    int gender, target, numberOfMeals = 3;
    int bodyType;
    double weight;

    public AddPlanSelectNumberOfMealsFragment() {
        // Required empty public constructor
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
        stepsView.setLabels(getResources().getStringArray(R.array.steps))
                .setBarColorIndicator(getContext().getResources().getColor(R.color.material_blue_grey_800))
                .setProgressColorIndicator(getContext().getResources().getColor(R.color.primary))
                .setLabelColorIndicator(getContext().getResources().getColor(R.color.teal_background))
                .setCompletedPosition(4)
                .drawView();
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
                numberOfMeals = sbNumberOfMeals.getProgress();
                AddPlanResultFragment fragment = new AddPlanResultFragment();
                fragment.setArguments(fillBundleWithData());
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, fragment, AddPlanResultFragment.TAG);
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
}
