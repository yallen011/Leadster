package com.ubcma.leadster.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ubcma.leadster.R;

import static android.R.attr.type;

/**
 * Dialog Fragment to display goals that can be tracked.
 */
public class GoalsDetailsFragment extends DialogFragment {



    private static final String GOAL_CALL_MESSAGE = "Calls per:";
    private static final String GOAL_INTERVIEW_MESSAGE = "Interviews per:";
    private static final String GOAL_PARTY_MESSAGE = "Parties per:";
    private static final String TITLE = "Goal Details";

    public GoalsDetailsFragment() {
        // Required empty public constructor
    }

    public static GoalsDetailsFragment newInstance(String goalType){

        GoalsDetailsFragment gdFragment = new GoalsDetailsFragment();

        if(goalType != null){

            Bundle bundle = new Bundle();
            bundle.putString("goalType", "goalType");
            gdFragment.setArguments(bundle);
        }
        return gdFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goals_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String goalType = getArguments().getString("goalType");
        String message;
        TextView messageTextView = (TextView) view.findViewById(R.id.goal_message);
        EditText goalInput = (EditText) view.findViewById(R.id.goal_input);


        //decide wich message to display based on the goal type
        switch(goalType){
            case "p":
                message = GOAL_PARTY_MESSAGE;
                break;
            case "c":
                message = GOAL_CALL_MESSAGE;
                break;
            case "i":
                message = GOAL_INTERVIEW_MESSAGE;
                break;
            default:
                message = GOAL_CALL_MESSAGE;
                break;
        }
        messageTextView.setText(message);

        //show soft keyboard automatically and request focus
        goalInput.requestFocus();
        getDialog().setTitle(TITLE);
        getDialog().setCanceledOnTouchOutside(true);
    }
}
