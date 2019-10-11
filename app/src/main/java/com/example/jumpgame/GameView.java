package com.example.jumpgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {
    private GameLoopThread gameLoopThread;
    private SurfaceHolder holder;
    public static int globalxSpeed =15;
    Bitmap playerbmp;
    Bitmap coinbmp;
    private List<Coin> coin = new ArrayList<Coin>();
    private List<Flappy> flappy = new ArrayList<Flappy>();

    public GameView(Context context){
        super(context);

        gameLoopThread = new GameLoopThread(this);

        holder = getHolder();
        holder.addCallback(new Callback(){
            public void surfaceDestroyed(SurfaceHolder arg0){
                //TODO Auto-generated method stub
            }
            public void surfaceCreated(SurfaceHolder arg0){
                //TODO Auto-generated method stub
                gameLoopThread.setRunning(true);
                gameLoopThread.start();

            }
            public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){
                //TODO Auto-generated method stub
            }

        });
        playerbmp = BitmapFactory.decodeResource(getResources(), R.drawable.flappy);
        flappy.add(new Flappy(this, playerbmp, 50, 50));
        coinbmp = BitmapFactory.decodeResource(getResources(), R.drawable.coinsprite);
        coin.add(new Coin(this, coinbmp, 50, 200));
    }
    @Override
    public boolean onTouchEvent(MotionEvent e){
        for(Flappy f: flappy){
            f.ontouch();
        }
        return false;
    }
    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(Color.CYAN);
        for(Flappy f: flappy){
            f.onDraw(canvas);
        }
        for(Coin c: coin){
            c.onDraw(canvas);
        }
    }
}
