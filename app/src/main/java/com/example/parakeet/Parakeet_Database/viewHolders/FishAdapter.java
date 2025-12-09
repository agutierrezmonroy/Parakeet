package com.example.parakeet.Parakeet_Database.viewHolders;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ListAdapter;

import com.example.parakeet.Parakeet_Database.Entities.Fish;

import java.util.List;

public class FishAdapter extends ListAdapter<Fish, FishViewHolder> {
    public FishAdapter() {
        super(new FishDiff());
    }

    //Updating up the display

    @Override
    public void onBindViewHolder(@NonNull FishViewHolder holder,
                                 int position) {
        Fish fish = getItem(position);

        String species   = safe(fish.getSpecies(),   "Unknown species");
        String bait      = safe(fish.getBait(),      "-");
        String discovery = safe(fish.getDiscovery(), "-");

        String text = "Species: " + species
                + " | Length: " + fish.getLength()
                + " | Weight: " + fish.getWeight()
                + " | Bait: " + bait
                + " | Discovery: " + discovery
                + " | Edible: " + (fish.isEdible() ? "Yes" : "No");

        holder.bind(text);
    }


    //Making sure the data is safe to use
    private String safe(String value, String fallback) {
        return value != null ? value : fallback;
    }


    @NonNull
    @Override
    public FishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return FishViewHolder.create(parent);
    }

    public static class FishDiff extends DiffUtil.ItemCallback<Fish> {

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
