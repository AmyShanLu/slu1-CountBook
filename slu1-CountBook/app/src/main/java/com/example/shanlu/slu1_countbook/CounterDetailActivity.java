package com.example.shanlu.slu1_countbook;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shanlu.slu1_countbook.Data.Counter;

import java.util.ArrayList;

import static com.example.shanlu.slu1_countbook.R.string.val_edit_hint;

public class CounterDetailActivity extends AppCompatActivity {

    // Boolean var indicates if the new counter needs to be added
    private boolean isNewCounter;

    // The position of the selected counter object in the array list
    private int counter_position;

    // The selected counter
    private Counter selected_counter;

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
            isNewCounter = true;

        } else {
            // Get the selected Counter object
            selected_counter = bundle.getParcelable(CounterAdapter.COUNTER_OBJ);
            // Get the counter object's position in the arraylist
            counter_position = bundle.getInt(CounterAdapter.COUNTER_OBJ_POS);

            // Initialize the detail ui
            InitializeDetailAct(selected_counter);

            isNewCounter = false;
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
        // Check if there're error in the current value edit box or it's empty
        if (validEditText(mCounterCurrValEdit)) {
            // Check if the edit box is empty
            if(mCounterCurrValEdit.getText().toString().trim().equals("")) {
                // Toast an invalid message
                Toast.makeText(this, "Please input a current value", Toast.LENGTH_LONG).show();
            } else {
                // Add 1 to the current value
                int curr_val = Integer.parseInt(mCounterCurrValEdit.getText().toString());

                curr_val += 1;

                // Set the value in the edit box to the updated curr_val
                mCounterCurrValEdit.setText(String.valueOf(curr_val));
            }

        } else {
            // Set error message in the current value edit box
            mCounterCurrValEdit.setError("Value must be a non-negative integer!");
        }
    }

    /**
     * Minus the value in the current value edit box by 1
     */
    private void MinusCurrValByOne(){
        // Check if there're error in the current value edit box or it's empty
        if (validEditText(mCounterCurrValEdit)) {
            // Check if the edit box is empty
            if(mCounterCurrValEdit.getText().toString().trim().equals("")) {
                // Toast an invalid message
                Toast.makeText(this, "Please input a current value", Toast.LENGTH_LONG).show();
            } else {
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
            }

        } else {
            // Set error message in the current value edit box
            mCounterCurrValEdit.setError("Value must be a non-negative integer!");
        }
    }

    /**
     * Reset the value in the current value edit box to the value in the initial value edit box
     */
    private void ResetCurrVal() {
        // Check if there're error in the editText box of the initial value or it's empty
        if (validEditText(mCounterInitValEdit)) {
            // Get the value in the initial value edit box
            int reset_val = Integer.parseInt(mCounterInitValEdit.getText().toString());

            // Set it to the value in the current value edit box
            mCounterCurrValEdit.setText(String.valueOf(reset_val));

        } else {
            // Set error message in the current value edit box
            mCounterInitValEdit.setError("Value must be a non-negative integer!");
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
        // Check if the current values in the edit box are valid for create new counter
        if (validEditText(mCounterNameEdit) && validEditText(mCounterCurrValEdit)
                && validEditText(mCounterInitValEdit)) {
            // for newly created counter
            if (isNewCounter) {
                Counter new_counter = createCounter();

                // Append this new counter to the end of the array list
                ArrayList<Counter> temp_counters = MainActivity.getCounterList();
                temp_counters.add(new_counter);

                // update the counter list in the mainactivity
                MainActivity.setCounterList(temp_counters);

                // Update the screen and data in the file
                MainActivity.updateData();

                // close detail screen
                finish();

            } else {
                // for existing counter
                // The old selected counter object
                // Set the name, initial value and coumment
                selected_counter.setCountName(mCounterNameEdit.getText().toString());
                selected_counter.setCountInitVal(Integer.parseInt(mCounterInitValEdit.getText().toString()));
                selected_counter.setCountComment(mCounterCommentEdit.getText().toString());

                // Check if the Current Value Edit box is empty
                if (mCounterCurrValEdit.getText().toString().trim().equals("")) {
                    // Set current value error
                    mCounterCurrValEdit.setError("Current value cannot be empty");
                } else {
                    // Check if user changed the current value
                    int old_curr_val = selected_counter.getCountCurrVal();
                    int new_curr_val = Integer.parseInt(mCounterCurrValEdit.getText().toString());

                    if (old_curr_val != new_curr_val) {
                        // Set the current value of the selected counter to new current value
                        selected_counter.setCountCurrVal(new_curr_val);
                    }

                    // The old array list of counters in the main screen
                    ArrayList<Counter> temp_counters = MainActivity.getCounterList();

                    // set the counter object at the position of the selected counter object to the new one
                    temp_counters.set(counter_position, selected_counter);

                    // update the counter list in the mainactivity
                    MainActivity.setCounterList(temp_counters);

                    // Update the screen and data in the file
                    MainActivity.updateData();

                    // close detail screen
                    finish();
                }
            }
        } else {
            // Toast an error message
            Toast.makeText(this,"Must give appropriate name and initial value",
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Check if the EditText has error or it's empty
     *
     * @return True if valid
     *         False if invalid
     */
    private boolean validEditText(EditText editText) {

        if (editText.getError() != null) {
            // The edit text has error
            return false;

        } else if (editText.getText().toString().equals("") || editText.getText() == null) {
            // Current edit text are allowed to be empty
            if (editText.getId() == R.id.counter_curr_val_edit) {
                return true;
            } else {
                return false;
            }
        }
        else {
            return true;
        }
    }

    /**
     * Create new Counter object from the current detail screen
     */
    private Counter createCounter() {
        Counter new_counter = new Counter(mCounterNameEdit.getText().toString(),
                Integer.parseInt(mCounterInitValEdit.getText().toString()));

        // Set new counter's current value and comment
        // Check if current value edit box is empty
        if (!mCounterCurrValEdit.getText().toString().trim().equals("")) {
            // if not, set the current value
            new_counter.setCountCurrVal(Integer.parseInt(mCounterCurrValEdit.getText().toString()));
        }

        new_counter.setCountComment(mCounterCommentEdit.getText().toString());

        return new_counter;
    }
}
