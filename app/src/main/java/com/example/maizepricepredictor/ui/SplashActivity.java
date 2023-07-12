package com.example.maizepricepredictor.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maizepricepredictor.MainActivity;
import com.example.maizepricepredictor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // User is already signed in, allow them to continue using the app
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            // No user is signed in, show the login screen
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish(); //Prevents splash screen from being included in app's back stack.
        }


    }
}