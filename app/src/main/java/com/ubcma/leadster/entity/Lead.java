package com.ubcma.leadster.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.ubcma.leadster.converter.StatusConverter;

import java.io.Serializable;

/**
 * Created by Yvonne on 6/27/2016.
 * Class that holds lead information
 */

@Entity
public class Lead{

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String number;
    private String type;
    private String email;
    @TypeConverters(StatusConverter.class)
    private Status status;
    private String followUpAttempt;

    //Used for testing
    public Lead (){
        super();
    }

    @Ignore
    public Lead(String name, String number, String type, String email, Status status, String followUpAttempt) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.email = email;
        this.status = status;
        this.followUpAttempt = followUpAttempt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getFollowUpAttempt() {
        return followUpAttempt;
    }

    public void setFollowUpAttempt(String followUpAttempt) {
        this.followUpAttempt = followUpAttempt;
    }

    public enum Status {
        NEW("New"),
        WRONG_NUMBER("Wrong Number"),
        NOT_INTERESTED("Not Interested"),
        INTERESTED("Interested"),
        NO_ANSWER("No Answer"),
        CALL_BACK("Call Back");

        private String status;

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return this.status;
        }

        public static Lead.Status statusFromString(String stringStatus) {
            for (Lead.Status enumStatus : Lead.Status.values()) {
                if (enumStatus.status.equals(stringStatus)) {
                    return enumStatus;
                }
            }
            return null;
        }
    }
}
