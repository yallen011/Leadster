package com.ubcma.leadster.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ubcma.leadster.entity.Goal;

import java.util.List;

/**
 * Created by Yvonne on 8/20/2017.
 */

@Dao
public interface GoalDao {

    @Query("SELECT * FROM goal")
    List<Goal> getAllGoals();

    @Query("SELECT * FROM goal WHERE :id = id")
    Goal getGoal(int id);

    @Insert
    void insertAllGoals(List<Goal> goals);

    @Insert
    Long insertGoal(Goal goal);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateGoal(Goal goal);

    @Delete
    void deleteGoal(Goal goal);
}
