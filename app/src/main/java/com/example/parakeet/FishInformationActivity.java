package com.example.parakeet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.parakeet.Parakeet_Database.Repository;
import com.example.parakeet.databinding.ActivityFishInformationBinding;
import com.example.parakeet.Parakeet_Database.Entities.Fish;

import com.example.parakeet.databinding.ActivityGeneralInfoBinding;

public class FishInformationActivity extends AppCompatActivity {

    ActivityFishInformationBinding binding;
    private static final String USERNAME_KEY = "com.example.parakeet.username";

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFishInformationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String username = getIntent().getStringExtra(USERNAME_KEY);
        repository = Repository.getRepository(getApplication());


        binding.logFishButton.setOnClickListener(v -> {
            String lengthStr = binding.fishLengthEditText.getText().toString().trim();
            String weightStr = binding.fishLengthEditText.getText().toString().trim();

            if(lengthStr.isEmpty() || weightStr.isEmpty()){
                Toast.makeText(this, "Please enter length and weight", Toast.LENGTH_SHORT).show();
                return;
            }

            double length = Double.parseDouble(lengthStr);
            double weight = Double.parseDouble(weightStr);

            Fish fish = new Fish(length, weight, true);
            repository.insertFish(fish);
            Toast.makeText(this, "Fish logged", Toast.LENGTH_SHORT).show();
        });
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