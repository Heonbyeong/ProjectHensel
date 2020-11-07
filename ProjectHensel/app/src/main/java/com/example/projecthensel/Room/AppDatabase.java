package com.example.projecthensel.Room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.projecthensel.MainActivity;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Database(entities = {Date.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DateDao dateDao();
    private static volatile AppDatabase INSTANCE;

    //싱글톤
    public static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "date_database").build();
                }
            }
        }
        return INSTANCE;
    }

    //DB 객체 제거
    public static void destroyInstance(){
        INSTANCE = null;
    }
}

