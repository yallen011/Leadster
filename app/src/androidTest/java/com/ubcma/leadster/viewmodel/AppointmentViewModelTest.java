package com.ubcma.leadster.viewmodel;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.ubcma.leadster.LeadTestUtil;
import com.ubcma.leadster.dao.AppointmentDao;
import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Appointment;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Yvonne on 11/12/2017.
 */
public class AppointmentViewModelTest {

    AppointmentViewModel viewModel;
    private AppointmentDao mApptDao;
    private LeadDao mLeadDao;
    private LeadsterDB mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, LeadsterDB.class).build();
        viewModel = new AppointmentViewModel();
        mApptDao = mDb.apptDao();
        mLeadDao = mDb.leadDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void shouldSaveAppointment() throws Exception {

        // TODO: 11/12/2017 remove ViewModel test since we already test most of the logic in the Dao Tests
        Appointment appointment = LeadTestUtil.getAppoinment();
        Long expectedApptId = 1L;
        appointment.setId(expectedApptId.intValue());
        LeadTestUtil.addLeadToAppt(appointment, mLeadDao);

        Long savedApptId = viewModel.saveAppointment(appointment);

        assertThat("Ids should be the same", savedApptId, is(expectedApptId));

    }
}