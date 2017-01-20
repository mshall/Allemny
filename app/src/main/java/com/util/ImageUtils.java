package com.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by elsaidel on 1/19/2017.
 */

public class ImageUtils {
    //-----------------------------------------------------------
    // Load image uri of a specific image
    //-----------------------------------------------------------
    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    //-----------------------------------------------------------
    // Load real pth of a specific image uri
    //-----------------------------------------------------------
    public static String getRealPathFromURI(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public static String getImageNameFromImageView(ImageView imageView) {
        return imageView.getContentDescription().toString();
    }

    //-----------------------------------------------------------
    // convert from bitmap to byte array
    //-----------------------------------------------------------
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    //-----------------------------------------------------------
    // convert from byte array to bitmap
    //-----------------------------------------------------------
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    //-----------------------------------------------------------
    // Get bitmap image from ImageView
    //-----------------------------------------------------------
    public static Bitmap getImageFromImageView(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        return bitmap;
    }
}
