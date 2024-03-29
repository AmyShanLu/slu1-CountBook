package com.example.shanlu.slu1_countbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shanlu.slu1_countbook.Data.Counter;

import java.util.ArrayList;

/**
 * The MainActivity shows a list of Counter objects on the screen, it contains an add button to allow
 * the user add new counter object to the list, and it has a view to show the total number of counter
 * objects in the list.
 */

public class MainActivity extends AppCompatActivity {

    // The list of counters saved in file
    private static ArrayList<Counter> counters;

    private static CounterFileStorage counterFileStorage;

    private static CounterAdapter mCounterAdapter;
    private RecyclerView mCountersList;

    private TextView mTotalNumTextView;

    private Button mAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterFileStorage = new CounterFileStorage(this);

        mTotalNumTextView = (TextView) findViewById(R.id.total_number_textView);

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
        mTotalNumTextView.setText(Integer.toString(counters.size()));

        mCounterAdapter = new CounterAdapter(this);

        mCountersList.setAdapter(mCounterAdapter);

        // Implement swipe to left to delete for recycler view
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Remove the swiped item from the list and screen
                int position = viewHolder.getAdapterPosition();

                counters.remove(position);

                mCounterAdapter.notifyItemRemoved(position);

                // Update the counters saved in file
                counterFileStorage.saveInFile(counters);

                // Update the total number in the list
                mTotalNumTextView.setText(Integer.toString(counters.size()));
            }

            // Reference: https://stackoverflow.com/questions/30820806/adding-a-colored-background-with-text-icon-under-swiped-row-when-using-androids
            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                        float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                    View itemView = viewHolder.itemView;
                    if (dX < 0) {
                        // Show the delete icon
                        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.delete_drawable);
                        Paint paint = new Paint();
                        paint.setARGB(255, 255, 0, 0);
                        c.drawBitmap(bitmap, dX + (float)itemView.getWidth(),
                                (float) itemView.getTop() + (float) itemView.getHeight()/2 - (float) bitmap.getHeight()/2,
                                paint);
                    }

                } else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        // Attach item touch helper to the recycler view
        itemTouchHelper.attachToRecyclerView(mCountersList);
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
