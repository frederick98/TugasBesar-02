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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    protected Bitmap mBitmap;
    protected Canvas mCanvas;
    protected ImageView ivCanvas;
    protected Paint strokePaint;
    protected Button btn_start;
    protected FloatingActionButton fab_pause;
    protected FloatingActionButton fab_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ivCanvas = findViewById(R.id.iv_canvas);
        this.btn_start=findViewById(R.id.btn_start);
        this.fab_pause = findViewById(R.id.fab_pause);
        this.fab_setting = findViewById(R.id.fab_menu);

        this.btn_start.setOnClickListener(this);
        this.fab_pause.setOnClickListener(this);
        this.fab_setting.setOnClickListener(this);
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
       else if(view.getId()==this.fab_pause.getId()){
           //cek dulu iconnya , jika ic_play >> mulai game
           //jika ic_pause >> pause game
       }
       else if(view.getId()==this.fab_setting.getId()){
           //tampilkan setting
       }
    }
}


