package com.example.drinkup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText etUserName = findViewById(R.id.etUserName);
        EditText etDailyGoal = findViewById(R.id.etDailyGoal);
        Button btnSend = findViewById(R.id.btnSend);
        Button btnCamera = findViewById(R.id.btnCamera);


        btnSend.setOnClickListener(v -> {
            String name = etUserName.getText().toString();
            String goal = etDailyGoal.getText().toString();


            SharedPreferences sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("name", name);
            editor.putString("goal", goal);
            editor.apply();


            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
            intent.putExtra("USER_NAME", name);
            intent.putExtra("DAILY_GOAL", goal);
            startActivity(intent);
        });


        btnCamera.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        });
    }
}