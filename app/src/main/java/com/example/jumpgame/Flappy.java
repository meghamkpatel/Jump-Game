package com.example.jumpgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class Flappy {
    static int x;
    static int y;
    static int gravity = 1;
    static int vspeed = 1;
    static int flappyHeight;
    static int flappyWidth;
    static int jumppower = -30;

    private int width,height;


    Rect flap;
    Bitmap bmp;
    GameView gameview;
    private int i;
    private Coin coin;

    public Flappy(GameView gameview, Bitmap bmp, int x, int y){
        this.x = x;
        this.y = y;
        this.gameview = gameview;
        this.bmp = bmp;

        this.width = bmp.getWidth();
        this.height = bmp.getHeight();

        flappyHeight=bmp.getHeight();

    }
    public void update(){
        checkGround();
    }
    public void checkGround(){
        if(y < gameview.getHeight()-64-flappyHeight){
            vspeed+=gravity;
            if(y > gameview.getHeight()-64-flappyHeight-vspeed){
                vspeed = gameview.getHeight()-64-y-flappyHeight;
            }
        }
        else if(vspeed>0){
            vspeed = 0;
            y = gameview.getHeight()-64-flappyHeight;
        }
        y+=vspeed;
    }
    public void ontouch(){
        if(y>=gameview.getHeight()-64-flappyHeight){
            vspeed = jumppower;
        }
    }
    public Rect getBounds()
    {
        return new Rect(this.x,this.y,this.x+width,this.y+height);
    }

    public void onDraw(Canvas canvas){
        update();
        canvas.drawBitmap(bmp, x, y, null);
    }
}
