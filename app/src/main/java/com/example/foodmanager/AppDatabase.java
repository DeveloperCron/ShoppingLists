package com.example.foodmanager;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ItemModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemModelDao itemModelDao();
}
