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

import com.example.parakeet.Parakeet_Database.Entities.User;
import com.example.parakeet.Parakeet_Database.Repository;
import com.example.parakeet.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private Repository repository;
    private User user = null;
    ActivityLoginBinding binding;

    private Boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = Repository.getRepository(getApplication());


        binding.loginButton.setOnClickListener(v -> {
            if (verifyUser()) {
                Intent intent = LandingPageActivity.landingPageActivityIntentFactory(LoginActivity.this, isAdmin);
                startActivity(intent);
            }
        });

        binding.returnButton.setOnClickListener(v -> {
            Intent intent = MainActivity.mainActivityIntentFactory(LoginActivity.this);
            startActivity(intent);
        });
    }

    private boolean verifyUser(){
        String username = binding.usernameEditText.getText().toString();
        if (username.isEmpty()){
            toastMaker("Username may not be blank.");
            return false;
        }
        user = repository.getUserByUsername(username);
        if (user != null){
            String password = binding.passwordEditText.getText().toString();
            if (password.equals(user.getPassword())){
                isAdmin = user.isIs_admin();
                return true;
            } else {
                toastMaker("Invalid Password");
                return false;
            }
        }

        toastMaker(String.format("No %s found",
                username));

        return false;

    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    static Intent loginActivityIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }
}