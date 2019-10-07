package com.example.jumpgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {
    private GameLoopThread gameLoopThread;
    private SurfaceHolder holder;

    Bitmap playerbmp;
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
                flappy.add(new Flappy(this, playerbmp, 50, 50));
            }
            public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){
                //TODO Auto-generated method stub
            }
        });
    }
    @Override
    protected void onDraw(Canvas canvas){

    }
}
