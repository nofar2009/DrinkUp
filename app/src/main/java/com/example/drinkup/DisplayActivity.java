package com.example.drinkup;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        EditText etWaterDrank = findViewById(R.id.etWaterDrank);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        LinearLayout dynamicContainer = findViewById(R.id.dynamicContainer);

        // קבלת הנתונים מהמסך הקודם
        String name = getIntent().getStringExtra("USER_NAME");
        int goal = Integer.parseInt(getIntent().getStringExtra("DAILY_GOAL"));

        tvWelcome.setText("שלום " + name + "!");

        btnCalculate.setOnClickListener(v -> {
            int drank = Integer.parseInt(etWaterDrank.getText().toString());
            int left = goal - drank;

            // הסרת טקסט קודם אם היה
            dynamicContainer.removeAllViews();

            // יצירה דינמית של TextView (דרישת חובה)
            TextView tvResult = new TextView(this);
            tvResult.setTextSize(18);

            if (left > 0) {
                tvResult.setText("נשארו לך עוד " + left + " כוסות ליעד!");
            } else {
                tvResult.setText("הגעת ליעד! כל הכבוד!");
                // הצגת Dialog (דרישת חובה)
                showSuccessDialog();
            }
            dynamicContainer.addView(tvResult);
        });
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("כל הכבוד!")
                .setMessage("הגעת ליעד השתייה שלך להיום!")
                .setPositiveButton("אישור", null)
                .show();
    }

    // הוספת Options Menu (דרישת חובה)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("אודות");
        menu.add("יציאה");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("יציאה")) finishAffinity();
        if (item.getTitle().equals("אודות")) Toast.makeText(this, "DrinkUp - אפליקציה לבריאות שלך", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}