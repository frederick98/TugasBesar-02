package com.example.tugasbesar_02;

import android.app.Activity;
import android.widget.ImageView;

public class Plane extends GameObject {
    protected ImageView ivPlane;
    protected int x;
    protected int y;
    protected Activity activity;

    public Plane(Activity activity) {
        this.activity = activity;
        super.getDisplayLayout(activity);
        this.ivPlane = this.activity.findViewById(R.id.iv_plane);
        this.y=(int)ivPlane.getY();
    }



    protected void setY(int y){
        this.ivPlane.setY(this.y = y);
    }

    protected int getY(){
        return this.y;
    }

    protected int getHeight(){
        return this.ivPlane.getHeight();
    }

    protected int getWidth(){
        return this.ivPlane.getWidth();
    }

    protected void spawn(){



    }
}
