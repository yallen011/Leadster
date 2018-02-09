package com.ubcma.leadster.viewmodel;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.ubcma.leadster.LeadTestUtil;
import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Lead;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.ubcma.leadster.LeadTestUtil.getLead;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Yvonne on 11/2/2017.
 */
@RunWith(AndroidJUnit4.class)
public class LeadViewModelTest {

    private LeadViewModel viewModel;
    private LeadsterDB mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, LeadsterDB.class).build();
        viewModel = new LeadViewModel();
    }

    @After
    public void closeDb() throws IOException {
        mDb.close();
    }

    @Test
    public void shouldSaveLeadAndReturnId() throws Exception {
        Lead lead = getLead();
        int expectedLeadId = 4;
        lead.setId(expectedLeadId);
        int actualLeadId = viewModel.saveLead(lead);

        assertThat("Lead id should be equal", actualLeadId, is(expectedLeadId));

    }

//    @Test
//    public void shouldReturnListOfLeads() throws Exception {
//        Lead lead = LeadTestUtil.getLead();
//        lead.setId(viewModel.saveLead(lead));
//
//        List<Lead> resultLeads = viewModel.loadLeads();
//
//       assertThat(resultLeads.size(), greaterThanOrEqualTo(1));
//    }


    @Test
    public void shouldDeleteLead() throws Exception {
        Lead lead = getLead();
        int id = viewModel.saveLead(lead);
        lead.setId(id);

        boolean actualIsRowDeleted = viewModel.deleteLead(lead);
        assertTrue(actualIsRowDeleted);
    }

    @Test
    public void shouldNotDeleteLead() throws Exception {
        Lead lead = getLead();
        lead.setId(23211);

        boolean actualIsRowDeleted = viewModel.deleteLead(lead);
        assertFalse(actualIsRowDeleted);
    }
}