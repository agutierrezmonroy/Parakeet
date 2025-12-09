package com.example.parakeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.parakeet.databinding.FragmentLocationBinding;

public class LocationFragment extends Fragment {

    private static final String USERNAME_KEY = "username";

    private FragmentLocationBinding binding;
    private String username;

    public static LocationFragment newInstance(String username) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME_KEY, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            username = getArguments().getString(USERNAME_KEY);
        }

        binding.setLocationButton.setOnClickListener(v -> {
            Intent intent = LocationFoundActivity.locationFoundActivityIntentFactory(requireContext(), username);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
