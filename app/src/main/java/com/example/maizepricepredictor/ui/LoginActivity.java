package com.example.maizepricepredictor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.maizepricepredictor.MainActivity;
import com.example.maizepricepredictor.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText emailAddress;
    EditText password;
    Button loginBtn;
    TextView passwordReset;
    TextView registerPg;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailAddress = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);

        passwordReset = findViewById(R.id.textViewFgPWD);
        registerPg = findViewById(R.id.textSignUpPg);

       loginBtn.setOnClickListener(this);
        passwordReset.setOnClickListener(this);
        registerPg.setOnClickListener(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        Log.d("LoginActivity", "onClick called with view id: " + view.getId());
        int viewId = view.getId();
        if (viewId == R.id.textSignUpPg) {
            OpenRegisterActivity();
        } else if (viewId == R.id.textViewFgPWD) {
            OpenPasswordResetActivity();
        } else if (viewId == R.id.login_btn) {
            OpenMainActivity();
        }
    }

    private void OpenMainActivity() {
        EditText email = findViewById(R.id.email);
        EditText pwd = findViewById(R.id.password);

        String emailAddress = email.getText().toString();
        String password = pwd.getText().toString();

        // Validating input
        if (!emailAddress.isEmpty() && !password.isEmpty()) {
            mAuth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Intent destIntent = new Intent(this, MainActivity.class);
                    startActivity(destIntent);
                    finish();
                } else {
                    Toast.makeText(this, "Email provided does not match our records", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
        }
    }

    private void OpenPasswordResetActivity() {
        Intent intent = new Intent(this, PasswordResetActivity.class);
        startActivity(intent);
    }

    private void OpenRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}