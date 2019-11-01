package com.example.tugasbesar_02;

import android.app.Activity;
import android.widget.ImageView;

public class EnemyPlane extends GameObject{
    protected ImageView ivPlane2;
    protected int x = -80;
    protected int y = -80;
    protected Activity activity;

    public EnemyPlane(Activity activity) {
        this.activity = activity;
        super.getDisplayLayout(activity);
        ivPlane2 = this.activity.findViewById(R.id.iv_plane_enemy);

        // command dibawah ini buat set supaya si objek ga spawn dulu di awal activity launch
        ivPlane2.setX(x);
        ivPlane2.setY(y);
    }

    protected void setX(int x){
        ivPlane2.setX(x);
    }

    protected int getX(){
        return (int) ivPlane2.getX();
    }

    protected void setY(int y){
        ivPlane2.setY(y);
    }

    protected int getY(){
        return (int) ivPlane2.getX();
    }

    protected int getHeight(){
        return ivPlane2.getHeight();
    }

    protected int getWidth(){
        return ivPlane2.getWidth();
    }

    protected int getTop(){
        return this.ivPlane2.getTop();
    }

    protected int getLeft(){
        return this.ivPlane2.getLeft();
    }

    protected void spawn(){
        this.x -= 10;
        if(this.x < 0){
            this.x = super.lebarLayar + 20;
            this.y = (int) Math.floor(Math.random() * (super.tinggiLayar - ivPlane2.getHeight()));
        }
        ivPlane2.setX(this.x);
        ivPlane2.setY(this.y);
    }
}