package com.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

/**
 * Created by elsaidel on 1/19/2017.
 */

public class IntentUtils {

    //-----------------------------------------------------------
    // This methods responsible for navigating to gallery for activity
    //-----------------------------------------------------------
    public static void launchCameraIntentForActivity(Activity activity, int cameraRequestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, cameraRequestCode);
    }

    //-----------------------------------------------------------
    // This methods responsible for navigating to gallery for activity
    //-----------------------------------------------------------
    public static void launchGlleryIntentForActivity(Activity activity, String selectFile, int galleryRequestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        try {

            activity.startActivityForResult(Intent.createChooser(intent, selectFile), galleryRequestCode);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


    //-----------------------------------------------------------
    // This methods responsible for navigating to camera for fragment
    //-----------------------------------------------------------
    public static void launchCameraIntentForFragment(Fragment fragment, int cameraRequestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fragment.startActivityForResult(intent, cameraRequestCode);
    }

    //-----------------------------------------------------------
    // This methods responsible for navigating to gallery for fragment
    //-----------------------------------------------------------
    public static void launchGalleryIntentForFragment(Fragment fragment, String selectFile, int galleryRequestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        try {
            fragment.startActivityForResult(Intent.createChooser(intent, selectFile), galleryRequestCode);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
