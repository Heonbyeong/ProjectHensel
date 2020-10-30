package com.example.projecthensel;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface RoomDao {
    @Query("SELECT * FROM RoomEntity")
    List<RoomEntity> getAll();

    @Insert
    void insert(RoomEntity Entity);

    @Update
    void update(RoomEntity Entity);

    @Delete
    void delete(RoomEntity Entity);
}
