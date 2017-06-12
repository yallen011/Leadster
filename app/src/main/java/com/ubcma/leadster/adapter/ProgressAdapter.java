package com.ubcma.leadster.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ubcma.leadster.R;
import com.ubcma.leadster.vo.Progress;

import java.util.List;

/**
 * Created by Yvonne on 6/11/2017.
 */

public class ProgressAdapter extends RecyclerView.Adapter<ProgressAdapter.ViewHolder> {

    private List<Progress> mProgressList;


    //retrieves the views that are in the cardview
    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTitle, mPercent, mProgress;
        ProgressBar mProgressBar;
        public ViewHolder(View itemView) {
            super(itemView);

            mTitle = (TextView) itemView.findViewById(R.id.progress_title);
            mPercent = (TextView) itemView.findViewById(R.id.progress_percent);
            mProgress = (TextView) itemView.findViewById(R.id.progress_text);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar);

        }
    }

    //sets the list of data to be displayed
    public ProgressAdapter(List<Progress> progressList){
        this.mProgressList = progressList;
    }


    @Override
    public ProgressAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //get the cardview row
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.progress_list_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Progress progress = mProgressList.get(position);
        String progressString = progress.getProgress()+"/" +progress.getGoal()
                +" " + progress.getGoalTitle();
        holder.mTitle.setText(progress.getGoalTitle());
        holder.mProgress.setText(progressString);
        holder.mPercent.setText(progress.getPercent());

        //set progress bar attributes
        holder.mProgressBar.setMax(progress.getGoal());
        holder.mProgressBar.setProgress(progress.getProgress());

    }

    @Override
    public int getItemCount() {
        return mProgressList.size();
    }
}
