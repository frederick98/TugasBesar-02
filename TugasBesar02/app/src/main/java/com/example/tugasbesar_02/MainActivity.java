package com.example.tugasbesar_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected Bitmap mBitmap;
    protected Canvas mCanvas;
    protected ImageView ivCanvas;
    protected Paint strokePaint;
    protected Button btn_start;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ivCanvas = findViewById(R.id.iv_canvas);
        this.btn_start=findViewById(R.id.btn_start);
        this.btn_start.setOnClickListener(this);
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
        this.strokePaint.setColor(Color.BLUE);

        //create rectangle
        Pesawat p = new Pesawat(100, 100, 100);
        p.drawTriangle(this.mCanvas, this.strokePaint);


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
       if(btn_start.getId()==view.getId()) {
           this.initiateCanvas();
       }
    }
}


