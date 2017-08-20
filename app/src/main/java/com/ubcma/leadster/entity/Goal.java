package com.ubcma.leadster.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Yvonne on 8/20/2017.
 */

@Entity
public class Goal {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "goal_type")
    private int goalType;
    @ColumnInfo(name = "goal_title")
    private String goalTitle;

    public Goal() {
    }

    public Goal(int id, int goalType, String goalTitle) {
        this.id = id;
        this.goalType = goalType;
        this.goalTitle = goalTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoalType() {
        return goalType;
    }

    public void setGoalType(int goalType) {
        this.goalType = goalType;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public void setGoalTitle(String goalTitle) {
        this.goalTitle = goalTitle;
    }
}
