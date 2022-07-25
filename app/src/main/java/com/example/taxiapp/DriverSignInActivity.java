package com.example.taxiapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DriverSignInActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputName;
    private TextInputLayout textInputPassword;
    private TextInputLayout textInputConfirmPassword;

    private Button loginSignUpButton;
    private TextView toggleLoginSignUpTextView;

    private boolean isLoginModeActive;

    private static final String TAG = "DriverSignInActivity";

    private FirebaseAuth auth; //аутентификация бд фаербейза, 42 строка градла

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

        auth = FirebaseAuth.getInstance(); //иницилизируем аутентификацию

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
        } else if (!passwordInput.equals(confirmPasswordInput) && !isLoginModeActive) {
            textInputPassword.setError("Passwords have to match");
            return false;
        }else {
            textInputPassword.setError("");
            return true;
        }
    }

    public void loginSignUpDriver(View view) {
        if (!validateEmail() | !validateName() | !validatePassword()) {
            return;
        }

        if (isLoginModeActive) {
            auth.signInWithEmailAndPassword( // метод скопирован из документации Firebase
                                             // https://firebase.google.com/docs/auth/android/password-auth?authuser=0#java_2
                            textInputEmail.getEditText().getText().toString().trim(),
                            textInputPassword.getEditText().getText().toString().trim())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(DriverSignInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        } else {
            auth.createUserWithEmailAndPassword( // метод скопирован из документации Firebase
                                                 // https://firebase.google.com/docs/auth/android/password-auth?authuser=0#java_2
                            textInputEmail.getEditText().getText().toString().trim(),
                            textInputPassword.getEditText().getText().toString().trim())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = auth.getCurrentUser();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(DriverSignInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        }
    }

    public void toggleLoginSignUpDriver(View view) {

        if (isLoginModeActive) { // Sign Up
            isLoginModeActive = false;
            loginSignUpButton.setText("Sign Up");
            toggleLoginSignUpTextView.setText("Or, log in");
            textInputConfirmPassword.setVisibility(View.VISIBLE);
        } else { // Log In
            isLoginModeActive = true;
            loginSignUpButton.setText("Log In");
            toggleLoginSignUpTextView.setText("Or, sign up");
            textInputConfirmPassword.setVisibility(View.GONE);
        }

    }

}