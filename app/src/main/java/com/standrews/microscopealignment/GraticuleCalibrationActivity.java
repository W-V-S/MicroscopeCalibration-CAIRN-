package com.standrews.microscopealignment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GraticuleCalibrationActivity extends AppCompatActivity {

    private Button buttonReturnHome;
    private TextView textViewScreenDimensions;
    private RulerView rulerView;
    private int oneCmInPixels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graticule_calibration);

        buttonReturnHome = findViewById(R.id.buttonReturnHome);
        textViewScreenDimensions = findViewById(R.id.textViewScreenDimensions);
        rulerView = findViewById(R.id.rulerView);

        buttonReturnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GraticuleCalibrationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        displayScreenDimensions();
        drawOneCentimeterLines();
    }

    private void displayScreenDimensions() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        float density = displayMetrics.density;
        int dpi = displayMetrics.densityDpi;

        // Convert pixels to centimeters
        double heightCm = height / (double) dpi * 2.54;
        double widthCm = width / (double) dpi * 2.54;

        // Calculate major and minor tick distances in cm
        double majorTickDistanceCm = 0.1;
        double minorTickDistanceCm = 0.01;

        String dimensions = "Height: " + height + "px (" + String.format("%.2f", heightCm) + " cm)\n" +
                "Width: " + width + "px (" + String.format("%.2f", widthCm) + " cm)\n" +
                "Density: " + density + "\n" +
                "DPI: " + dpi + "\n" +
                "Horizontal line length: 1 cm\n" +
                "Major tick distance: " + String.format("%.2f", majorTickDistanceCm) + " cm\n" +
                "Minor tick distance: " + String.format("%.2f", minorTickDistanceCm) + " cm";
        textViewScreenDimensions.setText(dimensions);
    }

    private void drawOneCentimeterLines() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int dpi = displayMetrics.densityDpi;

        // Calculate 1cm in pixels
        oneCmInPixels = (int) (dpi / 2.54);

        // Set the length of the lines to 1cm
        rulerView.setLineLength(oneCmInPixels);
    }

    public static class RulerView extends View {

        private Paint paint;
        private int lineLength; // length of the main line in pixels

        public RulerView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        private void init() {
            paint = new Paint();
            paint.setColor(0xFF000000); // black color
            paint.setStrokeWidth(3);
        }

        public void setLineLength(int length) {
            this.lineLength = length;
            invalidate(); // redraw the view
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            if (lineLength > 0) {
                // Draw the main lines
                canvas.drawLine(getWidth() / 2, (getHeight() - lineLength) / 2, getWidth() / 2, (getHeight() + lineLength) / 2, paint); // vertical
                canvas.drawLine((getWidth() - lineLength) / 2, getHeight() / 2, (getWidth() + lineLength) / 2, getHeight() / 2, paint); // horizontal

                // Draw ruler marks
                for (int i = 0; i <= 100; i++) {
                    int offset = (int) (lineLength * (i / 100.0));
                    int majorLength = 20;
                    int minorLength = 10;
                    if (i % 10 == 0) {
                        // Major marks
                        canvas.drawLine(getWidth() / 2 - majorLength / 2, (getHeight() - lineLength) / 2 + offset, getWidth() / 2 + majorLength / 2, (getHeight() - lineLength) / 2 + offset, paint); // vertical major
                        canvas.drawLine((getWidth() - lineLength) / 2 + offset, getHeight() / 2 - majorLength / 2, (getWidth() - lineLength) / 2 + offset, getHeight() / 2 + majorLength / 2, paint); // horizontal major
                    } else {
                        // Minor marks
                        canvas.drawLine(getWidth() / 2 - minorLength / 2, (getHeight() - lineLength) / 2 + offset, getWidth() / 2 + minorLength / 2, (getHeight() - lineLength) / 2 + offset, paint); // vertical minor
                        canvas.drawLine((getWidth() - lineLength) / 2 + offset, getHeight() / 2 - minorLength / 2, (getWidth() - lineLength) / 2 + offset, getHeight() / 2 + minorLength / 2, paint); // horizontal minor
                    }
                }
            }
        }
    }
}







