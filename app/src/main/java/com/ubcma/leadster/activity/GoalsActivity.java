package com.ubcma.leadster.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ubcma.leadster.LeadsterApp;
import com.ubcma.leadster.R;
import com.ubcma.leadster.adapter.GoalsRecyclerViewAdapter;
import com.ubcma.leadster.dao.GoalDao;
import com.ubcma.leadster.entity.Goal;
import com.ubcma.leadster.fragment.GoalDetailsFragment;

import java.util.Arrays;
import java.util.List;

public class GoalsActivity extends AppCompatActivity implements GoalDetailsFragment.OnGoalSelectedListener {

    private static final String LOG_TAG = GoalsActivity.class.getSimpleName();

    private static final String INTERVIEW_FLAG = "i";
    private static final String CALL_FLAG = "c";
    private static final String PARTY_FLAG = "p";
    private static final String RECRUIT_FLAG = "r";
    private static final int FAB_ROTATE_ANGLE = 45;
    private static final int FAB_ORIGIN_ANGLE = 0;


    FloatingActionButton fab, fab_interviews, fab_calls, fab_parties, fab_recruits;
    Toolbar toolbar;
    RecyclerView goalsListView;
    FragmentManager fragmentManager;
    GoalDetailsFragment goalDetailsFragment;
    GoalsRecyclerViewAdapter adapter;
    TextView noGoalsTxt;
    GoalDao goalDao;

    private boolean showGoalsList = false;
    private boolean isFABOpen=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals);

        goalDao = LeadsterApp.get().getDB().goalDao();
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


    // Handles up navigation from the goals screen.
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

        new AsyncTask<Void, Void, List<Goal>>(){

            @Override
            protected List<Goal> doInBackground(Void... params) {
                List<Goal> returnedGoals = goalDao.getAllGoals();
                return returnedGoals;
            }

            @Override
            protected void onPostExecute(List<Goal> returnedGoals) {

                //check your goals list is empty or not
                if(returnedGoals.size() > 0){

                    //make the goals list visible if there is data
                    showGoalsList = true;

                    //set array adapter for the goals list
                    adapter = new GoalsRecyclerViewAdapter(returnedGoals);
                    goalsListView.setAdapter(adapter);
                }
                toggleGoalsList();
            }
        }.execute();
    }

    private void toggleGoalsList() {

        if(showGoalsList){
            goalsListView.setVisibility(View.VISIBLE);
            noGoalsTxt.setVisibility(View.GONE);
        }else{

            //if there is no data display empty list text.
            goalsListView.setVisibility(View.GONE);
            noGoalsTxt.setVisibility(View.VISIBLE);
        }
    }


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

        fab_recruits = findViewById(R.id.fab_recruits);
        fab_calls = findViewById(R.id.fab_calls);
        fab_interviews = findViewById(R.id.fab_interviews);
        fab_parties = findViewById(R.id.fab_parties);
        fab = findViewById(R.id.fab_goals);

        toolbar = findViewById(R.id.toolbar_goals);
        goalsListView = findViewById(R.id.goals_list);

        noGoalsTxt = findViewById(R.id.emptyElement);
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

    @Override
    public void onGoalSelected(String goalTarget, String frequency, String goalType) {

        Goal goalObj = new Goal(goalType, frequency, new Integer(goalTarget));

        Log.i(LOG_TAG, "Goal before db insert:ID - " + goalObj.getId()
                + ", Target - " + goalObj.getGoalTarget()
                + ", Frequency - " + goalObj.getGoalFrequency()
                + ", Goal Type - " + goalObj.getGoalType()
                + ", Goal Title - " + goalObj.getGoalTitle());

        new AsyncTask<Goal, Void, Goal>(){

            @Override
            protected Goal doInBackground(Goal...params) {

                Goal goalObj = params[0];
                Goal savedGoal;
                goalObj.setId(Integer.valueOf(goalDao.insertGoal(goalObj).toString()));

                savedGoal = goalDao.getGoal(goalObj.getId());
                //return the index of the persisted goal.
                return  savedGoal;
            }

            @Override
            protected void onPostExecute(Goal savedGoal) {

//                Toast.makeText(this, "Goal after DB insert:" + savedGoal.getGoalTarget()
//                                + ", Frequency:" + savedGoal.getGoalFrequency()
//                                + ", Goal Type:" + savedGoal.getGoalType()
//                        , Toast.LENGTH_SHORT).show();

                Log.d(LOG_TAG, "Goal after db insert: ID - " + savedGoal.getId()
                        + ", Target - " + savedGoal.getGoalTarget()
                        + ", Frequency - " + savedGoal.getGoalFrequency()
                        + ", Goal Type - " + savedGoal.getGoalType()
                        + ", Goal Title - " + savedGoal.getGoalTitle());

                //check to see if the list has been created before notifying data has changed
                if(adapter.getGoals() == null){
                    List<Goal> dataset = Arrays.asList(savedGoal);
                    adapter.setGoals(dataset);
                    //now that there is data, show the list of goals
                    showGoalsList = true;
                    toggleGoalsList();
                }else{
                    addGoalToList(savedGoal);
                }
                adapter.notifyDataSetChanged();
            }
        }.execute(goalObj);

    }

    private void addGoalToList(Goal savedGoal) {
        adapter.getGoals().add(savedGoal);

        if(adapter.getGoals().size() == 0){
            showGoalsList = true;
            toggleGoalsList();
        }

    }
}
