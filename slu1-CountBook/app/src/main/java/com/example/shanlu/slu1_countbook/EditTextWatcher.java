/**
 * Created by shanlu on 2017-09-21.
 */

package com.example.shanlu.slu1_countbook;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * TextWatcher for EditText
 */
public class EditTextWatcher implements TextWatcher {

    private EditText mEditText;
    private int mEditTextRId;

    public EditTextWatcher(EditText editText, int editTextRId) {
        this.mEditText = editText;
        this.mEditTextRId = editTextRId;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    /**
     * Get the EditText object of this EditTextWatcher
     *
     * @return EditText: mEditText
     */
    public EditText getEditText(){
        return this.mEditText;
    }

    /**
     * Get the EditText object's RId of this EditTextWatcher
     *
     * @return int: EditTextRId
     */
    public int getEditTextRId(){
        return this.mEditTextRId;
    }
}
