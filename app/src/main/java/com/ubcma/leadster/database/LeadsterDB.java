package com.ubcma.leadster.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ubcma.leadster.dao.AppointmentDao;
import com.ubcma.leadster.dao.GoalDao;
import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.entity.Appointment;
import com.ubcma.leadster.entity.Goal;
import com.ubcma.leadster.entity.Lead;

/**
 * Created by Yvonne on 8/20/2017.
 */

@Database(version = 4, entities = {Goal.class, Lead.class, Appointment.class})
public abstract class LeadsterDB extends RoomDatabase {

    abstract public GoalDao goalDao();
    abstract public LeadDao leadDao();
    abstract public AppointmentDao apptDao();
}
