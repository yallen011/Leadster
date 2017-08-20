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
    private String goalType;
    @ColumnInfo(name = "goal_title")
    private String goalTitle;
    @ColumnInfo(name = "goal_frequency")
    private String goalFrequency;
    @ColumnInfo(name = "goal_target")
    private int goalTarget;

    public Goal() {
    }

    public Goal(String goalType, String goalFrequency, int goalTarget) {

        this.goalType = goalType;
        this.goalTitle = goalTitle;
        this.goalFrequency = goalFrequency;
        this.goalTarget = goalTarget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    // TODO: 8/20/2017 refactor string flags into an enum class or use constant instead
    private void setGoalTitle(String goalType) {

        String goalTitle = "";
        if(getGoalType() == "c"){
            goalTitle = "Call";
        }else if (getGoalType() == "i"){
            goalTitle = "Interview";
        }else if(getGoalType() == "p"){
            goalTitle = "Party";
        }else {
            goalTitle = "Recruit";
        }

        this.goalTitle = goalTitle;
    }

    public String getGoalFrequency() {
        return goalFrequency;
    }

    public void setGoalFrequency(String goalFrequency) {
        this.goalFrequency = goalFrequency;
    }

    public int getGoalTarget() {
        return goalTarget;
    }

    public void setGoalTarget(int goalTarget) {
        this.goalTarget = goalTarget;
    }
}
