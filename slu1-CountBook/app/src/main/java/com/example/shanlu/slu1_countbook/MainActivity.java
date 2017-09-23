package com.example.shanlu.slu1_countbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shanlu.slu1_countbook.Data.Counter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // The list of counters saved in file
    private static ArrayList<Counter> counters;

    private static CounterFileStorage counterFileStorage;

    private static CounterAdapter mCounterAdapter;
    private RecyclerView mCountersList;

    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterFileStorage = new CounterFileStorage(this);

        mAddButton = (Button) findViewById(R.id.add_button);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Add a new counter to the list
                AddNewCounter();
            }
        });

        mCountersList = (RecyclerView) findViewById(R.id.counter_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mCountersList.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mCountersList.getContext(),
                layoutManager.getOrientation());
        mCountersList.addItemDecoration(mDividerItemDecoration);

        mCountersList.setHasFixedSize(true);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // The arraylist of counter records saved in the file
        counters = counterFileStorage.loadFromFile();

        // Set the total number of counters in the counter list
        ((TextView) findViewById(R.id.total_number_textView)).setText(Integer.toString(counters.size()));

        mCounterAdapter = new CounterAdapter(counters, this);

        mCountersList.setAdapter(mCounterAdapter);
    }

    /**
     * Start the CounterDetailActivity allowing user to create and add a new counter to the counter list.
     */
    private void AddNewCounter() {
        Intent counterDetail_intent = new Intent(this, CounterDetailActivity.class);

        startActivity(counterDetail_intent);
    }

    /**
     * Get the static variable counters in the mainActivity
     */
    public static ArrayList<Counter> getCounterList() {
        return counters;
    }

    /**
     * Set the updated counter list to the mainActivity's counters
     *
     * @param updated_counters The updated counter array list
     */
    public static void setCounterList(ArrayList<Counter> updated_counters) {
        counters = updated_counters;
    }

    /**
     * Update the screen and data in the file
     */
    public static void updateData() {
        // Data changed in the recyclerView list
        mCounterAdapter.notifyDataSetChanged();

        // Data changed in the file
        counterFileStorage.saveInFile(counters);
    }

}
