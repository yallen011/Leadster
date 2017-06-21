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

    ArrayAdapter<CharSequence> mSpinnerAdapter;
    List<String> mLabels;

    public GoalsAdapter(@NonNull Context context, @NonNull List<String> labels) {
        super(context, 0, labels);
        mLabels = labels;

        mSpinnerAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.goal_interval_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        mSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View goalsItemView = convertView;
        if(goalsItemView != null){
            goalsItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.goal_row, parent, false);
        }

        TextView goalsLabel = (TextView) goalsItemView.findViewById(R.id.calls_goal_label);
        goalsLabel.setText(mLabels.get(position));

        Spinner goalsSpinner = (Spinner) goalsItemView.findViewById(R.id.calls_goal_spinner);

        // Apply the mSpinnerAdapter to the spinner
        goalsSpinner.setAdapter(mSpinnerAdapter);
        return goalsItemView;
    }
}
