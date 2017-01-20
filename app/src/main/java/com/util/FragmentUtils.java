package com.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.allemny.R;

/**
 * Created by elsaidel on 1/20/2017.
 */

public class FragmentUtils {
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    public FragmentUtils(FragmentActivity activity) {
        mFragmentManager = activity.getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
    }

    //--------------------------------------------------------
    // Navigate from the current fragment to another one
    //--------------------------------------------------------
    public void navigateToFragment(Fragment fragment, String fragmentTag) {
//        if (addToBackStack) {
//            mFragmentTransaction.addToBackStack(fragmentTag).add(R.id.content_home, fragment).commit();
//        } else {
        mFragmentTransaction.replace(R.id.content_home, fragment, fragmentTag).addToBackStack(fragmentTag).commit();
//        }
    }
}
