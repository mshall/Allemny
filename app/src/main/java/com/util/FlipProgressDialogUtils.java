package com.util;

import android.app.FragmentManager;

import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elsaidel on 1/27/2017.
 */

public class FlipProgressDialogUtils {

    public static void showFlipProgressDialog(FragmentManager fragmentManager, int upDrawable, int downDrawable, int backgroundColor) {
        //List of animations
        //rotationX, rotationY,
        List<Integer> imageList = new ArrayList<Integer>();
        imageList.add(upDrawable);
        imageList.add(downDrawable);

        FlipProgressDialog border = new FlipProgressDialog();
        border.setImageList(imageList);
        border.setOrientation("rotationY");
        border.setDimAmount(0.8f);
//        border.setBorderStroke(10);
//        border.setBorderColor(Color.parseColor("#02A8F3"));
//        border.setBackgroundColor(Color.parseColor("#00796B"));//teal background
        border.setBackgroundColor(backgroundColor);
        border.show(fragmentManager, "");
    }
}
