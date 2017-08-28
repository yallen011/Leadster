package com.ubcma.leadster.adapter;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ubcma.leadster.entity.Goal;

/**
 * Created by Yvonne on 8/27/2017.
 */

public class GoalsRecyclerviewAdapter extends RecyclerView.Adapter<GoalsRecyclerviewAdapter.GoalsViewHolder> {
    @Override
    public GoalsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(GoalsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class GoalsViewHolder extends RecyclerView.ViewHolder {

        public GoalsViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(Goal goal){

        }
    }

    class GoalsAsyncTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
