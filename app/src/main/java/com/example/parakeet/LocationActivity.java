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
import com.example.parakeet.databinding.ActivityLocationBinding;

public class LocationActivity extends AppCompatActivity {

    ActivityLocationBinding binding;
    private static final String ADMIN_KEY = "com.example.parakeet.admin";
    private static final String USERNAME_KEY = "com.example.parakeet.username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String username = getIntent().getStringExtra(USERNAME_KEY);

        binding.setLocationButton.setOnClickListener(v -> {
            Intent intent = LocationFoundActivity.locationFoundActivityIntentFactory(LocationActivity.this, username);
            startActivity(intent);
        });
    }


    static Intent locationActivityIntentFactory(Context context, String username) {
        Intent intent = new Intent(context, LocationActivity.class);
        intent.putExtra(USERNAME_KEY, username);
        return intent;
    }
}