package com.ubcma.leadster.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ubcma.leadster.R;
import com.ubcma.leadster.entity.Progress;

import java.util.List;

/**
 * Created by Yvonne on 6/11/2017.
 */

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ProgressViewHolder> {

    private List<Progress> mProgressList;

    //sets the list of data to be displayed
    public ProgressAdapter(List<Progress> progressList){
        this.mProgressList = progressList;
    }


    @Override
    public ProgressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //get the cardview row
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.progress_list_row, parent, false);

        return new ProgressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProgressViewHolder holder, int position) {

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mProgressList.size();
    }

    //retrieves the views that are in the cardview
    public class ProgressViewHolder extends RecyclerView.ViewHolder{

        TextView mTitle, mPercent, mProgress;
        ProgressBar mProgressBar;
        public ProgressViewHolder(View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.progress_title);
            mPercent = itemView.findViewById(R.id.progress_percent);
            mProgress = itemView.findViewById(R.id.progress_text);
            mProgressBar = itemView.findViewById(R.id.progress_bar);

        }

        public void bind(int position){

            Progress progress = mProgressList.get(position);
            String progressString = progress.getProgress()+"/" +progress.getGoal()
                    +" " + progress.getGoalTitle();
            mTitle.setText(progress.getGoalTitle());
            mProgress.setText(progressString);
            mPercent.setText(progress.getPercent());

            //set progress bar attributes
            mProgressBar.setMax(progress.getGoal());
            mProgressBar.setProgress(progress.getProgress());
        }
    }
}
