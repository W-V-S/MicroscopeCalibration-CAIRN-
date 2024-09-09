package com.standrews.microscopealignment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class FrameRateInputActivity extends AppCompatActivity {

    private EditText frameRateInput;
    private EditText maxFramesInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_rate_input);

        frameRateInput = findViewById(R.id.frameRateInput);
        maxFramesInput = findViewById(R.id.maxFramesInput);
        Button executeButton = findViewById(R.id.executeButton);
        Button returnHomeButton = findViewById(R.id.returnHomeButton);

        executeButton.setOnClickListener(v -> {
            String frameRateStr = frameRateInput.getText().toString();
            String maxFramesStr = maxFramesInput.getText().toString();

            if (frameRateStr.isEmpty()) {
                Toast.makeText(FrameRateInputActivity.this, "Please enter a frame rate", Toast.LENGTH_SHORT).show();
                return;
            }

            int frameRate = Integer.parseInt(frameRateStr);
            int maxFrames = maxFramesStr.isEmpty() ? -1 : Integer.parseInt(maxFramesStr);

            Intent intent = new Intent(FrameRateInputActivity.this, FrameRateExecutionActivity.class);
            intent.putExtra("FRAME_RATE", frameRate);
            intent.putExtra("MAX_FRAMES", maxFrames);
            startActivity(intent);
        });

        returnHomeButton.setOnClickListener(v -> {
            Intent returnIntent = new Intent(FrameRateInputActivity.this, MainActivity.class);
            returnIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(returnIntent);
        });
    }
}


