package com.example.drinkup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {

    private ImageView imageView;

    // מאזין שמחכה לקבל את התמונה שצולמה
    private final ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Bundle extras = result.getData().getExtras();
                    if (extras != null && extras.containsKey("data")) {
                        Bitmap photo = (Bitmap) extras.get("data");
                        imageView.setImageBitmap(photo);
                    }
                } else {
                    Toast.makeText(this, "Action canceled or failed", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imageView = findViewById(R.id.imageView);
        Button buttonCamera = findViewById(R.id.buttonCamera);
        ImageButton btnClose = findViewById(R.id.btnClose);

        // מבטיח שכפתור האיקס יהיה בשכבה העליונה ביותר ולא ייחסם ללחיצה
        btnClose.bringToFront();

        // לחיצה על כפתור המצלמה
        buttonCamera.setOnClickListener(v -> {
            try {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraLauncher.launch(cameraIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Unable to open camera", Toast.LENGTH_LONG).show();
            }
        });

        // ---- השינוי כאן: לחיצה על כפתור האיקס מחזירה ישירות לעמוד הראשי ומאפסת ----
        btnClose.setOnClickListener(v -> {
            Intent intent = new Intent(CameraActivity.this, MainActivity.class);
            // הדגלים האלו מנקים את כל העמודים שהיו בדרך ומחזירים לעמוד הראשי כשהוא נקי ומאותר
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // סוגר את עמוד המצלמה הנוכחי
        });
    }
}