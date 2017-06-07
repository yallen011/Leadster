package com.ubcma.leadster.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ubcma.leadster.R;
import com.ubcma.leadster.activity.NewLeadActivity;
import com.ubcma.leadster.adapter.LeadRecyclerViewAdapter;
import com.ubcma.leadster.vo.Lead;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.AppCompatDrawableManager.get;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeadListFragment extends Fragment {

    private List<Lead> leadList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LeadRecyclerViewAdapter mLeadAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lead_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.lead_recycler_view);

        mLeadAdapter = new LeadRecyclerViewAdapter(leadList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mLeadAdapter);

        prepareLeadData();

        return rootView;
    }

    private void prepareLeadData() {

        Lead lead1 = new Lead("Adrian Knowles", "770-123-1234", "Recruit");
        leadList.add(lead1);

        Lead lead2 = new Lead("Tim Duncan", "770-123-1235", "Recruit");
        leadList.add(lead2);

        Lead lead3 = new Lead("Labron James", "770-123-1236", "Facial");
        leadList.add(lead3);

        Lead lead4 = new Lead("Kate Middleton", "770-123-1237", "Facial");
        leadList.add(lead4);

        Lead lead5 = new Lead("John Snow", "770-123-1238", "Recruit");
        leadList.add(lead5);

        Lead lead6 = new Lead("Peter Pan", "770-123-1239", "Facial");
        leadList.add(lead6);

        Lead lead7 = new Lead("Carl Moody", "770-123-1230", "Facial");
        leadList.add(lead7);

        Lead lead8 = new Lead("Jack Black", "770-123-1231", "Recruit");
        leadList.add(lead8);

        mLeadAdapter.notifyDataSetChanged();
    }

}
