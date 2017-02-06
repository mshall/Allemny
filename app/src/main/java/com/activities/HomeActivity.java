package com.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.allemny.R;
import com.database.dao.FoodDAO;
import com.fragments.AboutFragment;
import com.fragments.AddPlanSelectGenderFragment;
import com.fragments.MainFragment;
import com.fragments.MyPlansFragment;
import com.fragments.MyWeightProgressFragment;
import com.fragments.UpdateMyWeightFragment;
import com.util.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //-------------------Handling the replacement of fragments------------
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.content_home, new MainFragment()).commit();


        //------------------- Initaite the foods to be loaded -------------------------
        new FoodDAO(this).initializeAllFoods();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        FragmentUtils fragmentUtils = new FragmentUtils(this);
        if (id == R.id.nav_home) {
            Fragment fragment = fragmentUtils.getFragmentFromBackStack(MainFragment.TAG);
            if (fragment == null) {
                fragmentUtils.navigateToFragment(R.id.content_home, new MainFragment(), MainFragment.TAG);
            } else {
                if (fragment instanceof MainFragment) {
                    fragmentUtils.navigateToFragment(R.id.content_home, (MainFragment) fragment, MainFragment.TAG);
                }
            }

        } else if (id == R.id.nav_add_nutrition_plan) {
            fragmentUtils.navigateToFragment(R.id.content_home, new AddPlanSelectGenderFragment(), AddPlanSelectGenderFragment.TAG);
        } else if (id == R.id.nav_view_my_nutrition_plans) {
            fragmentUtils.navigateToFragment(R.id.content_home, new MyPlansFragment(), MyPlansFragment.TAG);

        } else if (id == R.id.nav_update_my_weight) {
            fragmentUtils.navigateToFragment(R.id.content_home, new UpdateMyWeightFragment(), UpdateMyWeightFragment.TAG);

        } else if (id == R.id.nav_my_weight_progress) {
            fragmentUtils.navigateToFragment(R.id.content_home, new MyWeightProgressFragment(), MyWeightProgressFragment.TAG);
        } else if (id == R.id.nav_about_app) {
            fragmentUtils.navigateToFragment(R.id.content_home, new AboutFragment(), AboutFragment.TAG);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
