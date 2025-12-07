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
import com.example.parakeet.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private Repository repository;
    ActivityLoginBinding binding;

    private static final String EXTRA_CREATE_ACCOUNT = "EXTRA_CREATE_ACCOUNT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = Repository.getRepository(getApplication());

        boolean createAccount = getIntent().getBooleanExtra(EXTRA_CREATE_ACCOUNT, false);


        if (createAccount) {
            setupCreateAccountMode();
        } else {
            setupLoginMode();
        }

        binding.returnButton.setOnClickListener(v -> {
            Intent intent = MainActivity.mainActivityIntentFactory(LoginActivity.this);
            startActivity(intent);
        });
    }

    private void setupLoginMode() {
        binding.loginButton.setText(R.string.login);
        binding.loginButton.setOnClickListener(v -> verifyUser());
    }

    private void setupCreateAccountMode() {
        binding.loginButton.setText(R.string.create_account);
        binding.usernameEditText.setHint("Create Username");
        binding.passwordEditText.setHint("Create Password");

        binding.loginButton.setOnClickListener(v -> createNewUser());
    }

    private void verifyUser(){
        String username = binding.usernameEditText.getText().toString();
        if (username.isEmpty()){
            toastMaker("Username may not be blank.");
            return;
        }
        LiveData<User> userObeserver = repository.getUserByUsername(username);
        userObeserver.observe(this, user ->{
            if (user != null) {
                String password = binding.passwordEditText.getText().toString();
                if (password.equals(user.getPassword())){
                    startActivity(LandingPageActivity.landingPageActivityIntentFactory(getApplicationContext(), user.getUsername()));
                }
                else {
                    toastMaker("Invalid Password");
                }
            }

            else {
                toastMaker(String.format("%s is not a valid username", username));
            }
            userObeserver.removeObservers(this);
        });
    }

    private void createNewUser() {
        String username = binding.usernameEditText.getText().toString().trim();
        String password = binding.passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            toastMaker("Username and password may not be blank.");
            return;
        }

        LiveData<User> userObserver = repository.getUserByUsername(username);
        userObserver.observe(this, existingUser -> {
            if (existingUser != null) {
                toastMaker("That username is already taken.");
            } else {
                User newUser = new User(username, password);
                repository.insertUser(newUser);

                toastMaker("Account created! Logging you in...");
                startActivity(LandingPageActivity.landingPageActivityIntentFactory(getApplicationContext(), username));
            }
            userObserver.removeObservers(this);
        });
    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    static Intent loginActivityIntentFactory(Context context, boolean createAccount){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(EXTRA_CREATE_ACCOUNT, createAccount);
        return intent;
    }
}