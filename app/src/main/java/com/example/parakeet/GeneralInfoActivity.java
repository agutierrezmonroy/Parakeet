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

public class GeneralInfoActivity extends AppCompatActivity {

    ActivityGeneralInfoBinding binding;
    private static final String ADMIN_KEY = "com.example.parakeet.admin";
    private static final String USERNAME_KEY = "com.example.parakeet.username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGeneralInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        boolean isAdmin = getIntent().getBooleanExtra(ADMIN_KEY, false);
        String username = getIntent().getStringExtra(USERNAME_KEY);

        binding.giReturnToHubButton.setOnClickListener(v -> {
                Intent intent = LandingPageActivity.landingPageActivityIntentFactory(GeneralInfoActivity.this, isAdmin, username);
                startActivity(intent);
        });
    }

    static Intent generalInfoActivityIntentFactory(Context context, Boolean isAdmin, String username){
        Intent intent = new Intent(context, GeneralInfoActivity.class);
        intent.putExtra(ADMIN_KEY, isAdmin);
        intent.putExtra(USERNAME_KEY, username);
        return intent;
    }
}