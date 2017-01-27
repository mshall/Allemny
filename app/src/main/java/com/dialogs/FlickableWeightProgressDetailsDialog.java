package com.dialogs;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.allemny.R;
import com.tkurimura.flickabledialog.FlickableDialog;
import com.util.ImageLoader;

/**
 * Created by elsaidel on 1/27/2017.
 */

public class FlickableWeightProgressDetailsDialog extends FlickableDialog {

    public static Bitmap bitmap;

    public static FlickableWeightProgressDetailsDialog newInstance(Fragment fragment, Bitmap bitmap) {
        FlickableWeightProgressDetailsDialog.bitmap = bitmap;
        FlickableWeightProgressDetailsDialog flackablePremiumAppealDialog = new FlickableWeightProgressDetailsDialog();
        Bundle bundle = new Bundle();
        bundle.putInt(LAYOUT_RESOURCE_KEY, R.layout.dialog_my_weight_progress_details);
        flackablePremiumAppealDialog.setTargetFragment(fragment, 0);
        flackablePremiumAppealDialog.setArguments(bundle);

        return flackablePremiumAppealDialog;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        ImageView ivUserImage = (ImageView) dialog.findViewById(R.id.ivDialogMyWeightProgressDetails);
        ImageLoader.setImageDrawable(getContext(), ivUserImage, bitmap);
        ImageView ivClose = (ImageView) dialog.findViewById(R.id.ivDialogMyWeightProgressDetailsClose);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return dialog;
    }
}