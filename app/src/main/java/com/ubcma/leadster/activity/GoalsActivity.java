package com.ubcma.leadster.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ubcma.leadster.R;
import com.ubcma.leadster.adapter.GoalsAdapter;
import com.ubcma.leadster.fragment.GoalDetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class GoalsActivity extends AppCompatActivity implements GoalDetailsFragment.OnGoalSelectedListener {

    private static final String INTERVIEW_FLAG = "i";
    private static final String CALL_FLAG = "c";
    private static final String PARTY_FLAG = "p";
    private static final String RECRUIT_FLAG = "r";
    private static final int FAB_ROTATE_ANGLE = 45;
    private static final int FAB_ORIGIN_ANGLE = 0;

    FloatingActionButton fab, fab_interviews, fab_calls, fab_parties, fab_recruits;
    Toolbar toolbar;
    ListView goalsListView;
    boolean isFABOpen=false;
    FragmentManager fragmentManager;
    GoalDetailsFragment goalDetailsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        initializeViews();
        initializeFragment();

        //set the toolbar and add up navigation
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //method to set the OnClickListeners for each fab button in the fab menu
        setFabMenuOnClickListeners();

        //create list that displays goals the user wish to track
        createGoalsList();
    }

    /**
     * Handles up navigation from the goals screen.
     * @param item
     * @return
     */
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

    /**
     * Closes the fab menu if it is opened if the user presses the back button.
     */
    @Override
    public void onBackPressed() {
        if(isFABOpen){
            closeFabMenu();
        }else{
            super.onBackPressed();
        }
    }

    private void createGoalsList() {
        //set array adapter for the goals list
        GoalsAdapter adapter = new GoalsAdapter(this, getLabels());
        goalsListView.setAdapter(adapter);

        //TODO: get empty list to display message
        goalsListView.setEmptyView(findViewById(R.id.emptyElement));
    }

    // TODO: 8/20/2017 add recruit fab to fab menu 
    private void setFabMenuOnClickListeners() {
        //open and close fab menu
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFabMenu();
                }else {
                    closeFabMenu();
                }
            }
        });

        fab_parties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalDetailsFragment = GoalDetailsFragment.newInstance(PARTY_FLAG);
                //fragmentManager.beginTransaction().add(R.id.goal_details_dialog, goalDetailsFragment).commit();
                goalDetailsFragment.show(fragmentManager, "GoalDetailsFragment");

            }
        });

        fab_interviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalDetailsFragment = GoalDetailsFragment.newInstance(INTERVIEW_FLAG);
                //fragmentManager.beginTransaction().add(R.id.goal_details_dialog, goalDetailsFragment).commit();
                goalDetailsFragment.show(fragmentManager, "GoalDetailsFragment");

            }
        });

        fab_calls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalDetailsFragment = GoalDetailsFragment.newInstance(CALL_FLAG);
                //fragmentManager.beginTransaction().add(R.id.goal_details_dialog, goalDetailsFragment).commit();
                goalDetailsFragment.show(fragmentManager, "GoalDetailsFragment");

            }
        });

        fab_recruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goalDetailsFragment = GoalDetailsFragment.newInstance(RECRUIT_FLAG);
                //fragmentManager.beginTransaction().add(R.id.goal_details_dialog, goalDetailsFragment).commit();
                goalDetailsFragment.show(fragmentManager, "GoalDetailsFragment");

            }
        });
    }

    private void initializeFragment() {
        fragmentManager = getSupportFragmentManager();
        goalDetailsFragment = GoalDetailsFragment.newInstance("");
    }

    /**
     * Method to initialize all views in the activity
     */
    private void initializeViews() {

        fab_recruits = (FloatingActionButton) findViewById(R.id.fab_recruits);
        fab_calls = (FloatingActionButton) findViewById(R.id.fab_calls);
        fab_interviews = (FloatingActionButton) findViewById(R.id.fab_interviews);
        fab_parties = (FloatingActionButton) findViewById(R.id.fab_parties);
        fab = (FloatingActionButton) findViewById(R.id.fab_goals);

        toolbar = (Toolbar) findViewById(R.id.toolbar_goals);
        goalsListView = (ListView) findViewById(R.id.goals_list);
    }

    /**
     * show the fab menu if it is closed
     */
    private void showFabMenu() {
        isFABOpen = true;

        fab.animate().rotationBy(FAB_ROTATE_ANGLE);
        fab_calls.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        fab_interviews.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
        fab_parties.animate().translationY(-getResources().getDimension(R.dimen.standard_145));
        fab_recruits.animate().translationY(-getResources().getDimension(R.dimen.standard_190));
    }

    /**
     * hide the fab menu if it is opened
     */
    private void closeFabMenu() {
        isFABOpen = false;
        fab.animate().rotationBy(-FAB_ROTATE_ANGLE);
        fab_calls.animate().translationY(FAB_ORIGIN_ANGLE);
        fab_interviews.animate().translationY(FAB_ORIGIN_ANGLE);
        fab_parties.animate().translationY(FAB_ORIGIN_ANGLE);
        fab_recruits.animate().translationY(FAB_ORIGIN_ANGLE);
    }

    //sample list data
    public List<String> getLabels(){

        List<String> labels = new ArrayList<>();
        labels.add("Calls per week");
        labels.add("Interviews per week");
        labels.add("Appointments per week");
        labels.add("Team Members per week");
        labels.add("Recruits per week");

        return labels;
    }

    @Override
    public void onGoalSelected(String goal, String frequency, String goalType) {
        Toast.makeText(this, "Goal:" + goal + ", Frequency:" + frequency + ", Goal Type:" + goalType
                , Toast.LENGTH_SHORT).show();
    }
}
