package com.example.tugasbesar_02;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Pesawat {

    protected int x;
    protected int y;
    protected int width;


    public Pesawat(int x,int y,int width)
    {
        this.x=x;
        this.y=y;
        this.width=width;
    }


    public void drawTriangle(Canvas canvas, Paint paint)
    {
        int halfWidth = width / 2;
        Path path = new Path();
        path.moveTo(x, y - halfWidth);
        path.lineTo(x - halfWidth, y + halfWidth);
        path.lineTo(x + halfWidth, y + halfWidth);
        path.lineTo(x, y - halfWidth);
        path.close();

        canvas.drawPath(path,paint);

    }

}
