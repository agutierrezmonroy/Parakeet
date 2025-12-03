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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGeneralInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        boolean isAdmin = getIntent().getBooleanExtra(ADMIN_KEY, false);

        binding.giReturnToHubButton.setOnClickListener(v -> {
                Intent intent = LandingPageActivity.landingPageActivityIntentFactory(GeneralInfoActivity.this, isAdmin);
                startActivity(intent);
        });
    }

    static Intent generalInfoActivityIntentFactory(Context context, Boolean isAdmin){
        Intent intent = new Intent(context, GeneralInfoActivity.class);
        intent.putExtra(ADMIN_KEY, isAdmin);
        return intent;
    }
}