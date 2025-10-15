package com.example.ergasia1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product {
    @PrimaryKey
    @ColumnInfo(name = "pid")
    private int id;

    @ColumnInfo (name = "pname")
    private String name;
    @ColumnInfo (name = "ptype")
    private String type;
    @ColumnInfo (name = "pvolume")
    private int pvolume;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPvolume() {
        return pvolume;
    }

    public void setPvolume(int pvolume) {
        this.pvolume = pvolume;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
