package com.ubcma.leadster.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Yvonne on 10/10/2017.
 */
@Entity(foreignKeys = {@ForeignKey(entity = Lead.class,
        parentColumns = "id",
        childColumns = "leadId",
        onDelete = ForeignKey.CASCADE)})
public class Appointment {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int leadTypeId;
    private boolean currentApptInd;
    private int leadId;
    private String date;// TODO: 10/10/2017 refactor to data after getting the converter
    private String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLeadTypeId() {
        return leadTypeId;
    }

    public void setLeadTypeId(int leadTypeId) {
        this.leadTypeId = leadTypeId;
    }

    public boolean isCurrentApptInd() {
        return currentApptInd;
    }

    public void setCurrentApptInd(boolean currentApptInd) {
        this.currentApptInd = currentApptInd;
    }

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
