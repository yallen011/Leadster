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

public class GoalsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = GoalsRecyclerViewAdapter.class.getSimpleName();

    List<Goal> mGoals;
    private final int EMPTY=0, GOAL=1;

    public GoalsRecyclerViewAdapter(List<Goal> goals) {
        Log.d(TAG, "adapter initialized");
        mGoals = goals;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_row, parent, false);

        Log.d(TAG, "onCreateViewHolder: inside");
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View emptyView = inflater.inflate(R.layout.empty_view, parent, false);

        Log.d(TAG, "onCreateViewHolder: viewType" + viewType);

        switch (viewType){
            case EMPTY:
                viewHolder = new EmptyViewHolder(emptyView);
            break;
            case GOAL:
                View goalsView = inflater.inflate(R.layout.goal_row, parent, false);
                viewHolder = new GoalsViewHolder(goalsView);
                break;
            default:
                viewHolder = new EmptyViewHolder(emptyView);
                break;
        }

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: inside");
        EmptyViewHolder emptyViewHolder= (EmptyViewHolder) holder;

        switch (holder.getItemViewType()){
            case GOAL:
                GoalsViewHolder goalsViewHolder = (GoalsViewHolder) holder;
                goalsViewHolder.bind(goalsViewHolder, position);
                break;
            case EMPTY:
                emptyViewHolder.bind(emptyViewHolder, position);
                break;
            default:
                emptyViewHolder.bind(emptyViewHolder, position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        Log.d(TAG, "getItemViewType: inside");

        if(mGoals.size()== EMPTY){
            return EMPTY;
        }else{
            return GOAL;
        }
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + mGoals.size());
        return mGoals.size();
    }

    public class GoalsViewHolder extends RecyclerView.ViewHolder {

        TextView goalsLabel, goalCount;
        public GoalsViewHolder(View itemView) {
            super(itemView);
            goalsLabel = (TextView) itemView.findViewById(R.id.calls_goal_label);
            goalCount = (TextView) itemView.findViewById(R.id.goal_count);
        }

        public void bind(GoalsViewHolder vh, int position){
            Goal goal = mGoals.get(position);
            vh.goalsLabel.setText(goal.getGoalTitle());
            vh.goalCount.setText(goal.getGoalFrequency());
        }
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder{

        TextView emptyTextView;
        public EmptyViewHolder(View itemView) {
            super(itemView);
            emptyTextView = (TextView) itemView.findViewById(R.id.emptyElement);
        }

        public void bind(EmptyViewHolder vh, int position){

            vh.emptyTextView.setText("No Goals Added");
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
