package com.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.allemny.R;
import com.database.dao.MealDAO;
import com.fragments.MyPlansFragment;
import com.pojo.Food;
import com.pojo.Meal;
import com.tkurimura.flickabledialog.FlickableDialog;
import com.util.FragmentUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by elsaidel on 1/27/2017.
 */

public class FlickableUpdateMealDialog extends FlickableDialog implements View.OnClickListener {

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

    public static FlickableUpdateMealDialog newInstance(Fragment fragment, Meal meal) {
        myPlansFragment = (MyPlansFragment) fragment;
        FlickableUpdateMealDialog.meal = meal;
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


        FlickableUpdateMealDialog flackablePremiumAppealDialog = new FlickableUpdateMealDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(LAYOUT_RESOURCE_KEY, R.layout.dialog_update_meal);
        flackablePremiumAppealDialog.setTargetFragment(fragment, 0);
        flackablePremiumAppealDialog.setArguments(bundle);

        return flackablePremiumAppealDialog;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        mealDAO = new MealDAO(getContext());
        ButterKnife.bind(this, dialog);
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


        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bDialogUpdateMealCancel:
                dismiss();
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
                dismiss();
                break;
            case R.id.ivDialogUpdateMealClose:
                dismiss();
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
                int carbsIndexDown = proteins.indexOf(carbsName);
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