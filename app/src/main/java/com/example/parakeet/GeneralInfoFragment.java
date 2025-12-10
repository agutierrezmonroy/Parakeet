package com.example.parakeet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.parakeet.Parakeet_Database.Repository;
import com.example.parakeet.Parakeet_Database.viewHolders.FishAdapter;
import com.example.parakeet.databinding.FragmentGeneralInfoBinding;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;


public class GeneralInfoFragment extends Fragment {

    private static final String USERNAME_KEY = "username";

    private FragmentGeneralInfoBinding binding;
    private Repository repository;
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
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            username = getArguments().getString(USERNAME_KEY);
        }

        repository = Repository.getRepository(requireActivity().getApplication());

        FishAdapter adapter = new FishAdapter(repository, getViewLifecycleOwner());
        binding.fishRecyclerView.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );
        binding.fishRecyclerView.setAdapter(adapter);

        repository.getUserByUsername(username)
                .observe(getViewLifecycleOwner(), user -> {
                    if (user == null) return;

                    long userId = user.getUserid();

                    repository.getAllFishByUserId(userId)
                            .observe(getViewLifecycleOwner(), adapter::submitList);
                });

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