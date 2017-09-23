/**
 * Created by shanlu on 2017-09-21.
 */

package com.example.shanlu.slu1_countbook;


import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * TextWatcher for the edit text with string input, (name, comment)
 */
public class StringEditTextWatcher extends EditTextWatcher {

    public StringEditTextWatcher(EditText editText, int editTextRId) {
        super(editText, editTextRId);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // For Name EditText, it cannot be empty string or null
        if (getEditTextRId() == R.id.counter_name_edit) {
            // Check if the input is empty string or only have spaces
            if (TextUtils.isEmpty(editable.toString().trim())) {
                // Set the error in the Name edit text
                getEditText().setError("Name cannot be empty!");
            }
        }
    }

}
