package com.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.allemny.R;
import com.constants.Constants;
import com.database.dao.MealDAO;
import com.database.dao.PlanDAO;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.pojo.Meal;
import com.pojo.Plan;
import com.util.FragmentUtils;
import com.util.SharedPreferencesUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class MyPlansFragment extends Fragment {
    public final static String TAG = "MyPlansFragment";
    SectionedRecyclerViewAdapter sectionAdapter;
    View view;
    @BindView(R.id.rvFragmentMyPlans)
    RecyclerView rvMyPlans;
    private AdView mAdView;
    PlanDAO planDAO;
    MealDAO mealDAO;
    InterstitialAd mInterstitialAd;
    @BindView(R.id.bFragmentMyPlansShowNotes)
    Button bNotes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
        planDAO = new PlanDAO(getContext());
        mealDAO = new MealDAO(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_plans, container, false);
        ButterKnife.bind(this, view);
        mInterstitialAd = new InterstitialAd(getActivity());
        mAdView = (AdView) view.findViewById(R.id.ad_view);
        sectionAdapter = new SectionedRecyclerViewAdapter();

        initializeViews();

        return view;
    }

    private void initializeViews() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.big_banner_Ad));

        AdRequest interstatialAdRequest = new AdRequest.Builder().build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(interstatialAdRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
        //-------------------------------------
        bNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*FlickableNotesDialog notesDialog = FlickableNotesDialog.newInstance(MyPlansFragment.this);
                notesDialog.show(getChildFragmentManager(), FlickableDialog.class.getSimpleName());*/
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, new AppNotesFragment(), AppNotesFragment.TAG);
            }
        });
        //-------------------------------------
        List<Plan> plans = planDAO.getUserPlans(SharedPreferencesUtils.getStringFromSharedPreferences(getContext(), Constants.EMAIL));
        for (Plan plan : plans) {
            List<Meal> meals = getPlanMeals(plan.getPlanNumber());
            if (meals.size() > 0) {
                sectionAdapter.addSection(new ExpandablePlansSection(getString(R.string.nutrition_plan), meals));
            }
        }

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvFragmentMyPlans);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionAdapter);

    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    public void refreshFragment() {
        new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, new MyPlansFragment(), MyPlansFragment.TAG);
    }

    private List<Meal> getPlanMeals(int planNumber) {
        List<Meal> meals = new ArrayList<>();
        meals = mealDAO.getPlanMeals(planNumber);
        return meals;
    }

    class ExpandablePlansSection extends StatelessSection {

        String title;
        List<Meal> list;
        boolean expanded = false;
        int planNumber;

        public ExpandablePlansSection(String title, List<Meal> list) {
            super(R.layout.section_header, R.layout.section_item);

            this.title = title;
            this.list = list;
            this.planNumber = list.get(0).getPlanNumber();
        }

        @Override
        public int getContentItemsTotal() {
            return expanded ? list.size() : 0;
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
            final ItemViewHolder itemHolder = (ItemViewHolder) holder;
            final Meal meal = list.get(position);

            itemHolder.tvMealNumber.setText((position + 1) + "");
            itemHolder.tvProtein.setText(meal.getProteinFoodName());
            itemHolder.tvCarb.setText(meal.getCarbFoodName());
            itemHolder.tvFats.setText(meal.getFatFoodName());
            itemHolder.tvFibers.setText(meal.getFiberFoodName());
            //-----
            itemHolder.tvProteinGrams.setText((int) meal.getProteinGrams() + " gr");
            itemHolder.tvCarbGrams.setText((int) meal.getCarbGrams() + " gr");
            itemHolder.tvFatsGrams.setText((int) meal.getFatGrams() + " gr");
            itemHolder.tvFibersGrams.setText((int) meal.getFiberGrams() + " gr");
            //-----
            NumberFormat currencyFormattter = new DecimalFormat("#0.00");
            /*String modifiedProteinGrams = currencyFormattter.format(meal.getActualProteinGrams()) + " gr";
            String modifiedCarbsGrams = currencyFormattter.format(meal.getActualCarbsGrams()) + " gr";
            String modifiedFatsGrams = currencyFormattter.format(meal.getActualFatsGrams()) + " gr";*/
            String modifiedProteinGrams = (int) meal.getActualProteinGrams() + " gr";
            String modifiedCarbsGrams = (int) meal.getActualCarbsGrams() + " gr";
            String modifiedFatsGrams = (int) meal.getActualFatsGrams() + " gr";
            itemHolder.tvActualProteinGrams.setText(modifiedProteinGrams);
            itemHolder.tvActualFatsGrams.setText(modifiedFatsGrams);


            if (position == 0) {//This is the first meal
                itemHolder.tvActualFibersGrams.setText("0 gr");
            } else {
                itemHolder.tvActualFibersGrams.setText("100 gr");
            }

            if (position == list.size() - 1) {//This is the last meal
                itemHolder.tvActualCarbsGrams.setText("0 gr");
            } else {
                itemHolder.tvActualCarbsGrams.setText((int) meal.getActualCarbsGrams() + " gr");
            }
            //-----

            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s", sectionAdapter.getSectionPosition(itemHolder.getAdapterPosition()), title), Toast.LENGTH_SHORT).show();
//                    FlickableUpdateMealDialog updateMealDialog = FlickableUpdateMealDialog.newInstance(MyPlansFragment.this, meal);
//                    updateMealDialog.show(getChildFragmentManager(), FlickableDialog.class.getSimpleName());
                    UpdateMealFragment fragment = new UpdateMealFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.MEAL_ID, meal);
                    fragment.setArguments(bundle);
                    new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, fragment, UpdateMealFragment.TAG);
                }
            });
            itemHolder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    new AlertDialog.Builder(getContext())
                            .setTitle(getString(R.string.error))
                            .setMessage(getString(R.string.delete_this_record))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    planDAO.deletePlan(meal.getPlanNumber());
                                    mealDAO.deleteSpecificMeals(meal.getPlanNumber());
                                    new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, new MyPlansFragment(), MyPlansFragment.TAG);
                                }
                            })
                            .setNegativeButton(R.string.cancel, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                    return false;
                }
            });
        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

            headerHolder.tvTitle.setText(title);
            headerHolder.tvSectionHeaderPlanNumber.setText(planNumber + "");
            headerHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expanded = !expanded;
                    headerHolder.imgArrow.setImageResource(
                            expanded ? R.drawable.ic_keyboard_arrow_up : R.drawable.ic_keyboard_arrow_down
                    );
                    sectionAdapter.notifyDataSetChanged();
                }
            });
            headerHolder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    new AlertDialog.Builder(getContext())
                            .setTitle(getString(R.string.warning))
                            .setMessage(getString(R.string.delete_this_record))
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
//                                    Toast.makeText(getContext(),"Plan number: "+planNumber,Toast.LENGTH_LONG ).show();
                                    planDAO.deletePlan(planNumber);
                                    mealDAO.deleteSpecificMeals(planNumber);
                                    new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, new MyPlansFragment(), MyPlansFragment.TAG);
                                }
                            })
                            .setNegativeButton(R.string.cancel, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                    return false;
                }
            });
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        final View rootView;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvSectionHeaderPlanNumber)
        TextView tvSectionHeaderPlanNumber;
        @BindView(R.id.imgArrow)
        ImageView imgArrow;


        public HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            rootView = view;
