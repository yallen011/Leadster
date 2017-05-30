package com.ubcma.leadster.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ubcma.leadster.R;
import com.ubcma.leadster.fragment.DatePickerFragment;
import com.ubcma.leadster.fragment.TimePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.widget.Toast.makeText;


public class LeadDetailsActivity extends AppCompatActivity implements DatePickerFragment.OnDatePickerListener, TimePickerFragment.OnTimePickerListener {

    TextView follow_up_call_date;
    TextView interview_date;
    TextView party_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.cld_attempts_detail);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        follow_up_call_date = (TextView) findViewById(R.id.cld_follow_up_call_detail);

        follow_up_call_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(v);
            }
        });

        interview_date = (TextView) findViewById(R.id.cld_interview_detail);
        interview_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(v);
            }
        });

        party_date = (TextView) findViewById(R.id.cld_party_detail);
        party_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(v);
            }
        });
    }

    /**change date to what was selected by the user
     * @param clickedView view clicked by user to select the date
     * @param date date selected by the user
     * **/
    @Override
    public void onDone(View clickedView, Date date) {
        String formattedDate = formatDate(date);

        int id = clickedView.getId();

        //open the time dialog for Interview and Party
        if((id == interview_date.getId()) || (id == party_date.getId())){

            TimePickerFragment timePickerFragment = new TimePickerFragment();
            timePickerFragment.setClickedView(clickedView);
            timePickerFragment.setSelectedDate(formattedDate);
            timePickerFragment.show(getSupportFragmentManager(), "time");
        }else{
            setClickedText(clickedView, formattedDate);
        }

    }
    public String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd");
        String newDate = sdf.format(date);
        return newDate;
    }

    /**
     * method to open datepicker dialog
     * @param clickedView view clicked by the user to select the date
     */
    public void pickDate(View clickedView){
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setClickedView(clickedView);
        datePickerFragment.show(getSupportFragmentManager(), "date");
    }

    /**
     * Changes the time and date to what was selected by the user in both dialogs
     * @param clickedView view selected by the user
     * @param date dated selected in the date picker
     * @param time time selected by the user in time picker
     */
    @Override
    public void onDone(View clickedView, String date, String time) {

        String formattedDateTime = date + " "+ time;
        setClickedText(clickedView, formattedDateTime);
    }

    /**
     * set the textview to the combined date and time, if applicable, after closing the dialogs
     * @param clickedView
     * @param formatedDateAndTime
     */
    public void setClickedText(View clickedView, String formatedDateAndTime){
        TextView tv = (TextView)clickedView;
        tv.setText(formatedDateAndTime);
    }
}
