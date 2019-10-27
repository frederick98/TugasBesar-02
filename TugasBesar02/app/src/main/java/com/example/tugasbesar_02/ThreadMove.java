package com.example.tugasbesar_02;

import android.util.Log;

import java.util.Random;

public class ThreadMove implements Runnable {

    protected UIHandler uiHandler;
    protected Thread thread;
    protected Musuh musuh;
    protected boolean status=true;

    public ThreadMove(UIHandler uiHandler,Musuh bomusuhla)
    {
        this.uiHandler=uiHandler;
        this.thread= new Thread(this);
        this.musuh=musuh;
    }



    @Override
    public void run() {
        while(this.musuh.isInside()) {
            this.musuh.Update();
            this.uiHandler.setMusuhMove(this.musuh);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void start(){
        this.status=true;
        this.thread.start();
    }
}
