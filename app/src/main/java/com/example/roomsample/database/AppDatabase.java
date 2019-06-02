package com.example.roomsample.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.roomsample.Entity.Task;
import com.example.roomsample.dao.TaskDao;

@Database(entities = {Task.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
