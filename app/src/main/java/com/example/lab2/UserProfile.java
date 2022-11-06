package com.example.lab2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {
    DBHandler db = new DBHandler(this);
    DepartmentHandler depDB = new DepartmentHandler(this);
    String user;
    int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Get current user
        Bundle arguments = getIntent().getExtras();
        user = arguments.get("user").toString();
        id = (int) arguments.get("id");
        Button buttonDeleteUser = findViewById(R.id.DeleteAccount);
        Button buttonChangePassword = findViewById(R.id.ChangePassword);
        Button buttonAddDepartment = findViewById(R.id.AddDep);
        TextView login = findViewById(R.id.UserName);
        login.setText(user);


        int depId = db.getDepartments(user);
        String dep = depDB.getDepartmentById(depId);
        Log.v("dep", dep);
        List<User> usersList = db.getAllUsers();

        buttonDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteUserByName(user);
                Intent myIntent = new Intent(UserProfile.this, LoginActivity.class);
                startActivity(myIntent);
            }
        });

        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
                builder.setTitle("Update password").setMessage("Enter new password");

                final EditText input = new EditText(UserProfile.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v("NEW...", input.getText().toString());
                        Log.v("NEW...", String.valueOf(id));
                        db.updatePassword(id, input.getText().toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

        buttonAddDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserProfile.this);
                builder.setTitle("Create department").setMessage("Enter department name");

                final EditText input = new EditText(UserProfile.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int isExist = depDB.getId(login.getText().toString());
                        if (isExist == -1) {
                            depDB.addDepartment(new Department(input.getText().toString()));
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });
    }
}
