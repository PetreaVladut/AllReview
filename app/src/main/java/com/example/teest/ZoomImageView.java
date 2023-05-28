package com.example.teest;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;

public class ZoomImageView extends AppCompatImageView {

    private Matrix matrix;
    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1f;
    private PointF lastTouchPoint;

    public ZoomImageView(Context context) {
        super(context);
        initialize(context);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }

    private void initialize(Context context) {
        matrix = new Matrix();
        scaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
        lastTouchPoint = new PointF();
        setOnTouchListener(new TouchListener());
    }

    @Override
    public void setImageMatrix(Matrix matrix) {
        super.setImageMatrix(matrix);
        this.matrix.set(matrix);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(1f, Math.min(scaleFactor, 10.0f)); // Limit scale factor
            matrix.setScale(scaleFactor, scaleFactor);
            setImageMatrix(matrix);
            return true;
        }
    }

    private class TouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            scaleGestureDetector.onTouchEvent(event);

            PointF currentTouchPoint = new PointF(event.getX(), event.getY());
            int action = event.getActionMasked();

            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    lastTouchPoint.set(currentTouchPoint);
                    break;
                case MotionEvent.ACTION_MOVE:
                    float deltaX = currentTouchPoint.x - lastTouchPoint.x;
                    float deltaY = currentTouchPoint.y - lastTouchPoint.y;
                    matrix.postTranslate(deltaX, deltaY);
                    setImageMatrix(matrix);
                    lastTouchPoint.set(currentTouchPoint);
                    break;
            }

            return true;
        }
    }
}
