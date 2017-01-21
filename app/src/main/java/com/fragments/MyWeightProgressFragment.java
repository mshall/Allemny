package com.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adapters.WeightHistoryAdapter;
import com.allemny.R;
import com.communicators.IWeightHistoryCommunicator;
import com.constants.Constants;
import com.database.dao.WeightDAO;
import com.gaurav.cdsrecyclerview.CdsRecyclerView;
import com.pojo.Weight;
import com.util.SharedPreferencesUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWeightProgressFragment extends Fragment implements IWeightHistoryCommunicator {
    public static String tag = "MyWeightProgressFragment";
    ArrayList<Weight> weights;
    @BindView(R.id.rvFragmentMyWeightProgress)
    CdsRecyclerView rvWeightHistory;
    WeightDAO weightDAO;
    WeightHistoryAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public MyWeightProgressFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weights = new ArrayList<>();
        weightDAO = new WeightDAO(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_weight_progress, container, false);
        ButterKnife.bind(this, view);
        initializeViews();
        showWeightProgress();
        return view;
    }

    private void initializeViews() {
        rvWeightHistory.setHasFixedSize(true);
        rvWeightHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    private void showWeightProgress() {
        weights.clear();
        String userEmail = SharedPreferencesUtils.getStringFromSharedPreferences(getContext(), Constants.EMAIL);
        weights.addAll(weightDAO.getUserWeightsHistory(userEmail));
        adapter = new WeightHistoryAdapter(getContext(), this, weights);
        rvWeightHistory.setAdapter(adapter);
        rvWeightHistory.enableItemDrag();
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();
        showWeightProgress();
    }

    @Override
    public ArrayList<Weight> getUserWeightsHistory() {
        return weights;
    }
}
