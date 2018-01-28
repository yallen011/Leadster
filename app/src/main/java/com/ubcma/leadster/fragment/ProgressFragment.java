package com.ubcma.leadster.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ubcma.leadster.R;
import com.ubcma.leadster.adapter.ProgressAdapter;
import com.ubcma.leadster.entity.Progress;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressFragment extends Fragment {

    public static final String TAG = ProgressFragment.class.getSimpleName();
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private TextView noProgressTxt;
    private boolean showList = true;

    public ProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_progress, container, false);

        recyclerView = rootView.findViewById(R.id.progress_recycler_view);
        noProgressTxt = rootView.findViewById(R.id.progress_empty_element);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new ProgressAdapter(getProgressList());
        recyclerView.setAdapter(mAdapter);

        toggleProgressList();

        return rootView;
    }

    private void toggleProgressList(){

        if(showList){
            recyclerView.setVisibility(View.VISIBLE);
            noProgressTxt.setVisibility(View.GONE);
        }else {
            noProgressTxt.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    private List<Progress> getProgressList(){

        List<Progress> progressList = new ArrayList<>();
        progressList.add(new Progress(3, 1, 1, "Interviews"));
        progressList.add(new Progress(10, 10, 2, "Calls"));
        progressList.add(new Progress(10, 5, 3, "Parties"));

        return progressList;

    }

}
