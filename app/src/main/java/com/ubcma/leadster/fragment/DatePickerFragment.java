package com.ubcma.leadster.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.ubcma.leadster.R;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A simple {@link DialogFragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnDatePickerListener} interface
 * to handle interaction events.
 */
public class DatePickerFragment extends DialogFragment {

    private OnDatePickerListener mListener;
    private DatePicker datePicker;
    private View clickedView;

    public void setClickedView(View view){
        clickedView = view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.fragment_date_picker_dialog,null);
        datePicker = (DatePicker) v.findViewById(R.id.date_picker);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int year = datePicker.getYear();
                                int mon = datePicker.getMonth();
                                int day = datePicker.getDayOfMonth();
                                Date date = new GregorianCalendar(year,mon,day).getTime();
                                mListener = (OnDatePickerListener) getActivity();
                                mListener.onDone(clickedView, date);
                                dismiss();
                            }
                        })
                .create();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnDatePickerListener {

        /**method to return the date selected by the user**/
        void onDone(View view, Date date);
    }
}
