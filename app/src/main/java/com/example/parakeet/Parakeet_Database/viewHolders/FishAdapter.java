package com.example.parakeet.Parakeet_Database.viewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ListAdapter;

import com.example.parakeet.Parakeet_Database.Entities.Fish;

import java.util.List;


import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.parakeet.Parakeet_Database.Entities.Fish;
import com.example.parakeet.Parakeet_Database.Entities.Habitat;
import com.example.parakeet.Parakeet_Database.Repository;

import java.util.List;

public class FishAdapter extends ListAdapter<Fish, FishViewHolder> {

    private final Repository repository;
    private final LifecycleOwner lifecycleOwner;

    public FishAdapter(Repository repository, LifecycleOwner lifecycleOwner) {
        super(new FishDiff());
        this.repository = repository;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public FishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return FishViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull FishViewHolder holder, int position) {
        Fish fish = getItem(position);

        String fishText = "Species: " + safe(fish.getSpecies(), "Unknown species")
                + " | Length: " + fish.getLength()
                + " | Weight: " + fish.getWeight()
                + " | Edible: " + (fish.isEdible() ? "Yes" : "No");

        holder.bind(fishText);


        repository.getHabitatById(fish.getHabitat_id())
                .observe(lifecycleOwner, habitats -> {
                    if (habitats == null || habitats.isEmpty()) {
                        return;
                    }
                    Habitat habitat = habitats.get(0);

                    String habitatName = safe(habitat.getName(), "Unknown habitat");
                    String region = safe(habitat.getRegion(), "Unknown region");

                    String fullText = fishText
                            + " | Habitat: " + habitatName
                            + " | Region: " + region;

                    holder.bind(fullText);
                });
    }

    private String safe(String value, String fallback) {
        return value != null ? value : fallback;
    }

    static class FishDiff extends DiffUtil.ItemCallback<Fish> {

        @Override
        public boolean areItemsTheSame(@NonNull Fish oldItem, @NonNull Fish newItem) {
            return oldItem.getFish_id() == newItem.getFish_id();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Fish oldItem, @NonNull Fish newItem) {
            return oldItem.equals(newItem);
        }
    }
}
