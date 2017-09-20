package com.example.shanlu.slu1_countbook.Data;

/**
 * Created by shanlu on 2017-09-20.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is for each counter object in the CountBook. Each counter object contains the counter's
 * details, such as name, date, current value, initial value and comment. This class has get methods
 * used to get a counter object's attributes' value, and it has set methods used to set the counter's
 * attributes directly (except for the date attribute).
 */

public class Counter {

    // Each counter has the following private attributes:
    // Name (textual),
    // Date (yyyy-MM-dd format, the date when the counter is made or the current value is last changed)
    // Current Value (non-negative numeric)
    // Initial Value (non-negative numeric)
    // Comment (textual)

    private String name;
    private String date;
    private Integer curr_val;
    private Integer init_val;
    private String comment;

    // Constructor for a counter object
    // Users must specify the name and initial value to create a new counter object
    public Counter(String Name, Integer Init_Val) {

        // Save the new counter's name and initial value
        this.name = Name;
        this.init_val = Init_Val;

        // Set the counter's current value equal to its initial value when the new counter is created.
        // User can change this counter's current value directly in the Current Value's field.
        this.curr_val = this.init_val;

        //Set the counter's date to the date it's created
        this.date = setDate();

    }

    /**
     * Generate the date in yyyy-MM-dd format
     *
     * @return curr_Date: the current date in yyyy-MM-dd format (String)
     */
    private String setDate() {
        // set the current date format to yyyy-MM-dd format
        SimpleDateFormat currDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //get the current date
        Date currDate = new Date();
        // convert the current Date to string
        String curr_Date = currDateFormat.format(currDate);

        return curr_Date;
    }

    /**
     * Get method for getting the counter's name
     *
     * @return the name of the counter
     */
    public String getCountName() {
        return this.name;
    }

    /**
     * Get method for getting the counter's date
     *
     * @return the date of the counter
     */
    public String getCountDate() {
        return this.date;
    }

    /**
     * Get method for getting the counter's current value
     *
     * @return the current value of the counter
     */
    public Integer getCountCurrVal() {
        return this.curr_val;
    }

    /**
     * Get method for getting the counter's current value
     *
     * @return the initial value of the counter
     */
    public Integer getCountInitVal() {
        return this.init_val;
    }

    /**
     * Get method for getting the counter's comment
     *
     * @return the comment of the counter
     */
    public String getCountComment() {
        if (this.comment == null) {
            return "";
        } else {
            return this.comment;
        }
    }

    /**
     * Set method for setting the counter's name
     *
     * @param Name: the counter's name
     */
    public void setCountName(String Name) {
        this.name = Name;
    }

    /**
     * Set method for setting the counter's current value
     *
     * @param Curr_Val: the counter's current value
     */
    public void setCountCurrVal(Integer Curr_Val) {
        this.curr_val = Curr_Val;

        // Update the date of the counter
        this.date = setDate();
    }

    /**
     * Set method for setting the counter's initial value
     *
     * @param Init_Val: the counter's initial value
     */
    public void setCountInitVal(Integer Init_Val) {
        this.init_val = Init_Val;
    }

    /**
     * Set method for setting the counter's comment
     *
     * @param Comment: the counter's comment
     */
    public void setCountComment(String Comment) {
        this.comment = Comment;
    }
}
