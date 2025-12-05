package com.example.parakeet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.parakeet.databinding.ActivityGeneralInfoBinding;
import com.example.parakeet.databinding.ActivityLocationFoundBinding;

public class LocationFoundActivity extends AppCompatActivity {

    ActivityLocationFoundBinding binding;

    private static final String USERNAME_KEY = "com.example.parakeet.username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationFoundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String username = getIntent().getStringExtra(USERNAME_KEY);

        binding.locatiionReturnButton.setOnClickListener(v -> {
            Intent intent = LandingPageActivity.landingPageActivityIntentFactory(LocationFoundActivity.this, username);
            startActivity(intent);
        });
    }

    static Intent locationFoundActivityIntentFactory(Context context, String username) {
        Intent intent = new Intent(context, LocationFoundActivity.class);
        intent.putExtra(USERNAME_KEY, username);
        return intent;
    }
}