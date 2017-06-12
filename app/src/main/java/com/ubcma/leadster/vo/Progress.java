package com.ubcma.leadster.vo;

/**
 * Created by Yvonne on 6/11/2017.
 * Class to keep track of user progress for goals they are tracking
 */

public class Progress {

    private int goal;
    private int progress;
    private int goalType;
    private String goalTitle;
    private String percent;

    private static int PERCENT_MULTIPLIER = 100;

    public Progress() {
    }

    public Progress(int goal, int progress, int goalType, String goalTitle) {
        this.goal = goal;
        this.progress = progress;
        this.goalType = goalType;
        this.goalTitle = goalTitle;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
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

    public String getPercent() {

        //have to cast one to float to avoid Integer Division
        float basePercent = ((float)getProgress() / getGoal()) * PERCENT_MULTIPLIER;
        int percentWholeNum = Math.round(basePercent);
        percent = percentWholeNum + "%";
        return percent;
    }
}
