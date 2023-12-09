package com.example.foodmanager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemModelDao {
    @Query("SELECT * FROM itemmodel")
    List<ItemModel> getAll();

    @Insert
    void insertAll(ItemModel... itemModels);

    @Delete
    void delete(ItemModel itemModel);
}
