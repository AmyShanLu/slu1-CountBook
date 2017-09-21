package com.example.shanlu.slu1_countbook;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.shanlu.slu1_countbook.Data.Counter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CounterAdapter mCounterAdapter;
    private RecyclerView mCountersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        mCounterAdapter = new CounterAdapter(counters);

        mCountersList.setAdapter(mCounterAdapter);
    }
}
