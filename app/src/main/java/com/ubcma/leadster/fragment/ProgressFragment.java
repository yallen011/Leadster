package com.ubcma.leadster.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubcma.leadster.R;
import com.ubcma.leadster.adapter.ProgressAdapter;
import com.ubcma.leadster.entity.Progress;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ProgressFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_progress, container, false);

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.lead_recycler_view);


        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter
        mAdapter = new ProgressAdapter(getProgressList());
        recyclerView.setAdapter(mAdapter);


        return rootView;
    }

    private List<Progress> getProgressList(){

        List<Progress> progressList = new ArrayList<>();
        progressList.add(new Progress(3, 1, 1, "Interviews"));
        progressList.add(new Progress(10, 10, 2, "Calls"));
        progressList.add(new Progress(10, 5, 3, "Parties"));

        return progressList;

    }

}
