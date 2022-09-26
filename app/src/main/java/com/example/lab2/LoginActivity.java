package com.example.lab2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);

        String loginState = "123";
        String passwordState = "123";

        Button buttonLogin = findViewById(R.id.ButtonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText login = findViewById(R.id.LoginField);
                EditText password = findViewById(R.id.PasswordField);

                if (loginState.equals(login.getText().toString())  && passwordState.equals(password.getText().toString())) {
                    Intent myIntent = new Intent(LoginActivity.this, HelloActivity.class);
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
}
