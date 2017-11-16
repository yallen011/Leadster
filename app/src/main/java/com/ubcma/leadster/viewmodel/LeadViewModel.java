package com.ubcma.leadster.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.ubcma.leadster.LeadsterApp;
import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Lead;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.R.attr.id;
import static android.content.ContentValues.TAG;

/**
 * Created by Yvonne on 11/2/2017.
 */

public class LeadViewModel extends ViewModel {

    //private final List<Lead> leads;
    private LeadDao mLeadDao;

    public LeadViewModel() {
        mLeadDao = LeadsterApp.get().getDB().leadDao();
        //leads = mLeadDao.getAllLeads();
    }

//    public List<Lead> loadLeads(){
//        return leads;
//    }

    public int saveLead(Lead lead) {

        int id = 0;
        try {
            id = new AsyncTask<Lead, Integer, Integer>(){

                 @Override
                 protected Integer doInBackground(Lead... params) {
                     Lead lead = params[0];
                     int id = mLeadDao.insertLead(lead).intValue();
                     lead.setId(id);
                     return id;
                 }

                 @Override
                 protected void onPostExecute(Integer savedLeadId) {
                     super.onPostExecute(savedLeadId);
                 }
             }.execute(lead).get();
            return id;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return id;
    }
}
