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
import androidx.room.RoomSQLiteQuery;

import com.example.parakeet.Parakeet_Database.Repository;
import com.example.parakeet.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = Repository.getRepository(getApplication());


        binding.previousLoginButton.setOnClickListener(v -> {
            Intent intent = LoginActivity.loginActivityIntentFactory(MainActivity.this);
            startActivity(intent);
        });

        binding.createAccountButton.setOnClickListener(v -> {
            Intent intent = LoginActivity.loginActivityIntentFactory(MainActivity.this);
            startActivity(intent);
        });

        binding.createAccountButton.setOnClickListener(v -> {
            Intent intent = CreateAccountActivity.createAccountIntentFactory(MainActivity.this);
            startActivity(intent);
        });

    }

    static Intent mainActivityIntentFactory(Context context) {
        return new Intent(context, MainActivity.class);
    }
}


