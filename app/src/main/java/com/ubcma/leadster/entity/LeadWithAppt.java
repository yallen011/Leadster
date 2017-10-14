package com.ubcma.leadster.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by Yvonne on 10/12/2017.
 */

public class LeadWithAppt {
    @Embedded
    public Lead lead;

    @Relation(parentColumn = "id", entityColumn = "leadId", entity = Appointment.class)
    public List<Appointment> appointments;
}
