package com.ubcma.leadster;

import com.ubcma.leadster.dao.LeadDao;
import com.ubcma.leadster.database.LeadsterDB;
import com.ubcma.leadster.entity.Appointment;
import com.ubcma.leadster.entity.Lead;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvonne on 11/12/2017.
 */

public class LeadTestUtil {

    public static Lead getLead() {

        Lead lead = new Lead();
        lead.setNumber("770-808-9955");
        lead.setName("Grace");
        lead.setEmail("test@test.com");
        lead.setType("Team Member");
        lead.setFollowUpAttempt("1");
        lead.setStatus(Lead.Status.NEW);

        return lead;
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

    public static Appointment getAppoinment() {

        Appointment appt = new Appointment();
        appt.setCurrentApptInd(true);
        appt.setLocation("Atlanta");
        appt.setDate("Mon, Aug 2 4:30PM");
        appt.setLeadTypeId(1);

        return appt;
    }

    public static void addLeadToAppt(Appointment appt, LeadDao leadDao) {
        Lead lead = LeadTestUtil.getLead();
        int leadId = leadDao.insertLead(lead).intValue();
        appt.setLeadId(leadId);
    }
}
