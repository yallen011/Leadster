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

    @Insert
    void insertAllGoals(List<Lead> leads);

    @Insert
    Long insertGoal(Lead goal);

    // TODO: 8/20/2017 find correct value for replace on conflict
    @Update(onConflict = 1)
    void updateGoal(Lead lead);

    @Delete
    void deleteGoal(Lead lead);

}
