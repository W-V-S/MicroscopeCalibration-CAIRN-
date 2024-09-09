package com.standrews.microscopealignment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class CellActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell);

        ImageView imageView = findViewById(R.id.image_view_cell);
        Button returnMenuButton = findViewById(R.id.button_return_menu);

        // Convert 1 cm to pixels
        float cmToPx = convertCmToPx(1);

        // Set the ImageView width to 1 cm (in pixels)
        imageView.getLayoutParams().width = (int) cmToPx;
        imageView.requestLayout();

        returnMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // This will close CellActivity and return to MenuActivity
            }
        });
    }

    private float convertCmToPx(float cm) {
        // Get the screen's density in dpi
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return cm * (metrics.xdpi / 2.54f);  // Convert cm to inches and then to pixels
    }
}


