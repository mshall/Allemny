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
import com.spark.submitbutton.SubmitButton;
import com.util.Validator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.cv)
    CardView cardView;
    @BindView(R.id.tvLoginMessage)
    TextView tvMessage;
    @BindView(R.id.etLoginEmail)
    EditText etEmail;
    @BindView(R.id.etLoginPassword)
    EditText etPassword;
    @BindView(R.id.bLogin)
    SubmitButton bLogin;
    @BindView(R.id.tvLoginDontHaveAccount)
    TextView tvDontHaveAccount;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setWindowAnimations(Animation.ZORDER_NORMAL);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        tvDontHaveAccount.setOnClickListener(this);
        bLogin.setOnClickListener(this);
        userDAO = new UserDAO(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLoginDontHaveAccount:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.bLogin:
                ArrayList<EditText> formFields = new ArrayList<>();
                formFields.add(etEmail);
                formFields.add(etPassword);
                boolean isFormValid = new Validator().isValidForm(this, formFields);
                if (isFormValid) {//Valid form fields
                    boolean isLoginSuccess = userDAO.loginUser(etEmail.getText().toString().trim(), etPassword.getText().toString());
                    if (isLoginSuccess) {//Valid credentials
                        Snackbar.make(cardView, getString(R.string.welcome_back) + etEmail.getText().toString(), Snackbar.LENGTH_LONG).show();
                        tvMessage.setText(getString(R.string.login_success));
                        tvMessage.setTextColor(ContextCompat.getColor(this, R.color.teal_background));
                    } else {//Not valid credentials
                        Snackbar.make(cardView, getString(R.string.wrong_credenials), Snackbar.LENGTH_LONG).show();
                        tvMessage.setText(getString(R.string.wrong_credenials));
                        tvMessage.setTextColor(ContextCompat.getColor(this, R.color.red_background));
                    }
                } else {//Not a valid form
                    Snackbar.make(cardView, getString(R.string.all_fields_required), Snackbar.LENGTH_LONG).show();
                    tvMessage.setText(getString(R.string.all_fields_required));
                    tvMessage.setTextColor(ContextCompat.getColor(this, R.color.red_background));
                }
                break;
        }
    }
}
