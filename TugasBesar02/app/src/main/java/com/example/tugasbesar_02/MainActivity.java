package com.example.tugasbesar_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private MotionEvent motionEvent;
    protected Bitmap mBitmap;
    protected Canvas mCanvas;
    protected ImageView ivCanvas;
    protected Paint strokePaint;
    private GestureDetector mDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ivCanvas = findViewById(R.id.iv_canvas);

        //create gesture detector + listener
        this.mDetector = new GestureDetector(this, new MyCustomGestureListener());
        this.ivCanvas.setOnTouchListener(this);

        //atribut initialization
        //this.btnDraw.setOnClickListener(this);
        this.mCanvas = new Canvas();
        initiateCanvas();
    }


    public void initiateCanvas() {
        // 1. Create Bitmap
        this.mBitmap = Bitmap.createBitmap(this.ivCanvas.getWidth(), this.ivCanvas.getHeight(), Bitmap.Config.ARGB_8888);

        // 2. Associate the bitmap to the ImageView.
        this.ivCanvas.setImageBitmap(mBitmap);

        // 3. Create a Canvas with the bitmap.
        this.mCanvas = new Canvas(mBitmap);
        this.resetCanvas();

        // new paint for stroke + style (Paint.Style.STROKE)
        this.strokePaint = new Paint();
        this.strokePaint.setStyle(Paint.Style.STROKE);
        this.strokePaint.setStrokeWidth(2);

        //create rectangle
        Rect rect = new Rect(10, 20, 100, 100);
        mCanvas.drawRect(rect, strokePaint);

        // create circle
        // cx and cy adalah posisi titik tengah dimulainya radius
        mCanvas.drawCircle(150, 250, 100, strokePaint);

        //resetCanvas
        this.ivCanvas.invalidate();
    }

    public void resetCanvas() {
        // 4. Draw canvas background
        // Fill the entire canvas with solid color.
        int mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);
        mCanvas.drawColor(mColorBackground);

        // 5. force draw
        this.ivCanvas.invalidate();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    protected class MyCustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        protected PointF start;

        public boolean onDown(MotionEvent e) {
            //new start point with position if null, else set start position
            if (start == null) {
                this.start = new PointF(e.getX(), e.getY());
            } else {
                this.start.set(e.getX(), e.getY());
            }
            return true;
        }

        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            //set path
            Path strokePath = new Path();
            //change start point for next path
            strokePath.moveTo(this.start.x, this.start.y);

            //draw path
            strokePath.lineTo(e2.getX(), e2.getY());
            strokePath.close();

            //redraw
            this.start.set(e2.getX(), e2.getY());

            mCanvas.drawPath(strokePath, strokePaint);
            ivCanvas.invalidate();

            return true;
        }

        public void onLongPress(MotionEvent e) {
            //toggle change stroke + show toast
            //syntax : Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
            //			toast.show();

            if (strokePaint.getStrokeWidth() == 2) {
                strokePaint.setStrokeWidth(20);
            } else {
                strokePaint.setStrokeWidth(2);
            }
            Toast toast = Toast.makeText(getApplicationContext(), "stroke width changed to " + strokePaint.getStrokeWidth() + "", Toast.LENGTH_SHORT);
            toast.show();

        }
    }
}