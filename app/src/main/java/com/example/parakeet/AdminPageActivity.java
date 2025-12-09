package com.example.parakeet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.parakeet.databinding.ActivityAdminPageBinding;
import com.example.parakeet.databinding.ActivityGeneralInfoBinding;

public class AdminPageActivity extends AppCompatActivity {

    ActivityAdminPageBinding binding;

    private static final String USERNAME_KEY = "com.example.parakeet.username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String username = getIntent().getStringExtra(USERNAME_KEY);

        binding.apReturnToHubButton.setOnClickListener(v -> {
            Intent intent = LandingPageActivity.landingPageActivityIntentFactory(AdminPageActivity.this, username);
            startActivity(intent);
        });
    }

    static Intent adminPageActivityIntentFactory(Context context, String username) {
        return new Intent(context, AdminPageActivity.class);
    }
}