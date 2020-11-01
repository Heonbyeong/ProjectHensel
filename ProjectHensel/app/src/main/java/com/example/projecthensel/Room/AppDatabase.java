package com.example.projecthensel.Room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projecthensel.MainActivity;

import java.util.List;

@Database(entities = {Date.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

   // public abstract DateDao dateDao();
    private static AppDatabase instance = null;
    public static synchronized AppDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "date_Database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract DateDao RoomDao();
}
