package com.example.parakeet.Parakeet_Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.parakeet.Parakeet_Database.Entities.Fish;

import java.util.List;

@Dao
public interface FishDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(Fish... fish);

    @Query("DELETE FROM " + FishDatabase.FISH_TABLE)
    void deleteAll();

    @Query("SELECT * FROM " + FishDatabase.FISH_TABLE)
    LiveData<List<Fish>> getAllFish();

    @Query("SELECT * FROM " + FishDatabase.FISH_TABLE + " WHERE fish_user_id = :userId")
    LiveData<List<Fish>> getAllFishByUserId(int userId);
}
