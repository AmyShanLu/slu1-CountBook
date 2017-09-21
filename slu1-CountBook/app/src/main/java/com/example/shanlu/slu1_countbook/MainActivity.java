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

    public static String COUNTER_OBJ = "COUNTER";

    private CounterAdapter mCounterAdapter;
    private RecyclerView mCountersList;

    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddButton = (Button) findViewById(R.id.add_button);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Add a new counter to the list
                AddNewCounter();
            }
        });

        Counter counter0 = new Counter("dummy0", 0);
        counter0.setCountComment("HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello");

        Counter counter1 = new Counter("dummy1", 1);

        ArrayList<Counter> counters = new ArrayList<Counter>();
        counters.add(counter0);
        counters.add(counter1);
        counters.add(counter0);
        counters.add(counter1);
        counters.add(counter0);
        counters.add(counter1);
        counters.add(counter0);
        counters.add(counter1);
        counters.add(counter0);
        counters.add(counter1);

        // Set the total number of counters in the counter list
        ((TextView) findViewById(R.id.total_number_textView)).setText(Integer.toString(counters.size()));

        mCountersList = (RecyclerView) findViewById(R.id.counter_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mCountersList.setLayoutManager(layoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(mCountersList.getContext(),
                layoutManager.getOrientation());
        mCountersList.addItemDecoration(mDividerItemDecoration);

        mCountersList.setHasFixedSize(true);

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
}
