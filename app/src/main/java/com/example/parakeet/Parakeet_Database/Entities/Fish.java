package com.example.parakeet.Parakeet_Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.parakeet.Parakeet_Database.FishDatabase;

@Entity(tableName = FishDatabase.FISH_TABLE)

public class Fish {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private boolean edible;
    private double weight;
    private double length;

    public Fish(){}
    public Fish(double length, double weight, boolean edible){
        this.length = length;
        this.weight = weight;
        this.edible = edible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isEdible() {
        return edible;
    }

    public void setEdible(boolean edible) {
        this.edible = edible;
    }
}
