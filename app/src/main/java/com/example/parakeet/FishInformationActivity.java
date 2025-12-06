package com.example.parakeet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.parakeet.databinding.ActivityFishInformationBinding;
import com.example.parakeet.databinding.ActivityGeneralInfoBinding;

public class FishInformationActivity extends AppCompatActivity {

    ActivityFishInformationBinding binding;
    private static final String USERNAME_KEY = "com.example.parakeet.username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFishInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String username = getIntent().getStringExtra(USERNAME_KEY);


        binding.filocatiionReturnButton.setOnClickListener(v -> {
            Intent intent = LandingPageActivity.landingPageActivityIntentFactory(getApplicationContext(), username);
            startActivity(intent);
        });
    }


    static Intent fishInformationActivityIntentFactory(Context context, String username){
        Intent intent = new Intent(context, FishInformationActivity.class);
        intent.putExtra(USERNAME_KEY, username);
        return intent;
    }
}