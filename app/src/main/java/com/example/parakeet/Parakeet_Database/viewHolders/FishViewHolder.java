package com.example.parakeet.Parakeet_Database.viewHolders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.parakeet.R;

import org.w3c.dom.Text;

public class FishViewHolder extends RecyclerView.ViewHolder {

    private final TextView fishTextView;

    private FishViewHolder(View fishView) {
        super(fishView);
        fishTextView = fishView.findViewById(R.id.recyclerItemTextview);
    }

    public void bind(String text){
        fishTextView.setText(text);
    }

    static FishViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fish_recycler_item, parent, false);
        return new FishViewHolder(view);
    }

}
