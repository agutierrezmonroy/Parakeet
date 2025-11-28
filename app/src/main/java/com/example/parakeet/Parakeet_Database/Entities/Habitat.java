package com.example.parakeet.Parakeet_Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Repository")
public class Habitat {
    @PrimaryKey(autoGenerate = true)
    private int id;
}
