package com.ubcma.leadster.converter;

import android.arch.persistence.room.TypeConverter;
import android.util.Log;

import com.ubcma.leadster.entity.Lead;
import static com.ubcma.leadster.entity.Lead.Status.CALL_BACK;
import static com.ubcma.leadster.entity.Lead.Status.INTERESTED;
import static com.ubcma.leadster.entity.Lead.Status.NEW;
import static com.ubcma.leadster.entity.Lead.Status.NOT_INTERESTED;
import static com.ubcma.leadster.entity.Lead.Status.NO_ANSWER;
import static com.ubcma.leadster.entity.Lead.Status.WRONG_NUMBER;

/**
 * Created by Yvonne on 9/29/2017.
 */

public class StatusConverter {
    
    private static final String TAG = StatusConverter.class.getSimpleName();

    @TypeConverter
    public static Lead.Status stringToStatus(String status) {

        return Lead.Status.statusFromString(status);
    }

    @TypeConverter
    public static String statusToString(Lead.Status status) {
        return status.getStatus();
    }
}
