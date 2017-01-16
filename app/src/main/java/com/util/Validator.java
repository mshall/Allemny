package com.util;

import android.content.Context;
import android.widget.EditText;

import com.allemny.R;

import java.util.ArrayList;

/**
 * Created by elsaidel on 1/15/2017.
 */

public class Validator {

    public boolean isValidForm(Context context, ArrayList<EditText> formFields) {
        for (EditText editText : formFields) {
            if (editText.getText().toString().isEmpty())
                editText.setError(context.getString(R.string.required));
        }
        for (EditText editText : formFields) {
            if (editText.getText().toString().isEmpty())
               return false;
        }
        return true;
    }
}
