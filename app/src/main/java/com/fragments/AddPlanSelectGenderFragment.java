package com.fragments;


import android.graphics.Bitmap;
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
import com.util.ImageLoader;
import com.util.ImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlanSelectGenderFragment extends Fragment implements View.OnClickListener {
    public static String TAG = "AddPlanSelectGenderFragment";
    @BindView(R.id.ivFragmentAddPlanSelectGenderFemale)
    ImageView ivFemale;
    @BindView(R.id.ivFragmentAddPlanSelectGenderMale)
    ImageView ivMale;
    @BindView(R.id.llNavigationNext)
    LinearLayout llNavigationNext;
    @BindView(R.id.stepsView)
    StepsView stepsView;
    View view;

    boolean isGenderMaleSelected;

    public AddPlanSelectGenderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_plan_select_gender, container, false);
        ButterKnife.bind(this, view);
        initializeViews();

        return view;
    }

    private void initializeViews() {
        ivFemale.setOnClickListener(this);
        ivMale.setOnClickListener(this);
        llNavigationNext.setOnClickListener(this);
        stepsView.setLabels(getResources().getStringArray(R.array.steps))
                .setBarColorIndicator(getContext().getResources().getColor(R.color.material_blue_grey_800))
                .setProgressColorIndicator(getContext().getResources().getColor(R.color.primary))
                .setLabelColorIndicator(getContext().getResources().getColor(R.color.teal_background))
                .setCompletedPosition(0)
                .drawView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivFragmentAddPlanSelectGenderFemale:
                //Reset the male image first
                ImageLoader.setImageDrawable(getContext(), ivMale, R.drawable.male);
                //Now set the female glowed image
                Bitmap glowedFemaleBitmap = ImageUtils.addImageGlow(getContext(), R.drawable.female);
                ImageLoader.setImageDrawable(getContext(), ivFemale, glowedFemaleBitmap);
                //---------------------------
                isGenderMaleSelected = false;
                break;
            case R.id.ivFragmentAddPlanSelectGenderMale:
                ImageLoader.setImageDrawable(getContext(), ivFemale, R.drawable.female);
                //Now set the male glowed image
                Bitmap glowedMaleBitmap = ImageUtils.addImageGlow(getContext(), R.drawable.male);
                ImageLoader.setImageDrawable(getContext(), ivMale, glowedMaleBitmap);
                //---------------------------
                isGenderMaleSelected = true;
                break;
            case R.id.llNavigationNext:
                AddPlanAddWeightFragment fragment = new AddPlanAddWeightFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.GENDER, (isGenderMaleSelected) ? Constants.GENDER_MALE : Constants.GENDER_FEMALE);
                fragment.setArguments(bundle);
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, fragment, AddPlanAddWeightFragment.tag);
                break;
        }
    }
}
