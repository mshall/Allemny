package com.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.allemny.R;
import com.database.dao.UserDAO;
import com.pojo.User;
import com.spark.submitbutton.SubmitButton;
import com.util.Validator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.cv)
    CardView cardView;
    @BindView(R.id.etRegisterFirstName)
    EditText etFirstName;
    @BindView(R.id.etRegisterLastName)
    EditText etLastName;
    @BindView(R.id.etRegisterEmail)
    EditText etEmail;
    @BindView(R.id.etRegisterPassword)
    EditText etPassword;
    @BindView(R.id.bRegister)
    SubmitButton bRegister;
    @BindView(R.id.tvRegisterAlreadyHaveAccount)
    TextView tvHaveAccount;

    @BindView(R.id.tvRegisterMessage)
    TextView tvMessage;
    UserDAO userDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setWindowAnimations(Animation.ZORDER_NORMAL);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        bRegister.setOnClickListener(this);
        tvHaveAccount.setOnClickListener(this);
        userDAO = new UserDAO(this);
//        bRegister.set

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bRegister:
                ArrayList<EditText> formFields = new ArrayList<>();
                formFields.add(etFirstName);
                formFields.add(etLastName);
                formFields.add(etEmail);
                formFields.add(etPassword);
                boolean isValidToRegister = new Validator().isValidForm(this, formFields);
                if (isValidToRegister) {//Now all fields are valid
                    boolean isEmailExist = userDAO.isUserExist(etEmail.getText().toString());
                    if (isEmailExist) {//Email exists on the system
                        tvMessage.setText(getString(R.string.email_already_exist));
                    } else {//Now add the user
                        User user = new User();
                        user.setFirstName(etFirstName.getText().toString());
                        user.setLastName(etLastName.getText().toString());
                        user.setEmail(etEmail.getText().toString().trim());
                        user.setPassword(etPassword.getText().toString());
                        userDAO.addUser(user);
                        isEmailExist = userDAO.isUserExist(etEmail.getText().toString().trim());
                        if (isEmailExist) {//User is now added to the database
                            tvMessage.setText(getString(R.string.sucess_adding_user));
                            tvMessage.setTextColor(ContextCompat.getColor(this, R.color.teal_background));
                            Snackbar.make(cardView, getString(R.string.sucess_adding_user), Snackbar.LENGTH_LONG).show();
                        } else {//Couldn't add user to the database
                            tvMessage.setText(getString(R.string.error_adding_user));
                            tvMessage.setTextColor(ContextCompat.getColor(this, R.color.red_background));
                            Snackbar.make(cardView, getString(R.string.error_adding_user), Snackbar.LENGTH_LONG).show();
                        }
                    }
                } else {
                    tvMessage.setText(getString(R.string.all_fields_required));
                    tvMessage.setTextColor(ContextCompat.getColor(this, R.color.red_background));
                    Snackbar.make(cardView, getString(R.string.error_adding_user), Snackbar.LENGTH_LONG).show();
                }
                break;
            case R.id.tvRegisterAlreadyHaveAccount:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

}
