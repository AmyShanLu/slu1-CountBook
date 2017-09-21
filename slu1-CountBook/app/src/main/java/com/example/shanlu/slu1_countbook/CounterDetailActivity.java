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

import com.example.shanlu.slu1_countbook.Data.Counter;

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
        //TODO
    }

    /**
     * Minus the value in the current value edit box by 1
     */
    private void MinusCurrValByOne(){
        //TODO
    }
}
