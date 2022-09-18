package com.example.lab2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HelloActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloact);
        ArrayList<String> myStringArray = new ArrayList<String>();
        myStringArray.add("dqdq");
        myStringArray.add("dqdas1231q");
        ListView textList = findViewById(R.id.textList);
        ArrayAdapter<String> TextAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, myStringArray);
        textList.setAdapter(TextAdapter);

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
                login.setHint("Login");
                layout.addView(login);

                EditText password = new EditText(HelloActivity.this);
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                password.setHint("Password");
                layout.addView(password);

                alertDialog.setView(layout);
                alertDialog.setButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        myStringArray.add(login.getText().toString());
                    }
                });
                alertDialog.show();
            }
        });

        textList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                AlertDialog alertDialog = new AlertDialog.Builder(HelloActivity.this).create();
                alertDialog.setTitle("Delete");

                LinearLayout layout = new LinearLayout(HelloActivity.this);
                layout.setOrientation(LinearLayout.VERTICAL);

//                alertDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {}
//                });

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
    }


}
