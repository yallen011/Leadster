package com.ubcma.leadster.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ubcma.leadster.LeadsterApp;
import com.ubcma.leadster.R;
import com.ubcma.leadster.adapter.LeadRecyclerViewAdapter;
import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.entity.Lead;
import com.ubcma.leadster.viewmodel.LeadViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeadListFragment extends Fragment {

    private static final String TAG = LeadListFragment.class.getSimpleName();
    //private List<Lead> leadList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView noLeadsTxt;
    private LeadRecyclerViewAdapter mLeadAdapter;
    private LeadDao leadDao;
    private boolean showList = false;
    LeadViewModel mLeadViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: entered onCreateView");
        mLeadViewModel = ViewModelProviders.of(this).get(LeadViewModel.class);
        leadDao = LeadsterApp.get().getDB().leadDao();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lead_list, container, false);

        recyclerView = rootView.findViewById(R.id.lead_recycler_view);

        noLeadsTxt = rootView.findViewById(R.id.lead_empty_element);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        prepareLeadData();

        return rootView;
    }

    private void prepareLeadData() {


        mLeadViewModel.loadLeads().observe(this, new Observer<List<Lead>>() {
            @Override
            public void onChanged(@Nullable List<Lead> leads) {
                if(leads.size() > 0){
                    showList = true;
                    if(mLeadAdapter == null){
                        mLeadAdapter = new LeadRecyclerViewAdapter(leads);
                        recyclerView.setAdapter(mLeadAdapter);
                    }else{
                        mLeadAdapter.setLeadsList(leads);
                    }
                }
                toggleLeadsList();
            }
        });
    }

    private void toggleLeadsList(){

        if(showList){
            recyclerView.setVisibility(View.VISIBLE);
            noLeadsTxt.setVisibility(View.GONE);
        }else {
            noLeadsTxt.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    public void updateAdapter(){
        mLeadAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(TAG, "onResume: entered onResume");
        
    }
}
