package com.example.jumpgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import androidx.constraintlayout.solver.widgets.Rectangle;

public class Coin {
    private double x;
    private double y;
    private Bitmap bmp;
    private GameView gameview;
    private double xSpeed = 0;
    private int mColumnWidth = 4;
    private int width;
    private double mCurrentFrame = 0;

    public Coin(GameView gameview, Bitmap bmp, double x, double y){
        this.x = x;
        this.y = y;
        this.gameview = gameview;
        this.bmp = bmp;
        this.width = bmp.getWidth()/mColumnWidth;
    }
    public void update(){
        x += xSpeed;
        mCurrentFrame += 1%(mColumnWidth-1);
    }
    public void onDraw(Canvas canvas){
        
        double srcX = mCurrentFrame*width;
        Rect src =new Rect(mCurrentFrame*width, 0, srcX +width,16);
        Rect dst =new Rect((int)x,(int)y, (int)x+width, (int)y+16);
        canvas.drawBitmap(bmp, src, dst, null);
    }
}
