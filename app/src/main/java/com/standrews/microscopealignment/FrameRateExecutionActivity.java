package com.standrews.microscopealignment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class FrameRateExecutionActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private View colorBox;
    private TextView frameCountText;
    private int frameCount = 0;
    private boolean isWhite = true;
    private int frameRate;
    private int maxFrames;
    private Runnable runnable;
    private LarvaView larvaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_rate_execution);

        colorBox = findViewById(R.id.colorBox);
        frameCountText = findViewById(R.id.frameCountText);
        Button returnInputButton = findViewById(R.id.returnInputButton);
        larvaView = findViewById(R.id.larvaView);

        Intent intent = getIntent();
        frameRate = intent.getIntExtra("FRAME_RATE", 1);
        maxFrames = intent.getIntExtra("MAX_FRAMES", -1);

        runnable = new Runnable() {
            @Override
            public void run() {
                updateFrame();
                handler.postDelayed(this, 1000 / frameRate);
            }
        };
        handler.post(runnable);

        returnInputButton.setOnClickListener(v -> {
            Intent returnIntent = new Intent(FrameRateExecutionActivity.this, FrameRateInputActivity.class);
            returnIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(returnIntent);
        });

        larvaView.post(() -> larvaView.startMovement(frameRate, maxFrames));
    }

    private void updateFrame() {
        isWhite = !isWhite;
        colorBox.setBackgroundColor(isWhite ? Color.WHITE : Color.BLACK);
        frameCount++;
        frameCountText.setText(String.valueOf(frameCount));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}











