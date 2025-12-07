package com.example.parakeet.Parakeet_Database.Entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "Habitat")
public class Habitat {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Habitat_id")
    private int Habitat_id;

    private String desc;
    private String warnings;
    private String Favorite_Location;

    public int getHab_id() {
        return Habitat_id;
    }

    public void setHab_id(int habitat_id) {
        Habitat_id = habitat_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public String getFavorite_Location() {
        return Favorite_Location;
    }

    public void setFavorite_Location(String favorite_Location) {
        Favorite_Location = favorite_Location;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Habitat habitat = (Habitat) o;
        return Habitat_id == habitat.Habitat_id && Objects.equals(desc, habitat.desc) && Objects.equals(warnings, habitat.warnings) && Objects.equals(Favorite_Location, habitat.Favorite_Location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Habitat_id, desc, warnings, Favorite_Location);
    }
}
