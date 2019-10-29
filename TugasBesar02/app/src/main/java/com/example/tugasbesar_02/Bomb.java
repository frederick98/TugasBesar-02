package com.example.tugasbesar_02;

import android.app.Activity;
import android.widget.ImageView;

public class Bomb extends GameObject{
    protected ImageView ivBomb;
    protected int x;
    protected int y;
    protected Activity activity;

    public Bomb(Activity activity){
        this.activity = activity;
        super.getDisplayLayout(activity);
        this.ivBomb = this.activity.findViewById(R.id.iv_bomb);
        
        // command dibawah ini buat set supaya si objek ga spawn dulu di awal activity launch
        this.ivBomb.setX(-80);
        this.ivBomb.setY(-80);
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
        this.x -= 5;
        if(this.x < 0){
            this.x = super.lebarLayar + 12;
            this.y = (int) Math.floor(Math.random() * (super.tinggiLayar - this.ivBomb.getHeight()));
        }
        this.ivBomb.setX(this.x);
        this.ivBomb.setY(this.y);
    }
}
