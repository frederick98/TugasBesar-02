package com.example.tugasbesar_02;

import java.util.Random;

public class Musuh {
    protected int x;
    protected int y;
    protected int ranX;
    protected int ranY;
    protected int rad;

    public Musuh(int width,int height)
    {
        this.x=width/2;
        this.y=height/2;
        this.rad=50;
        this.Randpos();
    }



    public void Randpos()
    {
        Random r = new Random();
        this.ranX=r.nextInt(20)-10;
        this.ranY=r.nextInt(20)-10;

    }

    public void Update()
    {
        this.x+=ranX;
        this.y+=ranY;
    }

    public boolean isInside()
    {
        return ((x+rad < x*2 && x-rad > 0) && (y+rad < y*2 && y-rad > 0));
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getRadian()
    {
        return this.rad;
    }
}
