package com.example.parakeet.Parakeet_Database.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "Fish",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "user_id",
                        childColumns = "fish_user_id",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Habitat.class,
                        parentColumns = "habitat_id",
                        childColumns = "fish_habitat_id",
                        onDelete = ForeignKey.SET_NULL
                )
        }
)
public class Fish {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Fish_id")
    private int Fish_id;

    @ColumnInfo(name = "fish_user_id", index = true)
    private int fishUserId;


    @ColumnInfo(name = "fish_habitat_id", index = true)
    private Integer fishHabitatId;

    private String species;
    private double weight;
    private double length;
    private String bait;
    private String discovery;
    private boolean edible;


    public int getFish_id() {
        return Fish_id;
    }

    public void setFish_id(int fish_id) {
        Fish_id = fish_id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getBait() {
        return bait;
    }

    public void setBait(String bait) {
        this.bait = bait;
    }

    public String getDiscovery() {
        return discovery;
    }

    public void setDiscovery(String discovery) {
        this.discovery = discovery;
    }

    public boolean isEdible() {
        return edible;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }

    public int getFishUserId() {
        return fishUserId;
    }

    public void setFishUserId(int fishUserId) {
        this.fishUserId = fishUserId;
    }

    public Integer getFishHabitatId() {
        return fishHabitatId;
    }

    public void setFishHabitatId(Integer fishHabitatId) {
        this.fishHabitatId = fishHabitatId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Fish fish = (Fish) o;
        return Fish_id == fish.Fish_id && fishUserId == fish.fishUserId && Double.compare(weight, fish.weight) == 0 && Double.compare(length, fish.length) == 0 && edible == fish.edible && Objects.equals(fishHabitatId, fish.fishHabitatId) && Objects.equals(species, fish.species) && Objects.equals(bait, fish.bait) && Objects.equals(discovery, fish.discovery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Fish_id, fishUserId, fishHabitatId, species, weight, length, bait, discovery, edible);
    }
}

