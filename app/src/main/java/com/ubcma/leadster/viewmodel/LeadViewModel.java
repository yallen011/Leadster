package com.ubcma.leadster.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.ubcma.leadster.LeadsterApp;
import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.entity.Lead;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Yvonne on 11/2/2017.
 */

public class LeadViewModel extends ViewModel {

    private final LiveData<List<Lead>> leads;
    private LeadDao mLeadDao;

    public LeadViewModel() {
        mLeadDao = LeadsterApp.get().getDB().leadDao();
        leads = mLeadDao.getAllLeads();
    }

    public LiveData<List<Lead>> loadLeads(){
        return leads;
    }

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

    public boolean deleteLead(Lead lead){

        int rowsDeleted = 0;

        try {
            rowsDeleted = new AsyncTask<Lead, Void, Integer>(){

                @Override
                protected Integer doInBackground(Lead... params) {
                    Lead lead = params[0];
                    int rowsDeleted = mLeadDao.deleteLead(lead);
                    return rowsDeleted;
                }
                @Override
                protected void onPostExecute(Integer rowsDeleted) {
                    super.onPostExecute(rowsDeleted);
                }

            }.execute(lead).get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return (rowsDeleted != 0);

    }
}
