package com.example.ergasia1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "supply",
        primaryKeys = {"scid", "spid", "sdate"},
        foreignKeys = {
        @ForeignKey(entity = Company.class,
            parentColumns = "cid",
            childColumns = "scid",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = Product.class,
            parentColumns = "pid",
            childColumns = "spid",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE)
        })
public class Supply {
    @ColumnInfo (name = "scid") @NonNull
    private int cid;

    @ColumnInfo (name = "spid") @NonNull
    private int pid;

    @ColumnInfo (name = "sdate") @NonNull
    private String supply_day;

    @ColumnInfo (name = "sammount") @NonNull
    private int supply_ammount;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) { this.cid = cid; }

    public int getPid() { return pid; }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getSupply_day() {
        return supply_day;
    }

    public void setSupply_day(String supply_day) {
        this.supply_day = supply_day;
    }


    public int getSupply_ammount() {
        return supply_ammount;
    }

    public void setSupply_ammount(int supply_ammount) {
        this.supply_ammount = supply_ammount;
    }
}
