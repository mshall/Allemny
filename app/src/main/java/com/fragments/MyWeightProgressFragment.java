package com.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.dialogs.FlickableWeightProgressDetailsDialog;
import com.gaurav.cdsrecyclerview.CdsRecyclerView;
import com.pojo.Weight;
import com.tkurimura.flickabledialog.FlickableDialog;
import com.util.FragmentUtils;
import com.util.ImageUtils;
import com.util.SharedPreferencesUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWeightProgressFragment extends Fragment implements IWeightHistoryCommunicator {
    public static String TAG = "MyWeightProgressFragment";
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
        handleWeightProgressListClick();
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
//        rvWeightHistory.enableItemDrag();
//        rvWeightHistory.enableItemSwipe();
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

    public void handleWeightProgressListClick() {
        rvWeightHistory.setItemClickListener(new CdsRecyclerView.ItemClickListener() {
            @Override
            public void onItemClick(final int position) {
                //Toast.makeText(getActivity(), "You weight" +adapter.getItem(position).getWeight(), Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(getContext())
                        .setTitle(getString(R.string.warning))
                        .setMessage(getString(R.string.select_option))
                        .setPositiveButton(R.string.view, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with delete
//                                    Toast.makeText(getContext(),"Plan number: "+planNumber,Toast.LENGTH_LONG ).show();
                                FlickableWeightProgressDetailsDialog flickablePremiumAppealDialog = FlickableWeightProgressDetailsDialog.newInstance(MyWeightProgressFragment.this, ImageUtils.getImage(adapter.getItem(position).getUserImage()));
                                flickablePremiumAppealDialog.show(getChildFragmentManager(), FlickableDialog.class.getSimpleName());

                            }
                        })
                        .setNeutralButton(getString(R.string.delete), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                weightDAO.deleteSpecificWeight(adapter.getItem(position).getWeightId());
                                //new FragmentUtils(getActivity()).popFragmentFromBackStack(MyWeightProgressFragment.TAG);
                                new FragmentUtils(getActivity()).replaceFragment(R.id.content_home, new MyWeightProgressFragment(), MyWeightProgressFragment.TAG);
//                                Toast.makeText(getActivity(), "You weight Id " + adapter.getItem(position).getWeightId(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)

                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

        rvWeightHistory.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
    }
}
