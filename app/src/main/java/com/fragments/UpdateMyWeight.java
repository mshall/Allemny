package com.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.allemny.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateMyWeight extends Fragment {
    @BindView(R.id.etFragmentUpdateMyWeightDatePicker)
    DatePickerEditText etDatePicker;
    @BindView(R.id.etFragmentUpdateMyWeight)
    EditText etMyWeight;
    @BindView(R.id.ivFragmentUpdateMyWeightUserImage)
    ImageView ivUserImage;
    @BindView(R.id.bFragmentUpdateMyWeight)
    Button bUpdateMyWeight;
    View view;

    public UpdateMyWeight() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_my_weight, container, false);
        ButterKnife.bind(this, view);
        initializeViews();
        return view;
    }

    private void initializeViews() {
        etDatePicker.setManager(getActivity().getSupportFragmentManager());
        etDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDatePicker.clearFocus();
            }
        });

    }

    @OnClick(R.id.bFragmentUpdateMyWeight)
    private void handleUpdateButtonClick() {
        double weight = Double.parseDouble(etMyWeight.getText().toString());
        String date = etDatePicker.getText().toString();
        //Now get the user image
    }

    private void handleUserImagePicker() {

    }

}
