package com.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by elsaidel on 1/20/2017.
 */

public class FragmentUtils {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    FragmentActivity activity;

    public FragmentUtils(FragmentActivity activity) {
        this.activity = activity;
        mFragmentManager = activity.getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
    }

    //--------------------------------------------------------
    // Navigate from the current fragment to another one
    //--------------------------------------------------------
    public void navigateToFragment(int container, Fragment fragment, String fragmentTag) {
//        if (addToBackStack) {
//            mFragmentTransaction.addToBackStack(fragmentTag).add(R.id.content_home, fragment).commit();
//        } else {
        mFragmentTransaction.replace(container, fragment, fragmentTag).addToBackStack(fragmentTag).commit();
//        }
    }

    //--------------------------------------------------------
    // Navigate from the current fragment to another one
    //--------------------------------------------------------
    public void replaceFragment(int container, Fragment fragment, String fragmentTag) {
//        if (addToBackStack) {
//            mFragmentTransaction.addToBackStack(fragmentTag).add(R.id.content_home, fragment).commit();
//        } else {
        mFragmentTransaction.replace(container, fragment, fragmentTag).commit();
//        }
    }

    //--------------------------------------------------------
    // Remove the current fragment from the backstack
    //--------------------------------------------------------
    public void popFragmentFromBackStack(String tag) {
        FragmentManager fm = activity.getSupportFragmentManager();
        fm.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    //--------------------------------------------------------
    // Get fragment back from the backstack
    //--------------------------------------------------------
    public Fragment getFragmentFromBackStack(String tag) {
        FragmentManager fm = activity.getSupportFragmentManager();
        Fragment fragment = fm.findFragmentByTag(tag);
        return fragment;
    }


}
