package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.allemny.R;
import com.anton46.stepsview.StepsView;
import com.constants.Constants;
import com.util.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlanSelectBodyTypeFragment extends Fragment implements View.OnClickListener {

    public static String tag = "AddPlanSelectBodyTypeFragment";
    @BindView(R.id.stepsView)
    StepsView stepsView;
    @BindView(R.id.llNavigationBack)
    LinearLayout llNavigationBack;
    @BindView(R.id.llNavigationNext)
    LinearLayout llNavigationNext;
    @BindView(R.id.ivFragmentAddPlanSelectBodyTypeEndoBody)
    ImageView ivEndoBodyType;
    @BindView(R.id.ivFragmentAddPlanSelectBodyTypeEctoBody)
    ImageView ivEctoBodyType;
    @BindView(R.id.ivFragmentAddPlanSelectBodyTypeMesoBody)
    ImageView ivMesoBodyType;
    View view;
    int selectedBodyType;
    int gender, target, bodyType;
    double weight;

    public AddPlanSelectBodyTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_plan_select_body_type, container, false);
        ButterKnife.bind(this, view);
        processBundleData(getArguments());
        initializeViews();
        return view;
    }

    private void initializeViews() {
        llNavigationBack.setOnClickListener(this);
        llNavigationNext.setOnClickListener(this);
        ivEctoBodyType.setOnClickListener(this);
        ivEndoBodyType.setOnClickListener(this);
        ivMesoBodyType.setOnClickListener(this);
        stepsView.setLabels(getResources().getStringArray(R.array.steps))
                .setBarColorIndicator(getContext().getResources().getColor(R.color.material_blue_grey_800))
                .setProgressColorIndicator(getContext().getResources().getColor(R.color.primary))
                .setLabelColorIndicator(getContext().getResources().getColor(R.color.teal_background))
                .setCompletedPosition(3)
                .drawView();
    }

    private void processBundleData(Bundle bundle) {
        gender = bundle.getInt(Constants.GENDER);
        weight = bundle.getDouble(Constants.WEIGHT);
        target = bundle.getInt(Constants.TARGET);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llNavigationBack:
                new FragmentUtils(getActivity()).popFragmentFromBackStack(AddPlanSelectBodyTypeFragment.tag);
                break;
            case R.id.llNavigationNext:
                AddPlanSelectNumberOfMealsFragment fragment = new AddPlanSelectNumberOfMealsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.GENDER, gender);
                bundle.putDouble(Constants.WEIGHT, weight);
                bundle.putInt(Constants.TARGET, target);
                bundle.putInt(Constants.BODY, bodyType);
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, fragment, AddPlanSelectNumberOfMealsFragment.tag);
                break;
            case R.id.ivFragmentAddPlanSelectBodyTypeMesoBody:

                break;
            case R.id.ivFragmentAddPlanSelectBodyTypeEndoBody:

                break;
            case R.id.ivFragmentAddPlanSelectBodyTypeEctoBody:

                break;
        }
    }
}
