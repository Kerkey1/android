package com.example.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class HelloActivity extends Activity {
    int count1 = 0;
    int count2 = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helloact);


        Button button1 = findViewById(R.id.button1);
        TextView tv1 = findViewById(R.id.tv1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count1++;
                tv1.setText(String.valueOf(count1));
            }
        });


        Button button2 = findViewById(R.id.button2);
        TextView tv2 = findViewById(R.id.tv2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count2++;
                tv2.setText(String.valueOf(count2));
            }
        });

        Button resetButton = findViewById(R.id.buttonReset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count1 = 0;
                count2 = 0;
                tv1.setText(String.valueOf(count1));
                tv2.setText(String.valueOf(count2));
            }
        });
    }
}

