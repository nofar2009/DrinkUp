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


        String name = getIntent().getStringExtra("USER_NAME");
        int goal = Integer.parseInt(getIntent().getStringExtra("DAILY_GOAL"));

        tvWelcome.setText("helo " + name + "!");

        btnCalculate.setOnClickListener(v -> {
            int drank = Integer.parseInt(etWaterDrank.getText().toString());
            int left = goal - drank;


            dynamicContainer.removeAllViews();


            TextView tvResult = new TextView(this);
            tvResult.setTextSize(18);

            if (left > 0) {
                tvResult.setText("you hve left more " + left + " cups to the destintion");
            } else {
                tvResult.setText("you hve reached your destintion ! good jub");
                showSuccessDialog();
            }
            dynamicContainer.addView(tvResult);
        });
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("good jub !")
                .setMessage("you made it to your drink destintion today!")
                .setPositiveButton("confirm", null)
                .show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("about");
        menu.add("exit");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("exit")) finishAffinity();
        if (item.getTitle().equals("about")) Toast.makeText(this, "DrinkUp - the app for your health", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}