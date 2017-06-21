package com.ubcma.leadster.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.ubcma.leadster.R;

import java.util.List;

/**
 * Created by Yvonne on 6/14/2017.
 */

public class GoalsAdapter extends ArrayAdapter<String> {

    List<String> mLabels;
    Context mContext;

    public GoalsAdapter(@NonNull Context context, @NonNull List<String> labels) {
        super(context, 0, labels);
        mLabels = labels;
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView goalsLabel, goalCount;
        View goalsItemView = convertView;
        if(goalsItemView == null){
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            goalsItemView = inflater.inflate(R.layout.goal_row, parent, false);
        }

        goalsLabel = (TextView) goalsItemView.findViewById(R.id.calls_goal_label);
        goalsLabel.setText(mLabels.get(position));

        goalCount = (TextView) goalsItemView.findViewById(R.id.goal_count);
        goalCount.setText("4");


        return goalsItemView;
    }

    @Override
    public int getCount() {
        return mLabels.size();
    }
}
