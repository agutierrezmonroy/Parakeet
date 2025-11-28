package com.example.parakeet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.parakeet.databinding.ActivityLandingPageBinding;

public class LandingPageActivity extends AppCompatActivity {

    private ActivityLandingPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.generalInfoButton.setOnClickListener(v -> {
            Intent intent = LoginActivity.loginActivityIntentFactory(LandingPageActivity.this);
            startActivity(intent);
        });
    }

    static Intent landingPageActivityIntentFactory(Context context){
        Intent intent = new Intent(context, LandingPageActivity.class);
        return intent;
    }
}