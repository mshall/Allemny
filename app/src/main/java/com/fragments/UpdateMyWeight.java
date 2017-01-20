package com.fragments;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activities.HomeActivity;
import com.allemny.R;
import com.constants.Constants;
import com.database.dao.WeightDAO;
import com.pojo.Weight;
import com.spark.submitbutton.SubmitButton;
import com.util.DialogUtils;
import com.util.ImageLoader;
import com.util.ImageUtils;
import com.util.IntentUtils;
import com.util.PermissionsUtility;
import com.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.blackbox_vision.datetimepickeredittext.view.DatePickerEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateMyWeight extends Fragment implements View.OnClickListener {
    @BindView(R.id.etFragmentUpdateMyWeightDatePicker)
    DatePickerEditText etDatePicker;
    @BindView(R.id.etFragmentUpdateMyWeight)
    EditText etMyWeight;
    @BindView(R.id.ivFragmentUpdateMyWeightUserImage)
    ImageView ivUserImage;
    @BindView(R.id.ivFragmentUpdateMyWeightEditUserImage)
    ImageView ivEditImage;
    @BindView(R.id.tvFragmentUpdateMyWeightErrorMessage)
    TextView tvErrorMessage;
    @BindView(R.id.bFragmentUpdateMyWeight)
    SubmitButton bUpdateMyWeight;
    View view;
    String userChoosenTask = "test";
    HomeActivity activity;

    final int REQUEST_CAMERA_CAPTURE = 0;
    final int REQUEST_GALLERY_CAPTURE = 1;
    boolean isImageChanged;

    public UpdateMyWeight() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (HomeActivity) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_my_weight, container, false);
        ButterKnife.bind(this, view);
        initializeViews();
        return view;
    }

    private void initializeViews() {
        etDatePicker.setManager(getActivity().getSupportFragmentManager());
        etDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etDatePicker.clearFocus();
            }
        });
        ivUserImage.setOnClickListener(this);
        ivEditImage.setOnClickListener(this);
        bUpdateMyWeight.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bFragmentUpdateMyWeight:
                if (etDatePicker.getText().toString().isEmpty()) {

                    etDatePicker.setBackgroundResource(R.drawable.red_border);
                    tvErrorMessage.setText(getString(R.string.all_fields_required));
                }
                if (etMyWeight.getText().toString().isEmpty()) {
                    etMyWeight.setBackgroundResource(R.drawable.red_border);
                    tvErrorMessage.setText(getString(R.string.all_fields_required));
                    //double weight = Double.parseDouble(etMyWeight.getText().toString());
                }
                if (!isImageChanged) {
                    DialogUtils.showErrorDialog(getContext(), getString(R.string.missing_image));
                    tvErrorMessage.setText(getString(R.string.all_fields_required));
                }
                if (!etDatePicker.getText().toString().isEmpty() && !etMyWeight.getText().toString().isEmpty() && isImageChanged) {
                    Weight weight = new Weight();
                    String date = etDatePicker.getText().toString();
                    String email = SharedPreferencesUtils.getStringFromSharedPreferences(getContext(), Constants.EMAIL);
                    Log.e("IMAGE REAL NAME", "Changed image? " + isImageChanged);
                    double weightInDouble = Double.parseDouble(etMyWeight.getText().toString());
                    weight.setWeight(weightInDouble);
                    weight.setDate(date);
                    weight.setUserImage(ImageUtils.getBytes(ImageUtils.getImageFromImageView(ivUserImage)));
                    weight.setEmail(email);
                    WeightDAO dao = new WeightDAO(getContext());
                    dao.addUserWeight(weight);
                    if (dao.isUserWeightExist(email, date)) {
                        Snackbar.make(etMyWeight, getString(R.string.weight_update_added), Snackbar.LENGTH_LONG).show();

                    }
                }
                break;
            case R.id.ivFragmentUpdateMyWeightUserImage:
                //handleUserImagePicker();
                handleUserImagePicker();
                break;
            case R.id.ivFragmentUpdateMyWeightEditUserImage:
                handleUserImagePicker();
                break;
        }
    }

    private void handleUserImagePicker() {
        final CharSequence[] items = {getString(R.string.take_photo), getString(R.string.choose_from_library),
                getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.choose_option));
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = PermissionsUtility.checkPermission(getActivity());
                if (items[item].equals(getString(R.string.take_photo))) {
                    userChoosenTask = getString(R.string.take_photo);
                    if (result) {
                        //Prepare Camera
                        try {
                            IntentUtils.launchCameraIntentForFragment(UpdateMyWeight.this, REQUEST_CAMERA_CAPTURE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else if (items[item].equals(getString(R.string.choose_from_library))) {
                    userChoosenTask = getString(R.string.choose_from_library);
                    if (result)
                        //Prepare Gallery
                        IntentUtils.launchGalleryIntentForFragment(UpdateMyWeight.this, getString(R.string.select_file), REQUEST_GALLERY_CAPTURE);
                } else if (items[item].equals(getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }


    //-----------------------------------------------------------
    // This methods responsible for setting image coming from
    // camera or gallery
    //-----------------------------------------------------------
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        //Bundle extras = imageReturnedIntent.getExtras();
        switch (requestCode) {
            case REQUEST_CAMERA_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    /*Bitmap bitmap = (Bitmap)imageReturnedIntent.getExtras().get("data");
                    ivUserImage.setImageBitmap(bitmap);*/
                    isImageChanged = true;
                    ImageLoader.onCaptureImageResult(imageReturnedIntent, getContext(), ivUserImage, R.drawable.add_image);
                }
                break;

            case REQUEST_GALLERY_CAPTURE:
                if (resultCode == Activity.RESULT_OK) {
                    isImageChanged = true;
                    ImageLoader.onSelectFromGalleryResult(imageReturnedIntent, getContext(), ivUserImage, R.drawable.add_image);
                }
                break;


        }
    }


    //-----------------------------------------------------------
    // This methods responsible for taking action
    // depending on type of choosing action
    //-----------------------------------------------------------
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PermissionsUtility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
            case PermissionsUtility.MY_PERMISSIONS_REQUET_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals(getString(R.string.take_photo)))
                        IntentUtils.launchCameraIntentForFragment(this, REQUEST_CAMERA_CAPTURE);
                    else if (userChoosenTask.equals(getString(R.string.choose_from_library)))
                        IntentUtils.launchGalleryIntentForFragment(this, getString(R.string.select_file), REQUEST_GALLERY_CAPTURE);
                } else {
                    //code for deny
                    Toast.makeText(getActivity(), "Didn't choose any", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    //-----------------------------------------------------------
    // This methods responsible for navigating to gallery
    //-----------------------------------------------------------
    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA_CAPTURE);
    }

    //-----------------------------------------------------------
    // This methods responsible for navigating to gallery
    //-----------------------------------------------------------
    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        try {

            startActivityForResult(Intent.createChooser(intent, getString(R.string.select_file)), REQUEST_GALLERY_CAPTURE);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


}
