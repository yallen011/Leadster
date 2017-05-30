package com.ubcma.leadster.activity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ubcma.leadster.R;
import com.ubcma.leadster.adapter.LeadRecyclerViewAdapter;
import com.ubcma.leadster.vo.Lead;

import java.util.ArrayList;
import java.util.List;

public class LeadListActivity extends AppCompatActivity {

    private List<Lead> leadList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LeadRecyclerViewAdapter mLeadAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewLeadActivity.class);
                startActivity(intent);
                
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.lead_recycler_view);

        mLeadAdapter = new LeadRecyclerViewAdapter(leadList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mLeadAdapter);

        prepareLeadData();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lead_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void prepareLeadData() {

        Lead lead1 = new Lead("Adrian Knowles", "770-123-1234", "Recruit");
        leadList.add(lead1);

        Lead lead2 = new Lead("Tim Duncan", "770-123-1235", "Recruit");
        leadList.add(lead2);

        Lead lead3 = new Lead("Labron James", "770-123-1236", "Facial");
        leadList.add(lead3);

        Lead lead4 = new Lead("Kate Middleton", "770-123-1237", "Facial");
        leadList.add(lead4);

        Lead lead5 = new Lead("John Snow", "770-123-1238", "Recruit");
        leadList.add(lead5);

        Lead lead6 = new Lead("Peter Pan", "770-123-1239", "Facial");
        leadList.add(lead6);

        Lead lead7 = new Lead("Carl Moody", "770-123-1230", "Facial");
        leadList.add(lead7);

        Lead lead8 = new Lead("Jack Black", "770-123-1231", "Recruit");
        leadList.add(lead8);

        mLeadAdapter.notifyDataSetChanged();
    }

}
