package com.example.ergasia1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "company")
public class Company {
        @PrimaryKey
        @ColumnInfo(name = "cid")
        private int id;

        @ColumnInfo (name = "cname")
        private String name;
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

}

