package com.ubcma.leadster.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.ubcma.leadster.R;

/**
 * A simple {@link DialogFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimePickerFragment.OnTimePickerListener} interface
 * to handle interaction events.
 */
public class TimePickerFragment extends DialogFragment {


    private OnTimePickerListener mListener;
    private View mClickedView;
    private TimePicker mTimePicker;
    private String mSelectedDate;

    public void setClickedView(View view){
        mClickedView = view;
    }

    public void setSelectedDate(String selectedDate){
        mSelectedDate = selectedDate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_time_picker_dialog, null);

        mTimePicker = (TimePicker) v.findViewById(R.id.time_picker);
        return new android.support.v7.app.AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int hour = 0;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                    hour = mTimePicker.getHour();
                                }else{
                                    hour = mTimePicker.getCurrentHour();
                                }
                                int minute = 0;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                                    minute = mTimePicker.getMinute();
                                }else{
                                    minute = mTimePicker.getCurrentMinute();
                                }
                                mListener = (OnTimePickerListener) getActivity();
                                mListener.onDone(mClickedView, mSelectedDate, updateTime(hour,minute));
                                dismiss();
                            }
                        })
                .create();
    }

    public interface OnTimePickerListener {

        /**method to return the time selected by the user**/
        void onDone(View view, String date, String time);
    }

    private String updateTime(int hours, int mins) {

        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";

        String minutes = "";
        if (mins < 10)
            minutes = "0" + mins;
        else
            minutes = String.valueOf(mins);

        String myTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        return myTime;
    }

}
