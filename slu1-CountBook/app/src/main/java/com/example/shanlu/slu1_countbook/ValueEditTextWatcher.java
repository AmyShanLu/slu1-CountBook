/**
 * Created by shanlu on 2017-09-21.
 */

package com.example.shanlu.slu1_countbook;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.EditText;

/**
 * TextWatcher for the edit text with number input, (current value, initial value)
 */
public class ValueEditTextWatcher extends EditTextWatcher {

    public ValueEditTextWatcher(EditText editText, int editTextRId) {
        super(editText, editTextRId);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        // For Value EditText, it cannot be empty string
        // Check if the input is empty string or only have spaces
        if (TextUtils.isEmpty(editable.toString().trim())) {
            // Set the error in the value edit text
            getEditText().setError("Value cannot be empty!");
        } else{
            //  Check if the editable string can be parse to integer
            String editable_string = editable.toString().trim();

            try {
                int editable_val = Integer.parseInt(editable_string);

                // Check if the input value is negative
                if (editable_val < 0) {
                    getEditText().setError("Value cannot be negative!");
                }

            } catch (NumberFormatException e) {
                getEditText().setError("Value must be a non-negative integer!");
            }
        }
    }
}
