package com.ubcma.leadster.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.ubcma.leadster.LeadsterApp;
import com.ubcma.leadster.R;
import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Lead;

public class NewLeadActivity extends AppCompatActivity {

    LeadDao leadDao;
    Lead lead;
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
        RadioGroup leadTypeGroup;

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
                //lead.setType();
            }
        });


    }
}
