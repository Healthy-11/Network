package com.example.networking.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "room")
public class Room implements Serializable {

    @ColumnInfo(name = "server_id")
    String serverId = "";
    @ColumnInfo(name = "uid")
    String uid = "";
    @ColumnInfo(name = "name")
    String name = "";
    @ColumnInfo(name = "owner")
    String owner = "";

    @PrimaryKey(autoGenerate = true)
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Room() {
    }

    public Room(String id, String uid, String name, String owner) {
        this.serverId = id;
        this.uid = uid;
        this.name = name;
        this.owner = owner;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
