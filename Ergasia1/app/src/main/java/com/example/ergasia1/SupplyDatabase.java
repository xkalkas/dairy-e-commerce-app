package com.example.ergasia1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Company.class, Product.class, Supply.class}, version = 1)
public abstract class SupplyDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
