package com.ubcma.leadster.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Yvonne on 6/27/2016.
 * Class that holds lead information
 */

@Entity
public class Lead {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String number;
    private String type;
    private String email;

    //Used for testing
    public Lead (){
        super();
    }

    @Ignore
    public Lead(String name, String number, String type, String email) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.email = email;
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
}
