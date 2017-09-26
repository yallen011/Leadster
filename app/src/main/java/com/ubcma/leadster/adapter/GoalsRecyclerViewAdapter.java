package com.ubcma.leadster.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ubcma.leadster.R;
import com.ubcma.leadster.entity.Goal;

import java.util.List;

/**
 * Created by Yvonne on 8/27/2017.
 */

public class GoalsRecyclerViewAdapter extends RecyclerView.Adapter<GoalsRecyclerViewAdapter.GoalsViewHolder> {

    private static final String TAG = GoalsRecyclerViewAdapter.class.getSimpleName();

    private List<Goal> mGoals;

    public GoalsRecyclerViewAdapter(List<Goal> goals) {
        Log.d(TAG, "adapter initialized");
        mGoals = goals;
    }

    @Override
    public GoalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder: inside");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_row, parent, false);
        return new GoalsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoalsViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: position" + position);
        holder.bind(mGoals.get(position));
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mGoals.size());
        return mGoals.size();
    }

    class GoalsViewHolder extends RecyclerView.ViewHolder {

        TextView goalsLabel, goalCount;

        GoalsViewHolder(View itemView) {
            super(itemView);
            goalsLabel = (TextView) itemView.findViewById(R.id.calls_goal_label);
            goalCount = (TextView) itemView.findViewById(R.id.goal_count);
        }

        void bind(Goal goal){
            Log.d(TAG, "bind: " + goal.toString());
            goalsLabel.setText(goal.getGoalTitle());
            goalCount.setText(String.valueOf(goal.getGoalTarget()));
        }
    }

    public List<Goal> getGoals(){
        return mGoals;
    }

//    private class GoalsAsyncTask extends AsyncTask<Void, Void, Void>{
//
//        GoalDao goalDao;
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            goalDao = LeadsterApp.get().getDB().goalDao();
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//        }
//    }
}
