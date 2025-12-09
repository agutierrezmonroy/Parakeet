package com.example.parakeet.Parakeet_Database.Entities;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.parakeet.Parakeet_Database.FishDatabase;

import java.util.Objects;

@Entity(tableName = FishDatabase.HABITAT_TABLE)
public class Habitat {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "habitatId")
    private long habitatId;

    private String name;

    private String region;
    private String desc;
    private String warnings;
    private String Favorite_Location;

    public Habitat(String name, String region){
        this.name = name;
        this.region = region;
    }

    public long getHabitatId() {
        return habitatId;
    }

    public void setHabitatId(long habitatId) {
        this.habitatId = habitatId;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Habitat habitat = (Habitat) o;
        return habitatId == habitat.habitatId && Objects.equals(desc, habitat.desc) && Objects.equals(warnings, habitat.warnings) && Objects.equals(Favorite_Location, habitat.Favorite_Location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(habitatId, desc, warnings, Favorite_Location);
    }
}
