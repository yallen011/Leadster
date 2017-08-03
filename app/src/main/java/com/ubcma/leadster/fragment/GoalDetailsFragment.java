package com.ubcma.leadster.fragment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ubcma.leadster.R;

/**
 * Dialog Fragment to display goals that can be tracked.
 */
public class GoalDetailsFragment extends DialogFragment {



    private static final String GOAL_CALL_MESSAGE = "Calls per:";
    private static final String GOAL_INTERVIEW_MESSAGE = "Interviews per:";
    private static final String GOAL_PARTY_MESSAGE = "Parties per:";
    private static final String TITLE = "Goal Details";
    private Activity mContext;

    public GoalDetailsFragment() {
        // Required empty public constructor
    }

    public static GoalDetailsFragment newInstance(String goalType){

        GoalDetailsFragment gdFragment = new GoalDetailsFragment();

        if(goalType != null){

            Bundle bundle = new Bundle();
            bundle.putString("goalType", goalType);
            gdFragment.setArguments(bundle);
        }
        return gdFragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater inflater = mContext.getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.fragment_goals_details, null);

        //build dialog
        builder.setView(dialogView)
                .setTitle(TITLE)
                .setPositiveButton(R.string.dialog_done, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO create a action on call back
                    }
                });

        Bundle bundle = getArguments();
        String goalType = getArguments().getString("goalType");
        String message;
        TextView messageTextView = (TextView) dialogView.findViewById(R.id.goal_message);
        EditText goalInput = (EditText) dialogView.findViewById(R.id.goal_input);


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

        //AlertDialog dialog = builder.create();
        return builder.create();
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_goals_details, container, false);
//    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        String goalType = getArguments().getString("goalType");
//        String message;
//        TextView messageTextView = (TextView) view.findViewById(R.id.goal_message);
//        EditText goalInput = (EditText) view.findViewById(R.id.goal_input);
//
//
//        //decide wich message to display based on the goal type
//        switch(goalType){
//            case "p":
//                message = GOAL_PARTY_MESSAGE;
//                break;
//            case "c":
//                message = GOAL_CALL_MESSAGE;
//                break;
//            case "i":
//                message = GOAL_INTERVIEW_MESSAGE;
//                break;
//            default:
//                message = GOAL_CALL_MESSAGE;
//                break;
//        }
//        messageTextView.setText(message);
//
//        //show soft keyboard automatically and request focus
//        goalInput.requestFocus();
//        getDialog().setTitle(TITLE);
//        getDialog().setCanceledOnTouchOutside(true);
//    }
}
