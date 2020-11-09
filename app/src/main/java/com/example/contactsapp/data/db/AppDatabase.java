package com.example.contactsapp.data.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.contactsapp.data.db.dao.PersonDao;
import com.example.contactsapp.data.db.entity.Person;


@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract PersonDao personModel();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "persons")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
