package com.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by salaheld on 10/22/2016.
 */

public class ImageLoader {

    public static void loadImageFromURL(String url, Picasso p, final ImageView target, final int place_holder_image) {
        p.load((url))
//                .fit()
                .into(target,
                        new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                Log.i("picasso", " picasso_InAirport fragment success");
                            }

                            @Override
                            public void onError() {

                                Log.e("picasso", " picasso_InAirport fragment failed");
                                try {
                                    Drawable d = target.getContext().getResources().getDrawable(place_holder_image);
                                    target.setImageResource(place_holder_image);
                                } catch (Exception e) {
                                    target.setVisibility(View.GONE);
                                }


                            }
                        });


    }


    public static void loadImageFromURL(Context context, String url, final ImageView target, final int place_holder_image) {
        Picasso.with(context).load(url).fit().transform(new CircleTransform()).error(place_holder_image).into(target);
    }

    public static void loadImageFromURLWithNoTransformation(Context context, String url, final ImageView target, final int place_holder_image) {
        Picasso.with(context).load(url).fit().error(place_holder_image).into(target);
    }

    public static void loadImageFromURL(String url, Picasso p, final ImageView target) {

        p.load((url))
                .fit()
                .into(target,
                        new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                                Log.i("picasso", " picasso_InAirport fragment success");
                            }

                            @Override
                            public void onError() {

                                Log.e("picasso", " picasso_InAirport fragment failed");
                            }
                        });
    }

    public static void loadImageFromURL_NoFit(String url, Picasso p, final ImageView target) {

        if (!url.equals("")) {
            p.load((url))
                    .into(target,
                            new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    Log.i("picasso", " picasso_InAirport fragment success");
                                }

                                @Override
                                public void onError() {

                                    Log.e("picasso", " picasso_InAirport fragment failed");
                                }
                            });
        }
    }

    public static void loadImageFromURL_NoFit(String url, Picasso p, final ImageView target, final int place_holder_image) {

        if (!url.equals("")) {
            p.load((url))
                    .into(target,
                            new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    Log.i("picasso", " picasso_InAirport fragment success");
                                }

                                @Override
                                public void onError() {

                                    Log.e("picasso", " picasso_InAirport fragment failed");
                                    try {
                                        Drawable d = target.getContext().getResources().getDrawable(place_holder_image);
                                        target.setImageResource(place_holder_image);
                                    } catch (Exception e) {
                                        target.setVisibility(View.GONE);
                                    }


                                }
                            });
        }


    }

    //-----------------------------------------------------------
    // This methods responsible for navigating to gallery
    //-----------------------------------------------------------
    private void cameraIntent(Fragment fragment) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fragment.startActivityForResult(intent, 0);
    }

    //-----------------------------------------------------------
    // This methods responsible for navigating to gallery
    //-----------------------------------------------------------
    private void galleryIntent(Fragment fragment, String selectFile) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        try {

            fragment.startActivityForResult(Intent.createChooser(intent, selectFile), 1);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }

    //-----------------------------------------------------------
    // This methods responsible for loading image into image view
    // After getting it back from gallery
    //-----------------------------------------------------------
    public static void onSelectFromGalleryResult(Intent data, Context context, ImageView ivUserImage, int placeHolderImage) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(context.getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri uri = ImageUtils.getImageUri(context, bm);
        Log.e("TEST IMG URI", "-> " + uri.toString());
        ImageLoader.loadImageFromURLWithNoTransformation(context, uri.toString(), ivUserImage, placeHolderImage);
    }

    //-----------------------------------------------------------
    // This methods responsible for loading image into image view
    // After getting it back from gallery
    //-----------------------------------------------------------
    public static void onCaptureImageResult(Intent data, Context context, ImageView ivUserImage, int placeHolderImage) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Uri uri = ImageUtils.getImageUri(context, thumbnail);
        Log.e("TEST IMG URI", "-> " + uri.toString());
        ImageLoader.loadImageFromURLWithNoTransformation(context, uri.toString(), ivUserImage, placeHolderImage);
    }

    //-----------------------------------------------------------
    // This methods responsible for Setting image
    //-----------------------------------------------------------
    public static void setImageDrawable(Context context, ImageView imageView, int drawableResource) {
        imageView.setImageDrawable(ContextCompat.getDrawable(context, drawableResource));
    }


    //-----------------------------------------------------------
    // This methods responsible for Setting image
    //-----------------------------------------------------------
    public static void setImageDrawable(Context context, ImageView imageView, Bitmap bitmap) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        imageView.setImageDrawable(bitmapDrawable);
    }

}
