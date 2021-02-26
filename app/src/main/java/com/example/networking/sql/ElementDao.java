package com.example.networking.sql;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.networking.models.Element;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ElementDao {
    @Insert(onConflict = REPLACE)
    void insert(Element element);

    @Delete
    void delete(Element element);

    @Update
    void update(Element... element);

    @Query("SELECT * FROM element")
    List<Element> getAll();

    @Query("SELECT * FROM element WHERE id = :elementId")
    Element getElement(int elementId);
}
