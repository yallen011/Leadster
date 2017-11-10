package com.ubcma.leadster.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.ubcma.leadster.LeadsterApp;
import com.ubcma.leadster.R;
import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.entity.Lead;
import com.ubcma.leadster.fragment.DatePickerFragment;
import com.ubcma.leadster.fragment.TimePickerFragment;

import java.util.Date;

public class NewLeadActivity extends AppCompatActivity implements DatePickerFragment.OnDatePickerListener, TimePickerFragment.OnTimePickerListener {

    private static final String TAG = NewLeadActivity.class.getSimpleName();
    LeadDao leadDao;
    Lead lead;
    String mLeadType;
    TextView mLeadName, mLeadNumber, mLeadEmail, mAddAppointment;
    Button mSave, mCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lead);

        leadDao = LeadsterApp.get().getDB().leadDao();
        lead = new Lead();

        initializeViews();


        setSaveCancelActions();


    }

    private void initializeViews() {
        Spinner spinner = findViewById(R.id.phone_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.phone_type_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //TODO: add radio button functionality
        RadioGroup leadTypeGroup = findViewById(R.id.lead_type_rg);
        leadTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton leadTypeBtn = findViewById(checkedId);
                mLeadType = leadTypeBtn.getText().toString();
            }
        });

        mLeadName = findViewById(R.id.name);
        mLeadNumber = findViewById(R.id.pNum);
        TextView leadAddress = findViewById(R.id.address);
        mLeadEmail = findViewById(R.id.email);

        mSave = findViewById(R.id.save);
        mCancel = findViewById(R.id.cancel);

        mAddAppointment = findViewById(R.id.tv_add_appt);
        mAddAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate(v);
            }
        });
    }

    private void setSaveCancelActions() {
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lead.setName(mLeadName.getText().toString());
                lead.setEmail(mLeadEmail.getText().toString());
                lead.setNumber(mLeadNumber.getText().toString());
                lead.setType(mLeadType);
                lead.setStatus(Lead.Status.NEW);
                lead.setFollowUpAttempt("1");

                new AsyncTask<Lead, Void, Lead>(){

                    @Override
                    protected Lead doInBackground(Lead... params) {
                        Lead lead = params[0];
                        int id = leadDao.insertLead(lead).intValue();
                        lead.setId(id);
                        return lead;
                    }

                    @Override
                    protected void onPostExecute(Lead savedLead) {
                        Log.d(TAG, "Lead added: id - " + savedLead.getName()
                                + ", name: " + savedLead.getName());

                    }
                }.execute(lead);

                backToHome();

            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToHome();
            }
        });
    }

    protected void backToHome(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
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

    @Override
    public void onDone(View view, Date date) {

    }

    @Override
    public void onDone(View view, String date, String time) {

    }
}
