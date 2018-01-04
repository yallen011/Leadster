package com.ubcma.leadster.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.ubcma.leadster.R;
import com.ubcma.leadster.entity.Appointment;
import com.ubcma.leadster.entity.Lead;
import com.ubcma.leadster.fragment.DatePickerFragment;
import com.ubcma.leadster.fragment.TimePickerFragment;
import com.ubcma.leadster.viewmodel.AppointmentViewModel;
import com.ubcma.leadster.viewmodel.LeadViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewLeadActivity extends AppCompatActivity implements DatePickerFragment.OnDatePickerListener, TimePickerFragment.OnTimePickerListener {

    private static final String TAG = NewLeadActivity.class.getSimpleName();
    Lead lead;
    String mLeadType;
    TextView mLeadName, mLeadNumber, mLeadEmail, mAddAppointment, mAddAppointmentTv;
    Button mSave, mCancel;
    LinearLayout mAddAppointmentContainer;

    AppointmentViewModel mApptViewModel;
    LeadViewModel mLeadViewModel;

    List<Appointment> mAppointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lead);

        lead = new Lead();

        mApptViewModel = ViewModelProviders.of(this).get(AppointmentViewModel.class);
        mLeadViewModel = ViewModelProviders.of(this).get(LeadViewModel.class);

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

        mAddAppointmentContainer = findViewById(R.id.add_appt_container);

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


               int id =  mLeadViewModel.saveLead(lead);
                lead.setId(id);

                Log.d(TAG, "Lead added: id - " + lead.getId()
                        + ", name: " + lead.getName());

                if(mAppointments != null){
                    saveAppointments(lead);
                }

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

    private void saveAppointments(Lead lead) {

        for(Appointment appointment: mAppointments){
            appointment.setLeadId(lead.getId());

            // TODO: 11/13/2017 change this to be location of the appointment; may require a new dialog
            appointment.setLocation("Customer's House");
            if(lead.getType().equals("Team Member")){
                appointment.setLeadTypeId(1);
            }else{
                appointment.setLeadTypeId(2);
            }
        }
        mApptViewModel.saveAllAppointments(mAppointments);
    }

    protected void backToHome(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    /**change date to what was selected by the user
     * @param clickedView view clicked by user to select the date
     * @param date date selected by the user
     * **/
    @Override
    public void onDone(View clickedView, Date date) {
        String formattedDate = formatDate(date);

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setClickedView(clickedView);
        timePickerFragment.setSelectedDate(formattedDate);
        timePickerFragment.show(getSupportFragmentManager(), "time");

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
        addAppointment(formattedDateTime);

        int index = mAddAppointmentContainer.indexOfChild(mAddAppointmentTv);

        TextView newAppointment = new TextView(this);

        //added LayoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        newAppointment.setLayoutParams(params);
        newAppointment.setPadding(8,4,0,4);
        mAddAppointmentContainer.addView(newAppointment, index);


        setClickedText(newAppointment, formattedDateTime);
    }

    private void addAppointment(String formattedDateTime) {

        Appointment appt = new Appointment();
        appt.setDate(formattedDateTime);

        Log.d(TAG, "onDone: Saved Appt: Date" + appt.getDate());
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
