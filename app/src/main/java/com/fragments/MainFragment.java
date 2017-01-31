package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.allemny.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.tomer.fadingtextview.FadingTextView;
import com.util.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    public static String TAG = "MainFragment";
    @BindView(R.id.cvFragmentMainAddNewPlan)
    CardView cvAddNewPlan;
    @BindView(R.id.cvFragmentMainMyPlans)
    CardView cvMyPlans;
    @BindView(R.id.cvFragmentMainUpdateMyWeight)
    CardView cvUpdateMyWeight;
    @BindView(R.id.cvFragmentMainMyWeightProgress)
    CardView cvMyWeightProgress;
    @BindView(R.id.fadingTextView)
    FadingTextView fadingTextView;

    private AdView mAdView;
    View view;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        mAdView = (AdView) view.findViewById(R.id.ad_view);
        initializeViews();
        /*String[] texts = getResources().getStringArray(R.array.welcome_words);
        fadingTextView.setTexts(texts);*/

        return view;
    }

    public void initializeViews() {
        cvAddNewPlan.setOnClickListener(this);
        cvMyPlans.setOnClickListener(this);
        cvUpdateMyWeight.setOnClickListener(this);
        cvMyWeightProgress.setOnClickListener(this);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvFragmentMainAddNewPlan:
//                startActivity(new Intent(getActivity(), PlanStepper.class));
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, new AddPlanSelectGenderFragment(), AddPlanSelectGenderFragment.TAG);
                break;

            case R.id.cvFragmentMainMyPlans:
                //startActivity(new Intent(getActivity(), AndroidDatabaseManager.class));
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, new MyPlansFragment(), MyPlansFragment.TAG);
                break;
            case R.id.cvFragmentMainUpdateMyWeight:
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, new UpdateMyWeightFragment(), UpdateMyWeightFragment.TAG);
//                mFragmentTransaction.replace(R.id.content_home, new UpdateMyWeightFragment()).commit();

                break;
            case R.id.cvFragmentMainMyWeightProgress:
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, new MyWeightProgressFragment(), MyWeightProgressFragment.TAG);
                break;
        }
    }

    @OnClick(R.id.cvFragmentMainMyPlans)
    public void showmyPlan() {
        Toast.makeText(getActivity(), "show my plan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}

