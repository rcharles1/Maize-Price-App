package com.example.maizepricepredictor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.maizepricepredictor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText emailAddress;
    EditText password;
    EditText confirmPassword;
    Button registerBtn;
    TextView loginPg;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        emailAddress = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        registerBtn = findViewById(R.id.register_btn);
        loginPg = findViewById(R.id.textSignInPg);

        registerBtn.setOnClickListener(this);
        loginPg.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.textSignInPg) {
            OpenSignInActivity();
        } else if (viewId == R.id.register_btn) {
            OpenLoginActivity();
        }
    }

    private void OpenLoginActivity() {

        EditText userName = findViewById(R.id.username);
        EditText email = findViewById(R.id.email);
        EditText pwd = findViewById(R.id.password);
        EditText confirmPwd = findViewById(R.id.confirmPassword);

        String username = userName.getText().toString();
        String emailAddress = email.getText().toString();
        String password = pwd.getText().toString();
        String confirmPassword = confirmPwd.getText().toString();

        if (!emailAddress.isEmpty() && !password.isEmpty() && !username.isEmpty() && !confirmPassword.isEmpty()) {
            //Check for matching passwords
            if (password.equals(confirmPassword)) {
                mAuth.createUserWithEmailAndPassword(emailAddress, password)
                        .addOnCompleteListener(this, task -> {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(this, LoginActivity.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Exception exception = task.getException();
                                if (exception != null) {
                                    FirebaseAuthException firebaseAuthException = (FirebaseAuthException) exception;
                                    switch (firebaseAuthException.getErrorCode()) {
                                        case "ERROR_EMAIL_ALREADY_IN_USE":
                                        case "ERROR_WEAK_PASSWORD":
                                            break;
                                        default:

                                            Toast.makeText(this, "Unexpected error. Check Your Connection", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            } else {
                Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show();
            }
        } else {
            //Display error message
            Toast.makeText(this, "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show();
        }
    }

    private void OpenSignInActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}