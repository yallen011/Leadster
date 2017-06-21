package com.ubcma.leadster.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ubcma.leadster.R;
import com.ubcma.leadster.adapter.GoalsAdapter;

import java.util.ArrayList;
import java.util.List;

public class GoalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_goals);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_goals);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ListView goalsListView = (ListView) findViewById(R.id.goals_list);
        GoalsAdapter adapter = new GoalsAdapter(this, getLabels());
        goalsListView.setAdapter(adapter);

        //TODO: get empty list to display message
        goalsListView.setEmptyView(findViewById(R.id.emptyElement));
    }

    public List<String> getLabels(){

        List<String> labels = new ArrayList<>();
        labels.add("Calls per week");
        labels.add("Interviews per week");
        labels.add("Appointments per week");
        labels.add("Recuits per week");

        return labels;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            //no menu resource file needed for up navigation
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
