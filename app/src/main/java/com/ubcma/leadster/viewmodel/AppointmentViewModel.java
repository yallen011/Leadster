package com.ubcma.leadster.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.ubcma.leadster.LeadsterApp;
import com.ubcma.leadster.dao.AppointmentDao;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Appointment;

import java.util.List;

/**
 * Created by Yvonne on 11/12/2017.
 */

public class AppointmentViewModel extends ViewModel {

    private LeadsterDB mDb;
    private AppointmentDao mApptDao;

    public AppointmentViewModel() {
        mDb = LeadsterApp.get().getDB();
        this.mApptDao = mDb.apptDao();
    }

    public Long saveAppointment(Appointment appointment) {

        Long appointmentId = mApptDao.insertAppointment(appointment);

        return appointmentId;
    }

    public void saveAllAppointments(List<Appointment> appointments) {

        mApptDao.insertAllAppts(appointments);

    }
}
