package com.example.jumpgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {
    public static int width;
    public static int height;

    private GameView gameview;
    private Bitmap bmp;
    private  int x;
    private int y;

    public Background(GameView gameview, Bitmap bmp, int x, int y){
        this.gameview = gameview;
        this.bmp = bmp;
        this.x = x;
        this.y = y;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();

    }

    public void update(){
        x-=gameview.globalxSpeed;
    }
    public int returnX(){
        return x;
    }

    public void onDraw(Canvas canvas)
    {
        update();
        canvas.drawBitmap(bmp, x, gameview.getHeight()-64, null);
    }

}
