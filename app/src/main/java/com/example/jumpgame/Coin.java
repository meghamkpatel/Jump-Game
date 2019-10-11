package com.example.jumpgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import androidx.constraintlayout.solver.widgets.Rectangle;

public class Coin {
    private int x;
    private int y;
    private Bitmap bmp;
    private GameView gameview;
    private int xSpeed = -GameView.globalxSpeed;
    private int mColumnWidth = 6;
    private int width;
    private int mCurrentFrame = 0;

    public Coin(GameView gameview, Bitmap bmp, int x, int y){
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
        update();
        int srcX = mCurrentFrame*width;
        Rect src =new Rect(mCurrentFrame*width, 0, srcX +width,58);
        Rect dst =new Rect(x,y, x+width, y+58);
        canvas.drawBitmap(bmp, src, dst, null);
    }
}
