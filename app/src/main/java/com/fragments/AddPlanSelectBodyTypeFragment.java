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
    int gender, target;
    int bodyType = Constants.BODY_ENDOMERPH;
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

        if (gender == Constants.GENDER_FEMALE) {
            Bitmap femaleEctomerph = ImageUtils.addImageGlow(getContext(), R.drawable.female_ectomorph);
            Bitmap femaleMesomerph = ImageUtils.addImageGlow(getContext(), R.drawable.female_mesomorph);
            Bitmap femaleEndomerph = ImageUtils.addImageGlow(getContext(), R.drawable.female_endomorph);
            ImageLoader.setImageDrawable(getContext(), ivEctoBodyType, femaleEctomerph);
            ImageLoader.setImageDrawable(getContext(), ivEndoBodyType, femaleEndomerph);
            ImageLoader.setImageDrawable(getContext(), ivMesoBodyType, femaleMesomerph);
        }
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
                fragment.setArguments(bundle);
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, fragment, AddPlanSelectNumberOfMealsFragment.tag);
                break;
            case R.id.ivFragmentAddPlanSelectBodyTypeMesoBody:
                bodyType = Constants.BODY_MESOMERPH;
                //Call the image clicks handler method
                handleBodyTypesClick(gender, bodyType);
                break;
            case R.id.ivFragmentAddPlanSelectBodyTypeEndoBody:
                bodyType = Constants.BODY_ENDOMERPH;
                //Call the image clicks handler method
                handleBodyTypesClick(gender, bodyType);
                break;
            case R.id.ivFragmentAddPlanSelectBodyTypeEctoBody:
                bodyType = Constants.BODY_ECTOMERPH;
                //Call the image clicks handler method
                handleBodyTypesClick(gender, bodyType);
                break;
        }
    }

    public void handleBodyTypesClick(int gender, int bodyType) {
        switch (bodyType) {
            case Constants.BODY_MESOMERPH:
                if (gender == Constants.GENDER_MALE) {//This is a male
                    //Reset the other body types images first
                    ImageLoader.setImageDrawable(getContext(), ivEctoBodyType, R.drawable.male_ectomorph);
                    ImageLoader.setImageDrawable(getContext(), ivEndoBodyType, R.drawable.male_endomorph);
                    //Now set the mesomerpth glowed image
                    Bitmap glowedMaleBitmap = ImageUtils.addImageGlow(getContext(), R.drawable.male_mesomorph);
                    ImageLoader.setImageDrawable(getContext(), ivMesoBodyType, glowedMaleBitmap);
                    //---------------------------
                } else {//This is a female
                    //Reset the other body types images first
                    ImageLoader.setImageDrawable(getContext(), ivEctoBodyType, R.drawable.female_ectomorph);
                    ImageLoader.setImageDrawable(getContext(), ivEndoBodyType, R.drawable.female_endomorph);
                    //Now set the mesomerpth glowed image
                    Bitmap glowedFemaleBitmap = ImageUtils.addImageGlow(getContext(), R.drawable.female_mesomorph);
                    ImageLoader.setImageDrawable(getContext(), ivMesoBodyType, glowedFemaleBitmap);
                }
                break;
            case Constants.BODY_ECTOMERPH:
                if (gender == Constants.GENDER_MALE) {//This is a male
                    //Reset the other body types images first
                    ImageLoader.setImageDrawable(getContext(), ivMesoBodyType, R.drawable.male_mesomorph);
                    ImageLoader.setImageDrawable(getContext(), ivEndoBodyType, R.drawable.male_endomorph);
                    //Now set the ectomerpth glowed image
                    Bitmap glowedMaleBitmap = ImageUtils.addImageGlow(getContext(), R.drawable.male_ectomorph);
                    ImageLoader.setImageDrawable(getContext(), ivEctoBodyType, glowedMaleBitmap);
                    //---------------------------
                } else {//This is a female
                    //Reset the other body types images first
                    ImageLoader.setImageDrawable(getContext(), ivMesoBodyType, R.drawable.female_mesomorph);
                    ImageLoader.setImageDrawable(getContext(), ivEndoBodyType, R.drawable.female_endomorph);
                    //Now set the ectomerpth glowed image
                    Bitmap glowedFemaleBitmap = ImageUtils.addImageGlow(getContext(), R.drawable.female_ectomorph);
                    ImageLoader.setImageDrawable(getContext(), ivEctoBodyType, glowedFemaleBitmap);
                }
                break;
            case Constants.BODY_ENDOMERPH:
                if (gender == Constants.GENDER_MALE) {//This is a male
                    //Reset the other body types images first
                    ImageLoader.setImageDrawable(getContext(), ivMesoBodyType, R.drawable.male_mesomorph);
                    ImageLoader.setImageDrawable(getContext(), ivEctoBodyType, R.drawable.male_ectomorph);
                    //Now set the ectomerpth glowed image
                    Bitmap glowedMaleBitmap = ImageUtils.addImageGlow(getContext(), R.drawable.male_endomorph);
                    ImageLoader.setImageDrawable(getContext(), ivEndoBodyType, glowedMaleBitmap);
                    //---------------------------
                } else {//This is a female
                    //Reset the other body types images first
                    ImageLoader.setImageDrawable(getContext(), ivMesoBodyType, R.drawable.female_mesomorph);
                    ImageLoader.setImageDrawable(getContext(), ivEctoBodyType, R.drawable.female_ectomorph);
                    //Now set the endomerpth glowed image
                    Bitmap glowedMaleBitmap = ImageUtils.addImageGlow(getContext(), R.drawable.female_endomorph);
                    ImageLoader.setImageDrawable(getContext(), ivEndoBodyType, glowedMaleBitmap);
                }
                break;
        }

    }
}
