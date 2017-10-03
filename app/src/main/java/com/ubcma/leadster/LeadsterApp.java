package com.ubcma.leadster;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.ubcma.leadster.database.LeadsterDB;

/**
 * Created by Yvonne on 8/27/2017.
 */

public class LeadsterApp extends Application {

    public static LeadsterApp INSTANCE;
    private static final String DATABASE_NAME = "LeadsterDB";
    private LeadsterDB db;


    public static LeadsterApp get() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // create database
        db = Room.databaseBuilder(getApplicationContext(), LeadsterDB.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()//recreate all of the tables
                .build();

        INSTANCE = this;
    }

    public LeadsterDB getDB() {
        return db;
    }
}
