package com.fragments;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.allemny.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.cvFragmentMainAddNewPlan)
    CardView cvAddNewPlan;
    @BindView(R.id.cvFragmentMainMyPlans)
    CardView cvMyPlans;
    @BindView(R.id.cvFragmentMainUpdateMyWeight)
    CardView cvUpdateMyWeight;
    @BindView(R.id.cvFragmentMainMyWeightProgress)
    CardView cvMyWeightProgress;
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
        initializeViews();
        return view;
    }

    public void initializeViews() {
        cvAddNewPlan.setOnClickListener(this);
        cvMyPlans.setOnClickListener(this);
        cvUpdateMyWeight.setOnClickListener(this);
        cvMyWeightProgress.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cvFragmentMainAddNewPlan:
                Snackbar.make(view, "You want to add a new plan", Snackbar.LENGTH_LONG).show();
                break;

            case R.id.cvFragmentMainMyPlans:
                Snackbar.make(view, "You want to show my plans", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.cvFragmentMainUpdateMyWeight:
                mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.content_home, new UpdateMyWeight()).commit();

                break;
            case R.id.cvFragmentMainMyWeightProgress:

                break;
        }
    }

    @OnClick(R.id.cvFragmentMainMyPlans)
    public void showmyPlan() {
        Toast.makeText(getActivity(), "show my plan", Toast.LENGTH_SHORT).show();
    }
}

