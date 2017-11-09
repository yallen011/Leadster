package com.ubcma.leadster.viewmodel;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Lead;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Yvonne on 11/2/2017.
 */
@RunWith(AndroidJUnit4.class)
public class LeadViewModelTest {

    private LeadViewModel viewModel;
    private LeadDao mLeadDao;
    private LeadsterDB mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, LeadsterDB.class).build();
        mLeadDao = mDb.leadDao();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void shouldReturnListOfUsers() throws Exception {
        mLeadDao.insertAllLeads(getLeads());

        // TODO: 11/2/2017 find away to load fake data for testing
        List<Lead> resultLeads = viewModel.loadLeads();
        assertTrue(resultLeads.size() == 2);
    }

    private List<Lead> getLeads() {

        List<Lead> leadsList = new ArrayList<>();

        Lead lead = new Lead();
        lead.setNumber("770-808-9955");
        lead.setName("Name");
        lead.setEmail("test@test.com");
        lead.setType("Team Member");
        lead.setFollowUpAttempt("1");
        lead.setStatus(Lead.Status.NEW);

        Lead lead2 = new Lead();
        lead.setNumber("770-808-9954");
        lead.setName("Name2");
        lead.setEmail("test2@test.com");
        lead.setType("Team Member");
        lead.setFollowUpAttempt("1");
        lead.setStatus(Lead.Status.NEW);

        leadsList.add(lead);
        leadsList.add(lead2);

        return leadsList;
    }

}