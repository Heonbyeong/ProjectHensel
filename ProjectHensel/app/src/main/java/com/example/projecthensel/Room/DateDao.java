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
    List<Date> getAll(); //LiveData

    @Query("DELETE FROM dateTable")
    void deleteAll();

//    @Query("SELECT * FROM dateTable WHERE year")
//    String getYear();
//
//    @Query("SELECT * FROM dateTable WHERE month")
//    List<Date> getMonth();
//
//    @Query("SELECT * FROM dateTable WHERE count")
//    List<Date> getCount();
}

