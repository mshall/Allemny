package com.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.allemny.R;
import com.spark.submitbutton.SubmitButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.bRegister)
    SubmitButton bRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
//        bRegister.set

    }
}
