package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.allemny.R;
import com.constants.Constants;
import com.stephentuso.welcome.WelcomeScreenHelper;
import com.util.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {

    WelcomeScreenHelper welcomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeScreen = new WelcomeScreenHelper(this, WelcomeActivity.class);
        boolean isFirstTimeRun = SharedPreferencesUtils.getBooleanFromSharedPreferences(MainActivity.this, Constants.IS_FIRST_TIME_RUN);
        Log.e("is first time", isFirstTimeRun + " Is 1st Time?");
        if (isFirstTimeRun) {
            welcomeScreen.show(savedInstanceState);
        } else {
//            Toast.makeText(this, "already appeared before", Toast.LENGTH_LONG).show();

        }
        /*findViewById(R.id.button_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                welcomeScreen.forceShow();
            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == WelcomeScreenHelper.DEFAULT_WELCOME_SCREEN_REQUEST) {
            String welcomeKey = data.getStringExtra(WelcomeActivity.WELCOME_SCREEN_KEY);

            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), welcomeKey + " completed", Toast.LENGTH_SHORT).show();
                SharedPreferencesUtils.saveBooleanToSharedPreferences(MainActivity.this, Constants.IS_FIRST_TIME_RUN, false);
            } else {
                Toast.makeText(getApplicationContext(), welcomeKey + " canceled", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }
}
