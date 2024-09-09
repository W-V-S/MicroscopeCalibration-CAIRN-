package com.standrews.microscopealignment;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        // Retrieve the TextView and set the information text
        TextView informationTextView = findViewById(R.id.tvInformation);
        informationTextView.setText("App developed for Cairn Research Ltd by William V. Smith. " +
                "\n \n" +
                "Frame Rate Calibrator: Adjustable box animation for specific frame-rate generation." +
                "\n \n" +
                "RGB Calibrator:" +
                "\n" +
                "Geometric - beads of RGB" +
                "\n" +
                "Cell - RGB in cellular form" +
                "\n \n" +
                "Graticule Calibration: Adjustable graticules for low magnification length adjustment");

        // Setup the return to main button
        Button returnToMainButton = findViewById(R.id.btnReturnToMain);
        returnToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

