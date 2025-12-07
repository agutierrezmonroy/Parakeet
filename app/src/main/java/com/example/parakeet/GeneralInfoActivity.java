package com.example.parakeet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;

import com.example.parakeet.Parakeet_Database.Entities.User;
import com.example.parakeet.Parakeet_Database.Repository;
import com.example.parakeet.databinding.ActivityGeneralInfoBinding;

public class GeneralInfoActivity extends AppCompatActivity {

    ActivityGeneralInfoBinding binding;
    private static final String USERNAME_KEY = "com.example.parakeet.username";
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGeneralInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String username = getIntent().getStringExtra(USERNAME_KEY);

        binding.giReturnToHubButton.setOnClickListener(v -> {
            Intent intent = LandingPageActivity.landingPageActivityIntentFactory(GeneralInfoActivity.this, username);
            startActivity(intent);
        });

    }

    static Intent generalInfoActivityIntentFactory(Context context, String username){
        Intent intent = new Intent(context, GeneralInfoActivity.class);
        intent.putExtra(USERNAME_KEY, username);
        return intent;
    }
}