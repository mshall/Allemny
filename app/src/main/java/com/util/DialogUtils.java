package com.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import com.allemny.R;

import java.util.ArrayList;

/**
 * Created by aliabozaid on 10/20/16.
 */

public class DialogUtils {

    public static void showImagePickerDialog(final Activity context, final ArrayList<String> userChoosenTask, final Fragment fragment) {
        final CharSequence[] items = {context.getString(R.string.take_photo), context.getString(R.string.choose_from_library),
                context.getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Add Profile Picture!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = PermissionsUtility.checkPermission(context);
                if (items[item].equals(context.getString(R.string.take_photo))) {
                    userChoosenTask.set(0, context.getString(R.string.take_photo));
                    if (result) {
                        //Prepare Camera
                        try {
                            ImagePickerUtils.prepareCameraForFragment(fragment);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (items[item].equals(context.getString(R.string.choose_from_library))) {
                    userChoosenTask.set(0, context.getString(R.string.choose_from_library));
                    if (result)
                        //Prepare Gallery
                        ImagePickerUtils.prepareGalleryForFragment(fragment);
                } else if (items[item].equals(context.getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    public static void showErrorDialog(Context context, String message) {
        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.error))
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}