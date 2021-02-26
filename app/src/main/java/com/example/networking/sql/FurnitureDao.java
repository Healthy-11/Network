package com.example.networking.sql;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.networking.models.Furniture;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FurnitureDao {
    @Insert(onConflict = REPLACE)
    void insert(Furniture furniture);

    @Delete
    void delete(Furniture furniture);

    @Update
    void update(Furniture... furniture);

    @Query("SELECT * FROM furniture")
    List<Furniture> getAll();

    @Query("SELECT * FROM furniture WHERE id = :furnitureId")
    Furniture getFurniture(int furnitureId);

}
