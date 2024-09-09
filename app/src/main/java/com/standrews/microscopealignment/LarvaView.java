package com.standrews.microscopealignment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class LarvaView extends View {
    private Bitmap larvaBitmap;
    private int larvaX;
    private int larvaY;
    private int direction = 1;
    private int frameRate;
    private int maxFrames;
    private int currentFrame;
    private boolean hasMaxFrames;

    public LarvaView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void startMovement(int frameRate, int maxFrames) {
        this.frameRate = frameRate;
        this.maxFrames = maxFrames;
        this.hasMaxFrames = maxFrames > 0;
        this.currentFrame = 0;
        moveLarva();
    }

    private void moveLarva() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (!hasMaxFrames || currentFrame < maxFrames) {
                    larvaX += direction;
                    if (larvaX >= getWidth() - larvaBitmap.getWidth() || larvaX <= 0) {
                        direction *= -1;
                    }
                    currentFrame++;
                    invalidate();
                    handler.postDelayed(this, 1000 / frameRate);
                }
            }
        };
        handler.post(runnable);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (larvaBitmap == null) {
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.larva);
            int width = getWidth() / 2;  // Scale to 1/2th of the screen width
            int height = (int) (originalBitmap.getHeight() * ((float) width / originalBitmap.getWidth()));
            larvaBitmap = Bitmap.createScaledBitmap(originalBitmap, width, height, true);
            larvaY = getHeight() / 2 - larvaBitmap.getHeight() - 40; // Position above the frame count text box
        }
        canvas.drawBitmap(larvaBitmap, larvaX, larvaY, null);
    }
}







