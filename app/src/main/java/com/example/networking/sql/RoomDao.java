package com.example.networking.sql;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.networking.models.Room;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RoomDao {
    @Insert(onConflict = REPLACE)
    void insert(Room room);

    @Delete
    void delete(Room room);

    @Update
    void update(Room... room);

    @Query("SELECT * FROM room")
    List<Room> getAll();

    @Query("SELECT * FROM room WHERE id = :roomId")
    Room getRoom(int roomId);
}
