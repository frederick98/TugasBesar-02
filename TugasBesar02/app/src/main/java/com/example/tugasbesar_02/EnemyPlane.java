package com.example.tugasbesar_02;

import android.app.Activity;
import android.widget.ImageView;

public class EnemyPlane extends GameObject{
    protected ImageView ivPlane2;
    protected int x;
    protected int y;
    protected Activity activity;

    public EnemyPlane(Activity activity) {
        this.activity = activity;
        super.getDisplayLayout(activity);
        this.ivPlane2 = this.activity.findViewById(R.id.iv_plane_enemy);

        // command dibawah ini buat set supaya si objek ga spawn dulu di awal activity launch
        this.ivPlane2.setX(-80);
        this.ivPlane2.setY(-80);
    }

    protected void setX(int x){
        this.x = x;
    }

    protected int getX(){
        return this.x;
    }

    protected void setY(int y){
        this.y = y;
    }

    protected int getY(){
        return this.y;
    }

    protected void spawn(){
        this.x -= 10;
        if(this.x < 0){
            this.x = super.lebarLayar + 20;
            this.y = (int) Math.floor(Math.random() * (super.tinggiLayar - this.ivPlane2.getHeight()));
        }
        this.ivPlane2.setX(this.x);
        this.ivPlane2.setY(this.y);
    }
}