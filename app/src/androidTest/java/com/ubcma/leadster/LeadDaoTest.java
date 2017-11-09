package com.ubcma.leadster;

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

import static org.junit.Assert.*;

/**
 * Created by Yvonne on 11/2/2017.
 */
@RunWith(AndroidJUnit4.class)
public class LeadDaoTest {

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
    public void shouldInsertLeadAndReturnId() throws Exception {

        Lead lead = getLead();

        Long result = mLeadDao.insertLead(lead);

        assertNotNull(result);
    }

    @Test
    public void shouldReadInLead() throws Exception {
        Lead expectedLead = getLead();

        expectedLead.setId(mLeadDao.insertLead(expectedLead).intValue());

        Lead actualLead = mLeadDao.getLead(expectedLead.getId());

        assertEquals("Lead Name should be the same", expectedLead.getName(),actualLead.getName());
        assertEquals("Lead Email should be the same", expectedLead.getEmail(), actualLead.getEmail());
        assertEquals("Lead Number should be the same", expectedLead.getNumber(), actualLead.getNumber());
        assertEquals("Lead Type should be the same", expectedLead.getType(), actualLead.getType());
        assertEquals("Lead Follow Up Attempt should be the same", expectedLead.getFollowUpAttempt(),
                actualLead.getFollowUpAttempt());

    }

    private Lead getLead() {

        Lead lead = new Lead();
        lead.setNumber("770-808-9955");
        lead.setName("Name");
        lead.setEmail("test@test.com");
        lead.setType("Team Member");
        lead.setFollowUpAttempt("1");
        lead.setStatus(Lead.Status.NEW);

        return lead;
    }

}