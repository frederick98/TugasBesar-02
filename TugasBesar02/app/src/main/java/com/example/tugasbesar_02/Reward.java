package com.example.tugasbesar_02;

import android.app.Activity;
import android.widget.ImageView;

public class Reward extends GameObject{
    protected ImageView ivReward;
    protected int x;
    protected int y;
    protected Activity activity;

    public Reward(Activity activity){
        this.activity = activity;
        super.getDisplayLayout(activity);
        this.ivReward = this.activity.findViewById(R.id.iv_reward);

        // command dibawah ini buat set supaya si objek ga spawn dulu di awal activity launch
        this.ivReward.setX(-80);
        this.ivReward.setY(-80);
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
            this.y = (int) Math.floor(Math.random() * (super.tinggiLayar - this.ivReward.getHeight()));
        }
        this.ivReward.setX(this.x);
        this.ivReward.setY(this.y);
    }
}
