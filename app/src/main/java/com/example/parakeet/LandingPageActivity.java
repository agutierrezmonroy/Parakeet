package com.example.parakeet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;

import com.example.parakeet.Parakeet_Database.Entities.User;
import com.example.parakeet.Parakeet_Database.Repository;
import com.example.parakeet.databinding.ActivityLandingPageBinding;

public class LandingPageActivity extends AppCompatActivity {
    private static final String USERNAME_KEY = "com.example.parakeet.username";
    private Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLandingPageBinding binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = Repository.getRepository(getApplication());

        String username = getIntent().getStringExtra(USERNAME_KEY);

        binding.generalInfoButton.setOnClickListener(v -> {
            Intent intent = GeneralInfoActivity.generalInfoActivityIntentFactory(LandingPageActivity.this, username);
            startActivity(intent);
        });

        binding.locationButton.setOnClickListener(v -> {
            Intent intent = LocationActivity.locationActivityIntentFactory(LandingPageActivity.this, username);
            startActivity(intent);
        });

        binding.caughtInfoButton.setOnClickListener(v -> {
            Intent intent = FishInformationActivity.fishInformationActivityIntentFactory(LandingPageActivity.this, username);
            startActivity(intent);
        });

        binding.logOutButton.setOnClickListener(v -> {
            Intent intent = LoginActivity.loginActivityIntentFactory(LandingPageActivity.this, false);
            startActivity(intent);
        });

        LiveData<User> userObserver = repository.getUserByUsername(username);
        userObserver.observe(this, user -> {

            if(user.isIs_admin()){
                binding.adminButton.setVisibility(View.VISIBLE);
            }

            binding.usernameText.setText(getString(R.string.welcome, user.getUsername()));

        });
    }

   public static Intent landingPageActivityIntentFactory(Context context, String username) {
        Intent intent = new Intent(context, LandingPageActivity.class);
        intent.putExtra(USERNAME_KEY, username);
        return intent;
    }
}