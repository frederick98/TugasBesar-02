package com.example.tugasbesar_02;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected int lebarLayar, tinggiLayar;

    public GameObject(){

    }

    protected void spawn(){

    }

    protected void getDisplayLayout(Activity activity){
        // buat dapetin ukuran layar trus set ke ukuranLayar
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);

        this.lebarLayar = displaySize.x;
        this.tinggiLayar = displaySize.y;
    }
}
