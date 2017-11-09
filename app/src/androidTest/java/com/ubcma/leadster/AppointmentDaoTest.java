package com.ubcma.leadster;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ubcma.leadster.dao.AppointmentDao;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Appointment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Yvonne on 10/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class AppointmentDaoTest {

    private AppointmentDao mApptDao;
    private LeadsterDB mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, LeadsterDB.class).build();
        mApptDao = mDb.apptDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void shouldWriteApptAndReadInList() throws Exception {

        // TODO: 11/2/2017 to get this to work, you need to add a lead to db first then insert appt
        Appointment appt = new Appointment();
        appt.setCurrentApptInd(true);
        appt.setLeadId(1);
        appt.setLocation("Atlanta");
        appt.setLeadTypeId(1);

        Long result = mApptDao.insertAppointment(appt);

        assertNotNull(result);

    }
}
