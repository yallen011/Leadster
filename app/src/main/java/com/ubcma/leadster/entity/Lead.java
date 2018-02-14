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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lead)) return false;

        Lead lead = (Lead) o;

        if (getId() != lead.getId()) return false;
        if (getName() != null ? !getName().equals(lead.getName()) : lead.getName() != null)
            return false;
        if (getNumber() != null ? !getNumber().equals(lead.getNumber()) : lead.getNumber() != null)
            return false;
        if (getType() != null ? !getType().equals(lead.getType()) : lead.getType() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(lead.getEmail()) : lead.getEmail() != null)
            return false;
        if (getStatus() != lead.getStatus()) return false;
        return getFollowUpAttempt() != null ? getFollowUpAttempt().equals(lead.getFollowUpAttempt()) : lead.getFollowUpAttempt() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getFollowUpAttempt() != null ? getFollowUpAttempt().hashCode() : 0);
        return result;
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
