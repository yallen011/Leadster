package com.ubcma.leadster.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;

import com.ubcma.leadster.LeadsterApp;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Lead;

import java.util.List;

/**
 * Created by Yvonne on 11/2/2017.
 */

public class LeadViewModel extends ViewModel {

    private final List<Lead> leads;
    private LeadsterDB mDb;

    public LeadViewModel() {
        mDb = LeadsterApp.get().getDB();
        leads = mDb.leadDao().getAllLeads();
    }

    public List<Lead> loadLeads(){
        return leads;
    }
}