//            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
//            imgArrow = (ImageView) view.findViewById(R.id.imgArrow);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        final View rootView;

        @BindView(R.id.tvMealNumber)
        TextView tvMealNumber;
        @BindView(R.id.tvProtein)
        TextView tvProtein;
        @BindView(R.id.tvCarb)
        TextView tvCarb;
        @BindView(R.id.tvFats)
        TextView tvFats;
        @BindView(R.id.tvFibers)
        TextView tvFibers;
        //
        @BindView(R.id.tvProteinGrams)
        TextView tvProteinGrams;
        @BindView(R.id.tvCarbGrams)
        TextView tvCarbGrams;
        @BindView(R.id.tvFatsGrams)
        TextView tvFatsGrams;
        @BindView(R.id.tvFibersGrams)
        TextView tvFibersGrams;
        //
        @BindView(R.id.tvSectionItemActualCarbsGrams)
        TextView tvActualCarbsGrams;
        @BindView(R.id.tvSectionItemActualProteinGrams)
        TextView tvActualProteinGrams;
        @BindView(R.id.tvSectionItemActualFatsGrams)
        TextView tvActualFatsGrams;
        @BindView(R.id.tvSectionItemActualFibersGrams)
        TextView tvActualFibersGrams;


        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            rootView = view;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null)
                activity.getSupportActionBar().setTitle(R.string.my_plans);
        }
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

}
