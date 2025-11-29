package com.example.parakeet.Parakeet_Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.parakeet.Parakeet_Database.Entities.User;

import java.util.ArrayList;

@Dao
public interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("Select * from " + Database.PARAKEET_TABLE)
    ArrayList<User> getAllUsers();
}
