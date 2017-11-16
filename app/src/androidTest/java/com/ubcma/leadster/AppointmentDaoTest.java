package com.ubcma.leadster;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ubcma.leadster.dao.AppointmentDao;
import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Appointment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ubcma.leadster.LeadTestUtil.addLeadToAppt;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Yvonne on 10/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class AppointmentDaoTest {

    private AppointmentDao mApptDao;
    private LeadDao mLeadDao;
    private LeadsterDB mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, LeadsterDB.class).build();
        mApptDao = mDb.apptDao();
        mLeadDao = mDb.leadDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void shouldWriteApptAndReadInList() throws Exception {

        // TODO: 11/2/2017 to get this to work, you need to add a lead to db first then insert appt
        Appointment appt = LeadTestUtil.getAppoinment();

        Long expectedId = 2L;

        addLeadToAppt(appt, mLeadDao);
        appt.setId(expectedId.intValue());
        Long resultId = mApptDao.insertAppointment(appt);

        assertThat("Ids match", resultId, is(expectedId));

    }

    @Test
    public void shouldUpdateAppt() throws Exception {

        Appointment appointment = LeadTestUtil.getAppoinment();
        addLeadToAppt(appointment, mLeadDao);

        Long apptId = mApptDao.insertAppointment(appointment);
        appointment.setId(apptId.intValue());

        //change appointment location
        appointment.setLocation("Chicago");

        //update appointment location in the database
        mApptDao.updateAppointment(appointment);

        //retrieve updated appointment from database
        Appointment result = mApptDao.getAppointment(apptId.intValue());

        //assert location was updated
        assertThat("Location should be Chicago", result.getLocation(), is(appointment.getLocation()));

    }

    @Test
    public void shouldSaveListOfAppointments() throws Exception {
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(LeadTestUtil.getAppoinment());
        appointments.add(LeadTestUtil.getAppoinment());

        LeadTestUtil.addLeadToAppt(appointments.get(0), mLeadDao);
        LeadTestUtil.addLeadToAppt(appointments.get(1), mLeadDao);

        mApptDao.insertAllAppts(appointments);
        List<Appointment> resultApptList = mApptDao.getAllAppointments();

        assertThat("Database should contain 2 or more Appointments", resultApptList.size(), greaterThanOrEqualTo(2));

    }
}
