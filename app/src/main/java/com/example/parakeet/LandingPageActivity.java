package com.example.parakeet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.parakeet.databinding.ActivityLandingPageBinding;

public class LandingPageActivity extends AppCompatActivity {
    private static final String ADMIN_KEY = "com.example.parakeet.admin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLandingPageBinding binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        boolean isAdmin = getIntent().getBooleanExtra(ADMIN_KEY, false);
        if (isAdmin) binding.adminButton.setVisibility(View.VISIBLE);

        binding.generalInfoButton.setOnClickListener(v -> {
            Intent intent = GeneralInfoActivity.generalInfoActivityIntentFactory(LandingPageActivity.this, isAdmin);
            startActivity(intent);
        });

        binding.logOutButton.setOnClickListener(v -> {
            Intent intent = LoginActivity.loginActivityIntentFactory(LandingPageActivity.this);
            startActivity(intent);
        });
    }

    static Intent landingPageActivityIntentFactory(Context context, Boolean isAdmin){
        Intent intent = new Intent(context, LandingPageActivity.class);
        intent.putExtra(ADMIN_KEY, isAdmin);
        return intent;
    }
}