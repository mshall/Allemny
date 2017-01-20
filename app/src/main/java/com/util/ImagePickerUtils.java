package com.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;

import com.mikelau.croperino.CropImage;
import com.mikelau.croperino.CroperinoFileUtil;
import com.mikelau.croperino.InternalStorageContentProvider;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * Created by elsaidel on 11/5/2016.
 */

public class ImagePickerUtils {
    public static void runCropImageForActivity(File file, Activity ctx, boolean isScalable, int aspectX, int aspectY, int color, int bgColor) {
        Intent intent = new Intent(ctx, CropImage.class);
        intent.putExtra("image-path", file.getPath());
        intent.putExtra("scale", isScalable);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("color", color);
        intent.putExtra("bgColor", bgColor);
        ctx.startActivityForResult(intent, 3);
    }

    public static void runCropImageForFragment(File file, Activity ctx, Fragment fragment, boolean isScalable, int aspectX, int aspectY, int color, int bgColor) {
        Intent intent = new Intent(ctx, CropImage.class);
        intent.putExtra("image-path", file.getPath());
        intent.putExtra("scale", isScalable);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("color", color);
        intent.putExtra("bgColor", bgColor);
        fragment.startActivityForResult(intent, 3);
    }

    public static void prepareCameraForFragment(Fragment fragment) throws Exception {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Uri mImageCaptureUri = null;
        String state = Environment.getExternalStorageState();
        if ("mounted".equals(state)) {
            mImageCaptureUri = Uri.fromFile(CroperinoFileUtil.newCameraFile());
        } else {
            mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI;
        }

        intent.putExtra("output", mImageCaptureUri);
        intent.putExtra("return-data", true);
        fragment.startActivityForResult(intent, 1);
    }

    public static void prepareGalleryForFragment(Fragment fragment) {
        Intent i = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        fragment.startActivityForResult(i, 2);
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        //BitmapFactory.Options optionss = new BitmapFactory.Options();
        //optionss.inPreferredConfig = Bitmap.Config.RGB_565;


        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
// Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeBase64StringIntoImage(String encodedText) {
        try {
            String imageDataBytes = encodedText.substring(encodedText.indexOf(",") + 1);
            InputStream stream = new ByteArrayInputStream(Base64.decode(imageDataBytes.getBytes(), Base64.DEFAULT));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            Log.v("Base64 Conversion", "Image Converted");
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }
}
