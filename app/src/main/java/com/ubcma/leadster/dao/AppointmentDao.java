package com.ubcma.leadster.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.ubcma.leadster.entity.Appointment;

import java.util.List;

/**
 * Created by Yvonne on 10/12/2017.
 */

@Dao
public interface AppointmentDao {

    @Query("SELECT * FROM appointment")
    List<Appointment> getAllAppointments();

    @Query("SELECT * FROM appointment WHERE id = :id")
    Appointment getAppointment(int id);

    @Insert
    Long insertAppointment(Appointment appt);

    @Insert
    void insertAllAppts(List<Appointment> appts);

    @Delete
    void  deleteAppointment(Appointment appt);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateAppointment(Appointment appt);
}
