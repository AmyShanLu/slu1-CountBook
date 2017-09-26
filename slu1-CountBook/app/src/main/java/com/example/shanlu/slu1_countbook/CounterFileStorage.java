/**
 * Created by shanlu on 2017-09-22.
 */

package com.example.shanlu.slu1_countbook;

import android.content.Context;

import com.example.shanlu.slu1_countbook.Data.Counter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * The CounterFileStorage contains two methods to save counter records in the counter list to file
 * and read counter records from the file.
 */
public class CounterFileStorage {

    private static final String FILENAME = "counters_file.sav";

    private Context mContext;

    public CounterFileStorage(Context context) {
        this.mContext = context;
    }

    /**
     * Read the counters list saved in the file
     *
     * @return The array list of counters saved in the file
     */
    public ArrayList<Counter> loadFromFile() {

        ArrayList<Counter> counters = new ArrayList<Counter>();

        try {
            FileInputStream fis = mContext.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();

            counters = gson.fromJson(in, listType);

            return counters;

        } catch (FileNotFoundException e) {

            return counters;

        } catch (IOException e) {

            throw new RuntimeException();
        }

    }

    /**
     * Save the updated counters list in the file
     *
     * @param counters
     */
    public void saveInFile(ArrayList<Counter> counters) {
        try {
            FileOutputStream fos = mContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(fos);

            Gson gson = new Gson();
            gson.toJson(counters, writer);

            writer.flush();

            fos.close();
        } catch (FileNotFoundException e) {

            throw new RuntimeException();

        } catch (IOException e) {

            throw new RuntimeException();
        }
    }
}
