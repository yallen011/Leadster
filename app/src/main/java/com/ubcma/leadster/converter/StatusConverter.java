package com.ubcma.leadster.converter;

import android.arch.persistence.room.TypeConverter;

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

    @TypeConverter
    public static Lead.Status stringToStatus(String status) {
        if (status == NEW.getStatus()) {
            return NEW;
        } else if (status == NOT_INTERESTED.getStatus()) {
            return NOT_INTERESTED;
        } else if (status == INTERESTED.getStatus()) {
            return INTERESTED;
        } else if(status == WRONG_NUMBER.getStatus()){
            return WRONG_NUMBER;
        } else if(status ==CALL_BACK.getStatus()){
            return CALL_BACK;
        } else if(status == NO_ANSWER.getStatus()){
            return NO_ANSWER;
        } else {
            throw new IllegalArgumentException("Could not recognize status");
        }
    }

    @TypeConverter
    public static String statusToString(Lead.Status status) {
        return status.getStatus();
    }
}
