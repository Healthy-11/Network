package com.example.networking.sql;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.networking.models.Element;
import com.example.networking.models.Furniture;
import com.example.networking.models.Room;

@Database(entities = {Room.class, Element.class, Furniture.class}, version = 3, exportSchema = false)
public abstract class DatabaseManager extends RoomDatabase {

    private static DatabaseManager database;

    private static String DATABASE_NAME = "databaseRoom";

    public  synchronized static DatabaseManager getInstance(Context context){

        if (database == null){
            database = androidx.room.Room.databaseBuilder(context.getApplicationContext()
                    , DatabaseManager.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return database;
    }

    public abstract RoomDao roomDao();
    public abstract ElementDao elementDao();
    public abstract FurnitureDao furnitureDao();

}
