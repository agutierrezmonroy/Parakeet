package com.example.parakeet.Parakeet_Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Repository")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;
    private String password;
    private boolean is_admin;
}
