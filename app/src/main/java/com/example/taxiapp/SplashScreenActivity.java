package com.example.taxiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread = new Thread() { //создаём поток для 3-х секундной заставки
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace(); // если не получилось поспать 3 сек - выводим ошибку
                } finally { // после сна запускаем другое действие (try-catch-finally, ну сам знаешь)
                    startActivity(new Intent(SplashScreenActivity.this, ChooseModeActivity.class));
                }
            }
        };

        thread.start(); // вызываем вышеуказанный поток
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}