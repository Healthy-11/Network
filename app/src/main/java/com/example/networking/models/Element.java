package com.example.networking.models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "element")

public class Element implements Serializable {


    @ColumnInfo(name = "server_id")
    String serverId = "";
    @ColumnInfo(name = "uid")
    String uid = "";
    @ColumnInfo(name = "name")
    String name = "";
    @ColumnInfo(name = "category_id")
    String category_id = "";
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

    public Element() {
    }

    public Element(String id, String uid, String name, String category_id, String owner) {
        this.serverId = id;
        this.uid = uid;
        this.name = name;
        this.category_id = category_id;
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

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
