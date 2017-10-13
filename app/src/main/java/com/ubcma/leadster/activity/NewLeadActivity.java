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
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Lead;

import static android.R.attr.fragment;

public class NewLeadActivity extends AppCompatActivity {

    private static final String TAG = NewLeadActivity.class.getSimpleName();
    LeadDao leadDao;
    Lead lead;
    String mLeadType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_lead);

        leadDao = LeadsterApp.get().getDB().leadDao();
        lead = new Lead();

        Spinner spinner = (Spinner) findViewById(R.id.phone_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.phone_type_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //TODO: add radio button functionality
        RadioGroup leadTypeGroup = (RadioGroup)findViewById(R.id.lead_type_rg);
        leadTypeGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton leadTypeBtn = (RadioButton) findViewById(checkedId);
                mLeadType = leadTypeBtn.getText().toString();
            }
        });

        final TextView leadName = (TextView) findViewById(R.id.name);
        final TextView leadNumber = (TextView) findViewById(R.id.pNum);
        TextView leadAddress = (TextView) findViewById(R.id.address);
        final TextView leadEmail = (TextView) findViewById(R.id.email);

        Button save = (Button) findViewById(R.id.save);
        Button cancel = (Button) findViewById(R.id.cancel);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lead.setName(leadName.getText().toString());
                lead.setEmail(leadEmail.getText().toString());
                lead.setNumber(leadNumber.getText().toString());
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



                /*TODO: pass data back to the main activity that hosts the lead list fragment.
                  send a flag that will be sent to fragment that calls a method to pull all the leads from
                  the database. or send data through the instance method that passes the flag on create
                  of the fragment
                */
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
