package com.example.projecthensel.Room;

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

    @Query("UPDATE dateTable SET date_year = :y, date_month = :m, date_date = :d, date_dataString = :ds, date_memo = :mm, date_address = :a, date_startTime = :s, date_endTime = :e WHERE date_Id = :id")
    void update(String y, String m, String d, String ds, String mm, String a, String s, String e, int id);

    @Delete
    void delete(Date date);

    @Query("SELECT * FROM dateTable")
    List<Date> getAll();

    @Query("DELETE FROM dateTable")
    void deleteAll();

    @Query("SELECT COUNT(*) as cnt FROM dateTable")
    int getDataCount();
}
