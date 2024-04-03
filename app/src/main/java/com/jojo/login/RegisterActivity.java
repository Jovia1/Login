package com.jojo.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    private EditText emailField, passwordField, c_passwordField;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.passwordField);
        c_passwordField = findViewById(R.id.c_passwordField);

        // Initialize registrationButton
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndSubmit();
            }
        });
    }

        private void validateAndSubmit() {
            // Reset Errors
            emailField.setError(null);
            passwordField.setError(null);
            c_passwordField.setError(null);

            // Get values from EditText fields
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();
            String c_password = c_passwordField.getText().toString();

            boolean isValid = true;

            //Check email
            if (TextUtils.isEmpty(email)) {
                emailField.setError("Email is required");
                isValid = false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailField.setError("Invalid email address");
                isValid = false;
            }

            //Check password
            if (TextUtils.isEmpty(password)) {
                passwordField.setError("Password is required");
                isValid = false;
            } else if (password.length() < 8) {
                passwordField.setError("Password must be at least 8 characters long");
                isValid = false;

            }

            if (TextUtils.isEmpty(password)) {
                c_passwordField.setError("Confirm Password is required");
                isValid = false;
            } else if (!c_password.equals(password)) {
                c_passwordField.setError("Passwords do not match");
                isValid = false;
            }

            // if all fields are valid , proceed with registration to login
            if (isValid) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

    }
}