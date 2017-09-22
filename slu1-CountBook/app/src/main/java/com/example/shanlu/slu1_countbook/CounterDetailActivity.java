package com.example.shanlu.slu1_countbook;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shanlu.slu1_countbook.Data.Counter;

import static com.example.shanlu.slu1_countbook.R.string.val_edit_hint;

public class CounterDetailActivity extends AppCompatActivity {

    private EditText mCounterNameEdit;
    private TextView mCounterDateText;
    private EditText mCounterCurrValEdit;
    private EditText mCounterInitValEdit;
    private EditText mCounterCommentEdit;

    private ImageButton mAddValButton;
    private ImageButton mMinusValButton;
    private Button mResetValButton;
    private Button mSaveButton;
    private Button mCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_detail);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar actionBar = getSupportActionBar();

        // Enable the Up button
        actionBar.setDisplayHomeAsUpEnabled(true);

        mCounterNameEdit = (EditText) findViewById(R.id.counter_name_edit);
        mCounterDateText = (TextView) findViewById(R.id.counter_date_text);
        mCounterCurrValEdit = (EditText) findViewById(R.id.counter_curr_val_edit);
        mCounterInitValEdit = (EditText) findViewById(R.id.counter_init_val_edit);
        mCounterCommentEdit = (EditText) findViewById(R.id.counter_comment_edit);

        // Set the TextWatcher for the EditText
        mCounterNameEdit.addTextChangedListener(new StringEditTextWatcher(mCounterNameEdit,
                R.id.counter_name_edit));
        mCounterCurrValEdit.addTextChangedListener(new ValueEditTextWatcher(mCounterCurrValEdit,
                R.id.counter_curr_val_edit));
        mCounterInitValEdit.addTextChangedListener(new ValueEditTextWatcher(mCounterInitValEdit,
                R.id.counter_init_val_edit));

        mAddValButton = (ImageButton) findViewById(R.id.add_val_button);
        mMinusValButton = (ImageButton) findViewById(R.id.minus_val_button);
        mResetValButton = (Button) findViewById(R.id.reset_curr_val_button);
        mSaveButton = (Button) findViewById(R.id.save_button);
        mCancelButton = (Button) findViewById(R.id.cancel_button);

        mAddValButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add the value in Current value edit box by 1
                AddCurrValByOne();
            }
        });

        mMinusValButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Minus the value in Current value edit box by 1
                MinusCurrValByOne();
            }
        });

        mResetValButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset the value in Current value edit box to the value in the Initial value edit box
                ResetCurrVal();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the changes
                SaveChange();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cancel the changes made, go to main list page
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle == null) {
            // Enter the counter detail ui from the main activity (Add button)
            Log.d("Test","From add button");

        } else {
            // Get the Counter object
            Counter counter = bundle.getParcelable(MainActivity.COUNTER_OBJ);

            // Initialize the detail ui
            InitializeDetailAct(counter);
        }
    }

    /**
     * Initialize the Counter Detail Activity UI with the attributes' value of the given counter object.
     */
    private void InitializeDetailAct(Counter counter) {
        // Set counter's name, date, current value, initial value and comment
        mCounterNameEdit.setText(counter.getCountName());
        mCounterDateText.setText(counter.getCountDate());
        mCounterCurrValEdit.setText(Integer.toString(counter.getCountCurrVal()));
        mCounterInitValEdit.setText(Integer.toString(counter.getCountInitVal()));
        mCounterCommentEdit.setText(counter.getCountComment());
    }

    /**
     * Add the value in the current value edit box by 1
     */
    private void AddCurrValByOne(){
        // Check if there're error in the current value edit box
        if (mCounterCurrValEdit.getError() == null) {
            // Add 1 to the current value
            int curr_val = Integer.parseInt(mCounterCurrValEdit.getText().toString());

            curr_val += 1;

            // Set the value in the edit box to the updated curr_val
            mCounterCurrValEdit.setText(String.valueOf(curr_val));

        } else {
            // Toast an error message
            Toast.makeText(this,getResources().getText(val_edit_hint),Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Minus the value in the current value edit box by 1
     */
    private void MinusCurrValByOne(){
        // Check if there're error in the current value edit box
        if (mCounterCurrValEdit.getError() == null) {
            // Minus 1 to the current value
            int curr_val = Integer.parseInt(mCounterCurrValEdit.getText().toString());

            curr_val -= 1;

            // Check if the updated current value is negative
            if (curr_val >= 0){
                // Set the value in the edit box to the updated curr_val
                mCounterCurrValEdit.setText(String.valueOf(curr_val));
            } else {
                // Toast an error message
                Toast.makeText(this,getResources().getText(val_edit_hint),Toast.LENGTH_LONG).show();
            }

        } else {
            // Toast an error message
            Toast.makeText(this,mCounterCurrValEdit.getError().toString(),Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Reset the value in the current value edit box to the value in the initial value edit box
     */
    private void ResetCurrVal() {
        // Check if there're error in the editText box of the initial value
        if (mCounterInitValEdit.getError() == null) {
            // Get the value in the initial value edit box
            int reset_val = Integer.parseInt(mCounterInitValEdit.getText().toString());

            // Set it to the value in the current value edit box
            mCounterCurrValEdit.setText(String.valueOf(reset_val));

        } else {
            // Toast an error message
            Toast.makeText(this,mCounterInitValEdit.getError().toString(),Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Save the changes made in the CounterDetail UI
     *
     * Two cases:
     *      1. Create a new counter and append it to the end of list in the main page
     *      2. Change the attributes of the current counter object
     */
    private void SaveChange() {
        //TODO
    }
}
