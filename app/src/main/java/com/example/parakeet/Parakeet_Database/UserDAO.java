package com.example.parakeet.Parakeet_Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.parakeet.Parakeet_Database.Entities.User;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User... user);

    @Query("DELETE FROM " + FishDatabase.USER_TABLE)
    void deleteAll();

    @Query("SELECT * from " + FishDatabase.USER_TABLE + " WHERE username == :username")
    LiveData<User> getUserByUsername(String username);
}
