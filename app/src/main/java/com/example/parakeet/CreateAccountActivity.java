package com.example.parakeet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
    }
    public static Intent createAccountIntentFactory(Context context){
        return new Intent(context, CreateAccountActivity.class);
    }
}
