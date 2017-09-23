package com.example.shanlu.slu1_countbook;

/**
 * Created by shanlu on 2017-09-20.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shanlu.slu1_countbook.Data.Counter;

/**
 * CounterAdapter is the adapter for the recycler view, it's used to display the counter item using the
 * recycler view.
 */

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.CounterViewHolder> {

    public static final String COUNTER_OBJ = "COUNTER";

    public static final String COUNTER_OBJ_POS = "COUNTER_POS";

    private Context mContext;

    public CounterAdapter(Context context) {
        this.mContext = context;
    }

    /**
     * This function creates a view holder for the counter recycler view
     *
     * @param parent : the parent which contains the view holder
     * @param viewType :the view type of the new view
     * @return CounterViewHolder: the new view holder that holds for the counter item
     */
    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.counter_list_item, parent, false);
        CounterViewHolder counterViewHolder = new CounterViewHolder(view);

        return counterViewHolder;
    }

    /**
     * Display the counter at the particular position.
     *
     * @param counterViewHolder: the view holder for the counter item in the list
     * @param position: the position of the counter item in the counter list adapter
     */
    @Override
    public void onBindViewHolder(CounterViewHolder counterViewHolder, int position) {
        counterViewHolder.bind(position);
    }

    /**
     * Get the total number of items in the list
     * @return the total number of items in the list
     */
    @Override
    public int getItemCount() {
        return MainActivity.getCounterList().size();
    }

    /**
     * The view holder for each counter item in the counter list
     * Assign the attributes of the counter to the corresponding fields
     */
    class CounterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView counterName_TextView;
        private TextView counterDate_TextView;
        private TextView counterCurrVal_TextView;
        private TextView counterInitVal_TextView;
        private TextView counterComment_TextView;

        public CounterViewHolder(View itemView) {
            super(itemView);

            counterName_TextView = (TextView) itemView.findViewById(R.id.couter_name_textView_id);
            counterDate_TextView = (TextView) itemView.findViewById(R.id.couter_date_textView_id);
            counterCurrVal_TextView = (TextView) itemView.findViewById(R.id.couter_curr_val_textView_id);
            counterInitVal_TextView = (TextView) itemView.findViewById(R.id.couter_init_val_textView_id);
            counterComment_TextView = (TextView) itemView.findViewById(R.id.couter_comment_textView_id);

            itemView.setOnClickListener(this);
        }

        /**
         * Get the counter item in the counters list with index = position
         * Assign the counter's attributes to the corresponding fields
         *
         * @param position: Position of the item in the list
         */
        void bind(int position) {
            // The counter at the position in the list
            Counter counter = MainActivity.getCounterList().get(position);

            // Assign the counter's attributes to the corresponding text view
            counterName_TextView.setText(counter.getCountName());
            counterDate_TextView.setText(counter.getCountDate());
            counterCurrVal_TextView.setText(counter.getCountCurrVal().toString());
            counterInitVal_TextView.setText(counter.getCountInitVal().toString());
            counterComment_TextView.setText(counter.getCountComment());
        }

        @Override
        public void onClick(View view) {
            // The position of the counter in the list that the user clicks
            int adapterPosition = getAdapterPosition();

            // The selected counter object
            Counter selected_Counter = MainActivity.getCounterList().get(adapterPosition);

            // Show the detail of the selected counter
            // Start the CounterDetailActivity
            Intent counterDetail_intent = new Intent(mContext, CounterDetailActivity.class);
            // Pass the selected counter object to the counter detail activity
            counterDetail_intent.putExtra(COUNTER_OBJ, selected_Counter);
            // Pass the position of the selected counter object to the detail activity
            counterDetail_intent.putExtra(COUNTER_OBJ_POS, adapterPosition);

            mContext.startActivity(counterDetail_intent);
        }
    }
}
