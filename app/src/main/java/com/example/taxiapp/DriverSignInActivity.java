package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class DriverSignInActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputName;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputConfirmPassword;

    private Button loginSignUpButton;
    private TextView toggleLoginSignUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign_in);

        textInputEmail = findViewById(R.id.textInputEmail);
        textInputName = findViewById(R.id.textInputName);
        textInputPassword = findViewById(R.id.textInputPassword);
        textInputConfirmPassword = findViewById(R.id.textInputConfirmPassword);

        loginSignUpButton = findViewById(R.id.loginSignUpButton);
        toggleLoginSignUpTextView = findViewById(R.id.toggleLoginSignUpTextView);

    }

    private boolean validateName() { // Проверяем корректность ввода имени
        String nameInput = textInputName.getEditText().getText().toString().trim();
        //Получаем EditText/получаем непосредственно сам текст/переводим в String/обрезаем пробелы

        if (nameInput.isEmpty()) {
            textInputName.setError("Please, input your name");
            return false;
        } else if (nameInput.length() >15) {
            textInputName.setError("Name length have to be less than 15");
            return false;
        }
        else {
            textInputName.setError("");
            return true;
        }
    }

    private boolean validateEmail() { // Проверяем корректность ввода мыла
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        //Получаем EditText/получаем непосредственно сам текст/переводим в String/обрезаем пробелы

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Please, input your email");
            return false;
        } else if (!emailInput.contains("@")) {
            textInputEmail.setError("Email must contain an '@' symbol");
            return false;
        } else {
            textInputEmail.setError("");
            return true;
        }
    }

    private boolean validatePassword() { // Проверяем корректность пароля
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        //Получаем EditText/получаем непосредственно сам текст/переводим в String/обрезаем пробелы
        String confirmPasswordInput = textInputConfirmPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Please, input your password");
            return false;
        } else if (passwordInput.length() < 7) {
            textInputPassword.setError("Password length have to be more than 6");
            return false;
        } else if (!passwordInput.equals(confirmPasswordInput)) {
            textInputPassword.setError("Passwords have to match");
            return false;
        }
        else {
            textInputName.setError("");
            return true;
        }
    }

    public void loginSignUpDriver(View view) {
        if (!validateEmail() | !validateName() | !validatePassword()) {
            return;
        }

        String userInput =  "Name: " + textInputName.getEditText().getText().toString().trim() + "\n" +
                            "Email: " + textInputEmail.getEditText().getText().toString().trim() + "\n" +
                            "Password: " + textInputPassword.getEditText().getText().toString().trim() + "\n";


        Toast.makeText(this, userInput, Toast.LENGTH_LONG).show();
    }

    public void toggleLoginSignUpDriver(View view) {
    }

}