package com.ubcma.leadster;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Lead;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static com.ubcma.leadster.LeadTestUtil.getLead;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yvonne on 11/2/2017.
 */
@RunWith(AndroidJUnit4.class)
public class LeadDaoTest {
    private static final String TAG = LeadDaoTest.class.getSimpleName();

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

        assertThat("Lead Name should be the same", actualLead.getName(), is(expectedLead.getName()));
        assertThat("Lead Email should be the same", actualLead.getEmail(), is(expectedLead.getEmail()));
        assertThat("Lead Number should be the same", actualLead.getNumber(), is(expectedLead.getNumber()));
        assertThat("Lead Type should be the same", actualLead.getType(), is(expectedLead.getType()));
        assertThat("Lead Follow Up Attempt should be the same", actualLead.getFollowUpAttempt(),
                is(expectedLead.getFollowUpAttempt()));

    }

    @Test
    public void shouldRetrieveAllLeads() throws Exception {
        Lead leads = getLead();
        mLeadDao.insertLead(leads);
        List<Lead> lead = mLeadDao.getAllLeads();
        //List<Lead> resultLeads = mLeadDao.getAllLeads();
        assertTrue(lead.size() == 1);
    }

    @Test
    public void shouldDeleteLead() throws Exception{

        int expectedRowsDeleted = 1;
        Lead lead = getLead();
        int id = mLeadDao.insertLead(lead).intValue();
        lead.setId(id);

        int rowsDeleted = mLeadDao.deleteLead(lead);
        assertEquals(expectedRowsDeleted, rowsDeleted);
    }

    @Test
    public void shouldNotDeleteLead() throws Exception {
        int expectedRowsDeleted = 0;
        Lead lead = getLead();
        lead.setId(34343);
        int rowsDeleted = mLeadDao.deleteLead(lead);
        assertEquals(expectedRowsDeleted, rowsDeleted);



    }
}