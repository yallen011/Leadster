package com.ubcma.leadster.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ubcma.leadster.LeadsterApp;
import com.ubcma.leadster.R;
import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.entity.Appointment;
import com.ubcma.leadster.entity.Lead;
import com.ubcma.leadster.entity.LeadWithAppt;
import com.ubcma.leadster.fragment.DatePickerFragment;
import com.ubcma.leadster.fragment.TimePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.widget.Toast.makeText;


public class LeadDetailsActivity extends AppCompatActivity implements DatePickerFragment.OnDatePickerListener, TimePickerFragment.OnTimePickerListener {

    private final String TAG = LeadDetailsActivity.class.getSimpleName();
    LeadDao leadDao;
    Lead mLead;
    int mLeadId;

    TextView followUpCallDate, interviewDate, partyDate, leadNumber, leadEmail, followUpAttempt;
    TextView leadStatus;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_details);

        leadDao = LeadsterApp.get().getDB().leadDao();
        mLead = new Lead();
        mLeadId = getIntent().getIntExtra("leadId", 1);

        loadLeadDetails();
        initializeViews();
        leadWithAppointment();//used for testing currently
    }

    private void leadWithAppointment(){
        new AsyncTask<Integer, Void, LeadWithAppt>() {

            @Override
            protected LeadWithAppt doInBackground(Integer... params) {

                LeadWithAppt leadWithAppt = leadDao.loadApptByLeadId(params[0]);
                return leadWithAppt;
            }

            @Override
            protected void onPostExecute(LeadWithAppt leadWithAppt) {
                Log.d(TAG, "onPostExecute: Lead - " + leadWithAppt.lead.getName());
                if(leadWithAppt.appointments ==null){
                    Log.d(TAG, "onPostExecute: Appointments - leads have no appts");
                }else{
                    Log.d(TAG, "onPostExecute: Appointments - " + leadWithAppt.appointments.size());
                }
            }
        }.execute(mLeadId);
    }

    private void loadLeadDetails() {

        new AsyncTask<Integer, Void, Lead>() {

            @Override
            protected Lead doInBackground(Integer... params) {

                Lead lead = leadDao.getLead(params[0]);
                return lead;
            }

            @Override
            protected void onPostExecute(Lead lead) {
                leadNumber.setText(lead.getNumber());
                leadEmail.setText(lead.getEmail());
                // TODO: 10/10/2017 add follow up attempts to lead
                followUpAttempt.setText(lead.getFollowUpAttempt());
                leadStatus.setText("Status: " + lead.getStatus().getStatus());
                getSupportActionBar().setTitle(lead.getName());

            }
        }.execute(mLeadId);
    }

    private void initializeViews() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        Spinner spinner = (Spinner) findViewById(R.id.cld_attempts_detail);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.numbers_array, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        spinner.setAdapter(adapter);

        followUpCallDate = findViewById(R.id.cld_follow_up_call_detail);

        followUpCallDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(v);
            }
        });

        interviewDate = findViewById(R.id.cld_interview_detail);
        interviewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(v);
            }
        });

        partyDate = findViewById(R.id.cld_party_detail);
        partyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(v);
            }
        });

        leadNumber = findViewById(R.id.cld_phone_detail);
        leadEmail = findViewById(R.id.cld_email_detail);
        followUpAttempt = findViewById(R.id.cld_attempts_detail);

        leadStatus = findViewById(R.id.tvLeadStatus);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    /**change date to what was selected by the user
     * @param clickedView view clicked by user to select the date
     * @param date date selected by the user
     * **/
    @Override
    public void onDone(View clickedView, Date date) {
        String formattedDate = formatDate(date);
        saveAppointment(formattedDate);

        int id = clickedView.getId();

        //open the time dialog for Interview and Party
        if((id == interviewDate.getId()) || (id == partyDate.getId())){

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


        String formattedDateTime =  date + " "+ time;
        saveAppointment(formattedDateTime);
        setClickedText(clickedView, formattedDateTime);
    }

    private void saveAppointment(String formattedDateTime) {

        new AsyncTask<String, Void, Appointment>() {

            @Override
            protected Appointment doInBackground(String... params) {

                Appointment appt = new Appointment();
                appt.setDate(params[0]);
                appt.setLeadId(1);
                Log.d(TAG, "doInBackground: inside method");

                return appt;
            }

            @Override
            protected void onPostExecute(Appointment appt) {

                Log.d(TAG, "onDone: Saved Appt: Date" + appt.getDate());
                Log.d(TAG, "onDone: Saved Appt: Lead id" + appt.getLeadId());
            }
        }.execute(formattedDateTime);

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
