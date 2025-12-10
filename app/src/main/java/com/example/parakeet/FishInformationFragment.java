package com.example.parakeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.parakeet.Parakeet_Database.Entities.Fish;
import com.example.parakeet.Parakeet_Database.Entities.Habitat;
import com.example.parakeet.Parakeet_Database.Repository;
import com.example.parakeet.databinding.FragmentFishInformationBinding;

public class FishInformationFragment extends Fragment {

    private static final String USERNAME_KEY = "username";

    private FragmentFishInformationBinding binding;
    private Repository repository;
    private String username;

    public static FishInformationFragment newInstance(String username) {
        FishInformationFragment fragment = new FishInformationFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME_KEY, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFishInformationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            username = getArguments().getString(USERNAME_KEY);
        }

        repository = Repository.getRepository(requireActivity().getApplication());

        repository.getUserByUsername(username)
                .observe(getViewLifecycleOwner(), user -> {
                    if (user == null) {
                        Toast.makeText(requireContext(),
                                "User not found. Please log in again.",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    long userId = user.getUserid();

                    binding.logFishButton.setOnClickListener(v -> {
                        String species = binding.fishSpeciesEditText.getText().toString().trim();
                        String lengthStr = binding.fishLengthEditText.getText().toString().trim();
                        String weightStr = binding.fishWeightEditText.getText().toString().trim();
                        String habitatName = binding.habitatNameEditText.getText().toString().trim();

                        String habitatRegion = binding.habitatRegionEditText.getText().toString().trim();

                        if (species.isEmpty() || lengthStr.isEmpty() || weightStr.isEmpty() || habitatName.isEmpty()) {
                            Toast.makeText(requireContext(),
                                    "Please enter species, length, weight, and habitat.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        double length;
                        double weight;
                        try {
                            length = Double.parseDouble(lengthStr);
                            weight = Double.parseDouble(weightStr);
                        } catch (NumberFormatException e) {
                            Toast.makeText(requireContext(),
                                    "Length and weight must be numbers.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Habitat habitat = new Habitat(habitatName, habitatRegion);

                        long habitatId = repository.insertHabitatSync(habitat);

                        Fish fish = new Fish(species, length, weight, true, habitatId);
                        fish.setFishUserId(userId);

                        repository.insertFish(fish);

                        Toast.makeText(requireContext(),
                                "Fish logged",
                                Toast.LENGTH_SHORT).show();

                        binding.fishSpeciesEditText.setText("");
                        binding.fishLengthEditText.setText("");
                        binding.fishWeightEditText.setText("");
                        binding.habitatNameEditText.setText("");
                        binding.habitatRegionEditText.setText("");
                    });
                });

        binding.filocatiionReturnButton.setOnClickListener(v -> {
            Intent intent = LandingPageActivity.landingPageActivityIntentFactory(requireContext(), username);
            startActivity(intent);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}