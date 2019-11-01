package com.example.tugasbesar_02;

import android.app.Activity;
import android.widget.ImageView;

public class Bomb extends GameObject{
    protected ImageView ivBomb;
    protected int x = -80;
    protected int y = -80;
    protected Activity activity;

    public Bomb(Activity activity){
        this.activity = activity;
        super.getDisplayLayout(activity);
        this.ivBomb = this.activity.findViewById(R.id.iv_bomb);
        
        // command dibawah ini buat set supaya si objek ga spawn dulu di awal activity launch
        this.ivBomb.setX(x);
        this.ivBomb.setY(y);
    }

    protected void setX(int x){
        this.ivBomb.setX(x);
    }

    protected int getX(){
        return (int) this.ivBomb.getX();
    }

    protected void setY(int y){
        this.ivBomb.setY(y);
    }

    protected int getY(){
        return (int) this.ivBomb.getY();
    }

    protected int getHeight(){
        return ivBomb.getHeight();
    }

    protected int getWidth(){
        return ivBomb.getWidth();
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
