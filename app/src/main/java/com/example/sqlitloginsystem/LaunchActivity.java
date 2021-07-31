package com.example.sqlitloginsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_launch);
        @SuppressLint("WrongConstant") SharedPreferences sharedPreferences=getSharedPreferences("Myfile",MODE_APPEND);
        if (sharedPreferences.getString("LOGEDIN","").equals("1"))
        {
            startActivity(new Intent(LaunchActivity.this,HomePage.class));

        }
        else
        {
            startActivity(new Intent(LaunchActivity.this,MainActivity.class));
        }

    }
}