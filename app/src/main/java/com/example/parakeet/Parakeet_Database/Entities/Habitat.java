package com.example.parakeet.Parakeet_Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.parakeet.Parakeet_Database.FishDatabase;

@Entity(tableName = FishDatabase.HABITAT_TABLE)

public class Habitat {
    @PrimaryKey(autoGenerate = true)
    private int id;


    private String name;
    private String region;

    public Habitat(String name, String region){
        this.name = name;
        this.region = region;
    }


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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
