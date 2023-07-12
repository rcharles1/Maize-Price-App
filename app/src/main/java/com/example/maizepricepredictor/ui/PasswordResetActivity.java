package com.example.maizepricepredictor.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maizepricepredictor.R;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText emailAddress;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        emailAddress = findViewById(R.id.editTextTextEmailAddress);
        sendBtn = findViewById(R.id.button);

        sendBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        compareEmail(emailAddress);
    }

    private void compareEmail(EditText email) {
        if (!email.getText().toString().isEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Email sent! Check your email.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(this, "Enter an email address to continue", Toast.LENGTH_SHORT).show();
        }
    }

}