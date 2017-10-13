package com.ubcma.leadster.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by Yvonne on 10/10/2017.
 */
@Entity(foreignKeys = {@ForeignKey(entity = Lead.class,
        parentColumns = "id",
        childColumns = "lead_type_id",
        onDelete = ForeignKey.CASCADE),

        @ForeignKey(entity = Lead.class,
                parentColumns = "id",
                childColumns = "lead_type_id",
                onDelete = ForeignKey.CASCADE)
        })
public class Appointment {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "lead_type_id")
    private int leadTypeId;
    private boolean currentApptInd;
    private int leadId;
    private String date;// TODO: 10/10/2017 refactor to data after getting the converter
    private String location;

}
