package com.example.tugasbesar_02;

import android.app.Activity;
import android.widget.ImageView;

public class Fuel extends GameObject{
    protected ImageView ivFuel;
    protected int x;
    protected int y;
    protected Activity activity;

    public Fuel(Activity activity){
        this.activity = activity;
        super.getDisplayLayout(activity);
        this.ivFuel = this.activity.findViewById(R.id.iv_fuel);

        // command dibawah ini buat set supaya si objek ga spawn dulu di awal activity launch
        this.ivFuel.setX(-80);
        this.ivFuel.setY(-80);
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
        this.x -= 3;
        if(this.x < 0){
            this.x = super.lebarLayar + 30;
            this.y = (int) Math.floor(Math.random() * (super.tinggiLayar - this.ivFuel.getHeight()));
        }
        this.ivFuel.setX(this.x);
        this.ivFuel.setY(this.y);
    }
}
