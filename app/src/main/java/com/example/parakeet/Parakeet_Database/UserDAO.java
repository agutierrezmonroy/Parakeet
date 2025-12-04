package com.example.parakeet.Parakeet_Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.parakeet.Parakeet_Database.Entities.User;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Query("DELETE FROM " + FishDatabase.PARAKEET_TABLE)
    void deleteAll();

    @Query("SELECT * from " + FishDatabase.PARAKEET_TABLE + " WHERE username == :username")
    User getUserByUsername(String username);
}
