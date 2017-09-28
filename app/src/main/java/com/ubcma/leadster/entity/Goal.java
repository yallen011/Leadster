package com.ubcma.leadster.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

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

    //Used for testing
    public Goal() {
    }

    @Ignore
    public Goal(String goalType, String goalFrequency, int goalTarget) {

        this.goalType = goalType;
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

        if(goalTitle == null && goalType != null){
            goalTitle = getTitleByGoalType();
        }

        return goalTitle;
    }

    public void setGoalTitle(String goalTitle) {

        if(TextUtils.isEmpty(goalTitle)){
            this.goalTitle = getTitleByGoalType();
        }else{
            this.goalTitle = goalTitle; //Title coming from DB
        }
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

    // TODO: 8/20/2017 refactor string flags into an enum class or use constant instead
    private String getTitleByGoalType() {

        String goalTitleByType = "";
        if(getGoalType() == "c"){
            goalTitleByType = "Call";
        }else if (getGoalType() == "i"){
            goalTitleByType = "Interview";
        }else if(getGoalType() == "p"){
            goalTitleByType = "Party";
        }else {
            goalTitleByType = "Recruit";
        }
        return goalTitleByType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Goal{");
        sb.append("id=").append(id);
        sb.append(", goalType='").append(goalType).append('\'');
        sb.append(", goalTitle='").append(goalTitle).append('\'');
        sb.append(", goalFrequency='").append(goalFrequency).append('\'');
        sb.append(", goalTarget=").append(goalTarget);
        sb.append('}');
        return sb.toString();
    }
}
