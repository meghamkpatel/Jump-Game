package com.example.jumpgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


public class Coin {
    private int x;
    private int y;

    private Bitmap bmp;
    private GameView gameview;
    private int xSpeed = -GameView.globalxSpeed;
    private int width;
    private int height;
    private int mCurrentFrame = 0;
    private Rect flappy;
    private Rect coin;


    public Coin(GameView gameview, Bitmap bmp, int x, int y){
        this.x = x;
        this.y = y;
        this.gameview = gameview;
        this.bmp = bmp;
        this.width = bmp.getWidth();
        this.height = bmp.getHeight();
    }
    public void update(){
        x += xSpeed;

        if(x < 0-width){
            x = gameview.getWidth()+width;
        }
    }
    public int returnX(){
        return x;
    }

    public boolean checkCollision(Rect player, Rect coin){

        this.flappy = player;
        this.coin = coin;

        return Rect.intersects(player, coin);
    }
    public Rect getBounds()
    {
        return new Rect(this.x,this.y,this.x+width,this.y+height);
    }


    public void onDraw(Canvas canvas){
        update();
        int srcX = mCurrentFrame*width;
        Rect src =new Rect(mCurrentFrame*width, 0, srcX +width,32);
        Rect dst =new Rect(x,y, x+width, y+32);
        canvas.drawBitmap(bmp, x, y, null);
    }
}
