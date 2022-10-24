package com.example.lab2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);
        String passwordState = "123";

        Button buttonLogin = findViewById(R.id.ButtonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText login = findViewById(R.id.LoginField);
                EditText password = findViewById(R.id.PasswordField);
                if (passwordState.equals(password.getText().toString())) {
                    Intent myIntent = new Intent(LoginActivity.this, HelloActivity.class);
                    myIntent.putExtra("user", login.getText().toString());
                    startActivity(myIntent);
                }
            }

        });

        Button buttonViewTable = findViewById(R.id.ButtonViewTable);
        buttonViewTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, HelloActivity.class);
                startActivity(myIntent);
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("AppLogger", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("AppLogger", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("AppLogger", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("AppLogger", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("AppLogger", "onDestroy");
    }
}
