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
import com.example.parakeet.Parakeet_Database.Repository;
import com.example.parakeet.databinding.FragmentFishInformationBinding;

public class FishInformationFragment extends Fragment {

    private static final String USERNAME_KEY = "username";

    private FragmentFishInformationBinding binding;
    Repository repository;
    private String username;

    public static FishInformationFragment newInstance(String username) {
        FishInformationFragment fragment = new FishInformationFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME_KEY, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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


        binding.logFishButton.setOnClickListener(v -> {
            String lengthStr = binding.fishLengthEditText.getText().toString().trim();
            String weightStr = binding.fishWeightEditText.getText().toString().trim();

            if(lengthStr.isEmpty() || weightStr.isEmpty()){
                Toast.makeText(requireContext(), "Please enter length and weight", Toast.LENGTH_SHORT).show();
                return;
            }

            double length = Double.parseDouble(lengthStr);
            double weight = Double.parseDouble(weightStr);

            Fish fish = new Fish(length, weight, true);
            repository.insertFish(fish);
            Toast.makeText(requireContext(), "Fish logged", Toast.LENGTH_SHORT).show();
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