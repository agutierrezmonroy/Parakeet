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

import com.example.parakeet.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.loginButton.setOnClickListener(v -> {
            Intent intent = LandingPageActivity.landingPageActivityIntentFactory(LoginActivity.this);
            startActivity(intent);
        });

        binding.returnButton.setOnClickListener(v -> {
            Intent intent = MainActivity.mainActivityIntentFactory(LoginActivity.this);
            startActivity(intent);
        });
    }

    static Intent loginActivityIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }
}