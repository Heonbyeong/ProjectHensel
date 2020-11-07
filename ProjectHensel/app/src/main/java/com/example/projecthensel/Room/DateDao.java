package com.example.projecthensel.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface DateDao {

    @Insert
    void insert(Date date);

    @Update
    void update(Date date);

    @Delete
    void delete(Date date);

    @Query("SELECT * FROM dateTable")
    LiveData<List<Date>> getAll(); //LiveData

    @Query("DELETE FROM dateTable")
    void deleteAll();
}

