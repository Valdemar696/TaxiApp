package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class PassengerSignInActivity extends AppCompatActivity {

    private TextInputLayout nameEditText;
    private TextInputLayout emailEditText;
    private TextInputLayout passwordEditText;
    private TextInputLayout confirmPasswordEditText;

    private Button signUpButton;
    private TextView logInTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_sign_in);

        nameEditText = findViewById(R.id.textInputName);
        emailEditText = findViewById(R.id.textInputEmail);
        passwordEditText = findViewById(R.id.textInputPassword);
        confirmPasswordEditText = findViewById(R.id.textInputConfirmPassword);

        signUpButton = findViewById(R.id.loginSignUpButton);
        logInTextView = findViewById(R.id.toggleLoginSignUpTextView);
    }

    private boolean validateName(){
        String nameInput = nameEditText.getEditText().getText().toString().trim();

        if (nameInput.isEmpty()) {
            nameEditText.setError("Please, input your name");
            return false;
        } else if (nameInput.length() > 15) {
            nameEditText.setError("Name length have to be less than 15");
            return false;
        } else {
            nameEditText.setError("");
            return true;
        }
    }

    private boolean validateEmail () {
        String emailInput = emailEditText.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            emailEditText.setError("Please, input your email");
            return false;
        } else if (!emailInput.contains("@")) {
            emailEditText.setError("Email must contain an '@' symbol");
            return false;
        } else {
            emailEditText.setError("");
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = passwordEditText.getEditText().getText().toString().trim();
        String confirmPasswordInput = confirmPasswordEditText.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            passwordEditText.setError("Please, input your password");
            return false;
        } else if (passwordInput.length() < 7) {
            passwordEditText.setError("Password length have to be more than 6");
            return false;
        } else if (!passwordInput.equals(confirmPasswordInput)) {
            passwordEditText.setError("Passwords have to match");
            return false;
        } else {
            passwordEditText.setError("");
            return true;
        }
    }

    public void loginSignUpUser(View view) {
        if (!validateEmail() | !validateName() | !validatePassword()) {
            return;
        }

        String userInput =  "Name: " + nameEditText.getEditText().getText().toString().trim() + "\n" +
                "Email: " + emailEditText.getEditText().getText().toString().trim() + "\n" +
                "Password: " + passwordEditText.getEditText().getText().toString().trim() + "\n";


        Toast.makeText(this, userInput, Toast.LENGTH_LONG).show();
    }


    public void toggleLoginSignUpUser(View view) {
    }
}