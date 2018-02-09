package com.ubcma.leadster.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ubcma.leadster.entity.Lead;
import com.ubcma.leadster.entity.LeadWithAppt;

import java.util.List;

/**
 * Created by Yvonne on 8/20/2017.
 */

@Dao
public interface LeadDao {

    @Query("SELECT * FROM lead")
    LiveData<List<Lead>> getAllLeads();

    @Query("SELECT * FROM lead WHERE :id = id")
    Lead getLead(int id);

    @Query("SELECT * FROM lead Where :id = id")
    LeadWithAppt loadApptByLeadId(int id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllLeads(List<Lead> leads);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertLead(Lead lead);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateLead(Lead lead);

    //returns number of rows deleted
    @Delete
    int deleteLead(Lead lead);

}
