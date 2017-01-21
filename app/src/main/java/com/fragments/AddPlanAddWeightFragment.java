package com.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
public class AddPlanAddWeightFragment extends Fragment implements View.OnClickListener {
    public static String tag = "AddPlanAddWeightFragment";
    @BindView(R.id.etFragmentAddPlanAddWeight)
    EditText etWeight;
    @BindView(R.id.llNavigationNext)
    LinearLayout llNavigationNext;
    @BindView(R.id.llNavigationBack)
    LinearLayout llNavigationBack;
    @BindView(R.id.stepsView)
    StepsView stepsView;
    View view;
    int gender;
    double weight;

    public AddPlanAddWeightFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_plan_add_weight, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        gender = bundle.getInt(Constants.GENDER);
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
                .setCompletedPosition(1)
                .drawView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llNavigationNext:
                if (validateWeightEditText()) {
                    AddPlanSelectTargetFragment fragment = new AddPlanSelectTargetFragment();
                    Bundle bundle = new Bundle();
                    bundle.putDouble(Constants.WEIGHT, Double.parseDouble(etWeight.getText().toString().trim()));
                    bundle.putInt(Constants.GENDER, gender);
                    fragment.setArguments(bundle);
                    new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, fragment, AddPlanSelectTargetFragment.tag);
                }
                break;
            case R.id.llNavigationBack:
                new FragmentUtils(getActivity()).popFragmentFromBackStack(AddPlanAddWeightFragment.tag);
                break;
        }
    }

    private boolean validateWeightEditText() {
        String weightString = etWeight.getText().toString().trim();
        if (weightString.isEmpty()) {
            etWeight.setError(getString(R.string.required));
            return false;
        } else {
            weight = Double.parseDouble(weightString);
            return true;
        }
    }
}
