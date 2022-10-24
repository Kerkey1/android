package com.example.lab2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class HelloActivity extends AppCompatActivity {
    private static final long DOUBLE_CLICK_TIME = 300;
    long lastClickTime = 0;
    ArrayList<String> myStringArray;
    String user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloact);

        //Get current user
        Bundle arguments = getIntent().getExtras();
        user = arguments.get("user").toString();
        SwitchCompat switch_btn = findViewById(R.id.switch_compat);

        //Load list from shPref
        Gson gson = new Gson();
        SharedPreferences sharedPref = this.getSharedPreferences(user, Context.MODE_PRIVATE);
        if (sharedPref.contains(user)) {
            String bs = sharedPref.getString(user, "");
            String[] text = gson.fromJson(bs, String[].class);
            myStringArray = new ArrayList<>(Arrays.asList(text));
            switch_btn.setChecked(sharedPref.getBoolean("switch", false));
            if (switch_btn.isChecked())
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            myStringArray = new ArrayList<String>();
        }

        //Set TextAdapter
        ListView textList = findViewById(R.id.textList);
        ArrayAdapter<String> TextAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myStringArray);
        textList.setAdapter(TextAdapter);

        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(HelloActivity.this).create();
                alertDialog.setTitle("Log in");

                LinearLayout layout = new LinearLayout(HelloActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                EditText login = new EditText(HelloActivity.this);
                login.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                login.setHint("Text");
                layout.addView(login);

                alertDialog.setView(layout);
                alertDialog.setButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        myStringArray.add(login.getText().toString());
                    }
                });
                alertDialog.show();
            }
        });

        textList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(HelloActivity.this).create();
                alertDialog.setTitle("Delete");

                LinearLayout layout = new LinearLayout(HelloActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                alertDialog.setButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        myStringArray.remove(position);
                        TextAdapter.notifyDataSetChanged();
                    }
                });
                alertDialog.show();

                return false;
            }

        });

        textList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long clickTime = System.currentTimeMillis();
                if (clickTime - lastClickTime < DOUBLE_CLICK_TIME) {
                    myStringArray.remove(position);
                    TextAdapter.notifyDataSetChanged();
                }
                lastClickTime = clickTime;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        String json = new Gson().toJson(myStringArray);
        SharedPreferences sharedPref = this.getSharedPreferences(user, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(user, json);
        SwitchCompat switch_btn = findViewById(R.id.switch_compat);
        editor.putBoolean("switch", switch_btn.isChecked());
        editor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();
        String json = new Gson().toJson(myStringArray);
        SharedPreferences sharedPref = this.getSharedPreferences(user, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(user, json);
        editor.apply();
    }

}
