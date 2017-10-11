package com.ubcma.leadster.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ubcma.leadster.entity.Lead;

import java.util.List;

/**
 * Created by Yvonne on 8/20/2017.
 */

@Dao
public interface LeadDao {

    @Query("SELECT * FROM lead")
    List<Lead> getAllLeads();

    @Query("SELECT * FROM lead WHERE :id = id")
    Lead getLead(int id);

    //TODO: 10/10/2017: uncomment the conflict strategy
    //@Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    void insertAllLeads(List<Lead> leads);

    //// TODO: 10/10/2017 : change the variable to say lead and not goal.
    @Insert
    Long insertLead(Lead goal);

    // TODO: 10/10/2017: uncomment the conflict strategy
    //@Update(onConflict = OnConflictStrategy.REPLACE)
    @Update(onConflict = 1)
    void updateLead(Lead lead);

    @Delete
    void deleteLead(Lead lead);

}
