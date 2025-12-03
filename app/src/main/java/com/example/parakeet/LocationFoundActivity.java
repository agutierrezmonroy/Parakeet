package com.example.parakeet;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.parakeet.databinding.ActivityGeneralInfoBinding;
import com.example.parakeet.databinding.ActivityLocationFoundBinding;

public class LocationFoundActivity extends AppCompatActivity {

    ActivityLocationFoundBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLocationFoundBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}