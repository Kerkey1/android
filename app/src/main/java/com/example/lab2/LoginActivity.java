package com.example.lab2;

import static com.example.lab2.DBContract.Users.TABLE_NAME;
import static java.lang.Math.random;
import static kotlin.random.RandomKt.Random;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class LoginActivity extends AppCompatActivity {
    DBHandler db = new DBHandler(this);
    DepartmentHandler depDB = new DepartmentHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginact);
        Button buttonLogin = findViewById(R.id.ButtonLogin);
        Button buttonLogUp = findViewById(R.id.button_log_up);
        TextView login = findViewById(R.id.LoginField);
        TextView password = findViewById(R.id.PasswordField);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String saveLogin = login.getText().toString();
                int isExist = db.isUser(saveLogin, password.getText().toString());
                if (isExist != -1) {
                    Intent myIntent = new Intent(LoginActivity.this, UserProfile.class);
                    myIntent.putExtra("user", saveLogin);
                    myIntent.putExtra("id", isExist);
                    startActivity(myIntent);
                }
            }
        });

        buttonLogUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int isExist = db.getId(login.getText().toString());
                int count = depDB.getAllDepartments().size();
                int rand = 1 + (int) (Math.random() * count);
                if (isExist == -1) {
                    db.addUser(new User(login.getText().toString(), password.getText().toString(), rand));
                }
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
        db.close();
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
