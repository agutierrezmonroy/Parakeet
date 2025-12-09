package com.example.parakeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.parakeet.databinding.FragmentGeneralInfoBinding;

import androidx.fragment.app.Fragment;


public class GeneralInfoFragment extends Fragment {

    private static final String USERNAME_KEY = "username";

    private FragmentGeneralInfoBinding binding;
    private String username;

    public static GeneralInfoFragment newInstance(String username) {
        GeneralInfoFragment fragment = new GeneralInfoFragment();
        Bundle args = new Bundle();
        args.putString(USERNAME_KEY, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGeneralInfoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            username = getArguments().getString(USERNAME_KEY);
        }

        binding.giReturnToHubButton.setOnClickListener(v -> {
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