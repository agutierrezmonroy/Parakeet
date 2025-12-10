package com.example.parakeet.Parakeet_Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Transaction;

import com.example.parakeet.Parakeet_Database.Entities.Fish;
import com.example.parakeet.Parakeet_Database.Entities.Habitat;
import com.example.parakeet.Parakeet_Database.Entities.User;
import com.example.parakeet.Parakeet_Database.FishDatabase;


@Dao
public interface UserFishHabitatDAO {

    @Insert
    long insertUser(User user);

    @Insert
    long insertHabitat(Habitat habitat);

    @Insert
    void insertFish(Fish... fish);

    @Transaction
    default void insertUHF(User user, Habitat habitat, Fish fishA, Fish fishB) {
        long userId = insertUser(user);
        long habitatId = insertHabitat(habitat);

        user.setUserid(userId);
        habitat.setHabitatId(habitatId);

        fishA.setFishUserId(userId);
        fishA.setHabitat_id(habitatId);

        fishB.setFishUserId(userId);
        fishB.setHabitat_id(habitatId);

        insertFish(fishA, fishB);
    }
}
