package com.example.parakeet.Parakeet_Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.parakeet.Parakeet_Database.Entities.Habitat;

import java.util.List;

@Dao
public interface HabitatDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Habitat... habitats);

    @Query("DELETE FROM " + FishDatabase.HABITAT_TABLE)
    void deleteAll();

    @Query("SELECT * from " + FishDatabase.HABITAT_TABLE)
    LiveData<List<Habitat>> getAllHabitats();
}
