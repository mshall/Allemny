package com.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.allemny.R;
import com.constants.Constants;
import com.database.dao.MealDAO;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.pojo.Food;
import com.pojo.Meal;
import com.util.FragmentUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateMealFragment extends Fragment implements View.OnClickListener {
    public static String TAG = "UpdateMealFragment";
    @BindView(R.id.ivDialogUpdateMealClose)
    ImageView ivClose;
    @BindView(R.id.ivDialogUpdateMealProteinUp)
    ImageView ivProteinUp;
    @BindView(R.id.ivDialogUpdateMealProteinDown)
    ImageView ivProteinDown;
    @BindView(R.id.ivDialogUpdateMealCarbsUp)
    ImageView ivCarbsUp;
    @BindView(R.id.ivDialogUpdateMealCarbsDown)
    ImageView ivCarbsDown;
    @BindView(R.id.ivDialogUpdateMealFatsUp)
    ImageView ivFatsUp;
    @BindView(R.id.ivDialogUpdateMealFatsDown)
    ImageView ivFatsDown;
    //---------------------------------------
    @BindView(R.id.bDialogUpdateMealUpdate)
    Button bUpdate;
    @BindView(R.id.bDialogUpdateMealCancel)
    Button bCancel;
    //---------------------------------------
    @BindView(R.id.tvDialogUpdateMealProtein)
    phelat.TextView.Plus tvProtein;
    @BindView(R.id.tvDialogUpdateMealCarbs)
    phelat.TextView.Plus tvCarbs;
    @BindView(R.id.tvDialogUpdateMealFats)
    phelat.TextView.Plus tvFats;

    static ArrayList<String> proteins;
    static ArrayList<String> carbs;
    static ArrayList<String> fats;

    public static Meal meal;
    MealDAO mealDAO;

    String proteinName;
    String carbsName;
    String fatsName;
    static MyPlansFragment myPlansFragment;
    InterstitialAd mInterstitialAd;
    View view;

    public UpdateMealFragment() {
        // Required empty public constructor
    }

    //initalize basic data structures
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mealDAO = new MealDAO(getContext());
        proteins = new ArrayList<>();
        carbs = new ArrayList<>();
        fats = new ArrayList<>();

        for (Food protein : Food.proteinFoods) {
            proteins.add(protein.getFoodName());
        }
        for (Food carb : Food.carbFoods) {
            carbs.add(carb.getFoodName());
        }
        for (Food fat : Food.fatFoods) {
            fats.add(fat.getFoodName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_update_meal, container, false);
        ButterKnife.bind(this, view);
        UpdateMealFragment.meal = (Meal) getArguments().getSerializable(Constants.MEAL_ID);
        initializeViews();
        return view;
    }

    private void initializeViews() {
        initializeAds();
        proteinName = meal.getProteinFoodName();
        carbsName = meal.getCarbFoodName();
        fatsName = meal.getFatFoodName();
        //------------------------------

        tvProtein.setText(proteinName);
        tvCarbs.setText(carbsName);
        tvFats.setText(fatsName);
        //------------------------------

        ivClose.setOnClickListener(this);
        bUpdate.setOnClickListener(this);
        bCancel.setOnClickListener(this);
        //------------------------------
        ivProteinUp.setOnClickListener(this);
        ivProteinDown.setOnClickListener(this);
        ivCarbsUp.setOnClickListener(this);
        ivCarbsDown.setOnClickListener(this);
        ivFatsUp.setOnClickListener(this);
        ivFatsDown.setOnClickListener(this);
    }

    private void initializeAds() {
        mInterstitialAd = new InterstitialAd(getContext());

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.big_banner_Ad));

        AdRequest adRequest = new AdRequest.Builder().build();
       /* AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .addTestDevice("C04B1BFFB0774708339BC273F8A43708")
                .build();*/
        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }


        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
//                Toast.makeText(getContext(), "Ad is loaded!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
//                Toast.makeText(getContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
//                Toast.makeText(getContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
//                Toast.makeText(getContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
//                Toast.makeText(getContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bDialogUpdateMealCancel:
//                dismiss();
                new FragmentUtils(getActivity()).popFragmentFromBackStack(getTag());
                break;
            case R.id.bDialogUpdateMealUpdate:
                proteinName = tvProtein.getText().toString();
                carbsName = tvCarbs.getText().toString();
                fatsName = tvFats.getText().toString();
                meal.setProteinFoodName(proteinName);
                meal.setCarbFoodName(carbsName);
                meal.setFatFoodName(fatsName);
                mealDAO.updateMeal(meal);
                Toast.makeText(getContext(), getString(R.string.record_has_been_updated), Toast.LENGTH_SHORT).show();
                new FragmentUtils(getActivity()).navigateToFragment(R.id.content_home, new MyPlansFragment(), MyPlansFragment.TAG);
//                dismiss();
                new FragmentUtils(getActivity()).popFragmentFromBackStack(getTag());
                break;
            case R.id.ivDialogUpdateMealClose:
//                dismiss();
                new FragmentUtils(getActivity()).popFragmentFromBackStack(getTag());
                break;
            case R.id.ivDialogUpdateMealProteinUp:
                int proteinIndexUp = proteins.indexOf(proteinName);
                if (proteinIndexUp - 1 <= 0) {
                    proteinIndexUp = proteins.size() - 1;
                } else {
                    proteinIndexUp--;
                }
                proteinName = proteins.get(proteinIndexUp);
                tvProtein.setText(proteinName);
                break;
            case R.id.ivDialogUpdateMealProteinDown:
                int proteinIndexDown = proteins.indexOf(proteinName);
                if (proteinIndexDown + 1 >= proteins.size()) {
                    proteinIndexDown = 0;
                } else {
                    proteinIndexDown++;
                }
                proteinName = proteins.get(proteinIndexDown);
                tvProtein.setText(proteinName);
                break;
            case R.id.ivDialogUpdateMealCarbsUp:
                int carbsIndexUp = carbs.indexOf(carbsName);
                if (carbsIndexUp - 1 <= 0) {
                    carbsIndexUp = carbs.size() - 1;
                } else {
                    carbsIndexUp--;
                }
                carbsName = carbs.get(carbsIndexUp);
                tvCarbs.setText(carbsName);
                break;
            case R.id.ivDialogUpdateMealCarbsDown:
                int carbsIndexDown = carbs.indexOf(carbsName);
                if (carbsIndexDown + 1 >= carbs.size()) {
                    carbsIndexDown = 0;
                } else {
                    carbsIndexDown++;
                }
                carbsName = carbs.get(carbsIndexDown);
                tvCarbs.setText(carbsName);
                break;
            case R.id.ivDialogUpdateMealFatsUp:
                int fatsIndexUp = fats.indexOf(fatsName);
                if (fatsIndexUp - 1 <= 0) {
                    fatsIndexUp = fats.size() - 1;
                } else {
                    fatsIndexUp--;
                }
                fatsName = fats.get(fatsIndexUp);
                tvFats.setText(fatsName);
                break;
            case R.id.ivDialogUpdateMealFatsDown:
                int fatsIndexDown = fats.indexOf(fatsName);
                if (fatsIndexDown + 1 >= fats.size()) {
                    fatsIndexDown = 0;
                } else {
                    fatsIndexDown++;
                }
                fatsName = fats.get(fatsIndexDown);
                tvFats.setText(fatsName);
                break;
        }
    }
}
