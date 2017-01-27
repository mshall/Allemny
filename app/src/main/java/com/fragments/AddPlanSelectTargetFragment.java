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

import net.soulwolf.widget.materialradio.MaterialRadioButton;
import net.soulwolf.widget.materialradio.MaterialRadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddPlanSelectTargetFragment extends Fragment implements View.OnClickListener {
    public static String tag = "AddPlanSelectTargetFragment";
    @BindView(R.id.mrgTarget)
    MaterialRadioGroup materialRadioGroup;
    @BindView(R.id.rbCut)
    MaterialRadioButton rbCut;
    @BindView(R.id.rbMaintain)
    MaterialRadioButton rbMaintain;
    @BindView(R.id.rbBulk)
    MaterialRadioButton rbBulk;
    @BindView(R.id.llNavigationNext)
    LinearLayout llNavigationNext;
    @BindView(R.id.llNavigationBack)
    LinearLayout llNavigationBack;
    @BindView(R.id.stepsView)
    StepsView stepsView;
    View view;
    double weight;
    int gender, target;

    public AddPlanSelectTargetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_plan_select_target, container, false);
        ButterKnife.bind(this, view);
        Utils.hideKeyboard(getContext());
        initializeViews();

        processBundleData(getArguments());
        return view;
    }

    private void initializeViews() {
        llNavigationBack.setOnClickListener(this);
        llNavigationNext.setOnClickListener(this);
        stepsView.setLabels(getResources().getStringArray(R.array.steps))
                .setBarColorIndicator(getContext().getResources().getColor(R.color.material_blue_grey_800))
                .setProgressColorIndicator(getContext().getResources().getColor(R.color.primary))
                .setLabelColorIndicator(getContext().getResources().getColor(R.color.teal_background))
                .setCompletedPosition(2)
                .drawView();
    }

    private void processBundleData(Bundle bundle) {
        weight = bundle.getDouble(Constants.WEIGHT);
        gender = bundle.getInt(Constants.GENDER);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llNavigationNext:
                processTargetSelection();
                AddPlanSelectBodyTypeFragment fragment = new AddPlanSelectBodyTypeFragment();
                Bundle bundle = new Bundle();
                bundle.putInt(Constants.GENDER, gender);
                bundle.putDouble(Constants.WEIGHT, weight);
                bundle.putInt(Constants.TARGET, target);
                fragment.setArguments(bundle);
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home,fragment,AddPlanSelectBodyTypeFragment.tag);
                break;
            case R.id.llNavigationBack:
                new FragmentUtils(getActivity()).popFragmentFromBackStack(AddPlanSelectTargetFragment.tag);
                break;
        }
    }

    private void processTargetSelection() {
        int selectedId = materialRadioGroup.getCheckedRadioButtonId();
        switch (selectedId) {
            case R.id.rbBulk:
                target = Constants.NUTRITION_BULK;
                break;
            case R.id.rbCut:
                target = Constants.NUTRITION_CUT;
                break;
            case R.id.rbMaintain:
                target = Constants.NUTRITION_MAINTAIN;
                break;
        }
    }
}
