package com.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allemny.R;
import com.constants.Constants;
import com.database.dao.MealDAO;
import com.database.dao.PlanDAO;
import com.pojo.Meal;
import com.pojo.Plan;
import com.util.SharedPreferencesUtils;

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
    PlanDAO planDAO;
    MealDAO mealDAO;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        planDAO = new PlanDAO(getContext());
        mealDAO = new MealDAO(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_plans, container, false);
        ButterKnife.bind(this, view);
        sectionAdapter = new SectionedRecyclerViewAdapter();

        initializeViews();

        return view;
    }

    private void initializeViews() {
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


    @Override
    public void onResume() {
        super.onResume();

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null)
                activity.getSupportActionBar().setTitle(R.string.my_plans);
        }
    }

    private List<Meal> getPlanMeals(int planNumber) {
        List<Meal> meals = new ArrayList<>();
        meals = mealDAO.getPlanMeals(planNumber);
        return meals;
    }

    class ExpandablePlansSection extends StatelessSection {

        String title;
        List<Meal> list;
        boolean expanded = true;

        public ExpandablePlansSection(String title, List<Meal> list) {
            super(R.layout.section_header, R.layout.section_item);

            this.title = title;
            this.list = list;
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
            Meal meal = list.get(position);

            itemHolder.tvMealNumber.setText(position+"");
            itemHolder.tvProtein.setText(meal.getProteinFoodName());
            itemHolder.tvCarb.setText(meal.getCarbFoodName());
            itemHolder.tvFats.setText(meal.getFatFoodName());
            itemHolder.tvFibers.setText(meal.getFiberFoodName());
            //-----
            itemHolder.tvProteinGrams.setText(meal.getProteinGrams() + "");
            itemHolder.tvCarbGrams.setText(meal.getCarbGrams() + "");
            itemHolder.tvFatsGrams.setText(meal.getFatGrams() + "");
            itemHolder.tvFibersGrams.setText(meal.getFiberFoodName());
            //-----
            itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), String.format("Clicked on position #%s of Section %s", sectionAdapter.getSectionPosition(itemHolder.getAdapterPosition()), title), Toast.LENGTH_SHORT).show();
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
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {

        final View rootView;
        @BindView(R.id.tvTitle)
        TextView tvTitle;
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

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            rootView = view;
        }
    }
}
