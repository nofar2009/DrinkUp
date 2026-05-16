package com.example.drinkup;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // ---- הקישור החדש של ה-Toolbar לג'אווה ----
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // -------------------------------------------

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        EditText etWaterDrank = findViewById(R.id.etWaterDrank);
        Button btnCalculate = findViewById(R.id.btnCalculate);

        String name = getIntent().getStringExtra("USER_NAME");
        int goal = Integer.parseInt(getIntent().getStringExtra("DAILY_GOAL"));

        tvWelcome.setText("helo " + name + "!");

        // הפעולה של כפתור החישוב
        btnCalculate.setOnClickListener(v -> {
            String drankInput = etWaterDrank.getText().toString();
            if (drankInput.isEmpty()) {
                drankInput = "0";
            }

            int drank = Integer.parseInt(drankInput);
            int left = goal - drank;

            String dialogTitle;
            String dialogMessage;

            if (left > 0) {
                dialogTitle = "Keep Going!";
                dialogMessage = "You have left more " + left + " cups to the destination.";
            } else {
                dialogTitle = "Good job !";
                dialogMessage = "You have reached your destination ! Good job";
            }

            new AlertDialog.Builder(this)
                    .setTitle(dialogTitle)
                    .setMessage(dialogMessage)
                    .setCancelable(false)
                    .setPositiveButton("Confirm & Back", (dialog, which) -> {
                        Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    })
                    .show();
        });
    }

    // פונקציה שמייצרת את שלוש הנקודות של התפריט על ה-Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("about");
        menu.add("exit");
        return true;
    }

    // פונקציה שמקשיבה ללחיצות על פריטי התפריט
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("exit")) {
            finishAffinity();
        }
        if (item.getTitle().equals("about")) {
            Toast.makeText(this, "DrinkUp - the app for your health", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}