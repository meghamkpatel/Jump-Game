package com.example.jumpgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView {
    private GameLoopThread gameLoopThread;
    private SurfaceHolder holder;
    public static int globalxSpeed =8;
    Bitmap playerbmp;
    Bitmap coinbmp;
    Bitmap backgroundbmp;
    int xx = 0;

    private List<Coin> coin = new ArrayList<Coin>();
    private List<Flappy> flappy = new ArrayList<Flappy>();
    private List<Background> bg = new ArrayList<Background>();

    public static int Coinscollected = 0;
    private int timerCoins = 0;


    public GameView(Context context){
        super(context);

        gameLoopThread = new GameLoopThread(this);

        holder = getHolder();
        holder.addCallback(new Callback(){
            public void surfaceDestroyed(SurfaceHolder arg0){
                Coinscollected = 0;
                gameLoopThread.running = false;
            }
            public void surfaceCreated(SurfaceHolder arg0){
                //TODO Auto-generated method stub
                gameLoopThread.setRunning();
                gameLoopThread.start();

            }
            public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3){
                //TODO Auto-generated method stub
            }

        });
        playerbmp = BitmapFactory.decodeResource(getResources(), R.drawable.flappy);
        coinbmp = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        backgroundbmp = BitmapFactory.decodeResource(getResources(), R.drawable.jumpbackground);

        flappy.add(new Flappy(this, playerbmp, 70, 50));
        coin.add(new Coin(this, coinbmp, 190, 850));
        coin.add(new Coin(this,coinbmp,0,950));
        coin.add(new Coin(this,coinbmp,400,1150));
        coin.add(new Coin(this,coinbmp,650,1300));
        coin.add(new Coin(this,coinbmp,970,850));
//        bg.add(new Background(this,backgroundbmp,0,0));




    }
    @Override
    public boolean onTouchEvent(MotionEvent e){
        for(Flappy f: flappy){
            f.ontouch();
        }
        return false;
    }
    public void update(){
        updatetimers();
        deleteground();

    }
    public void updatetimers() {

        timerCoins++;


        if (timerCoins >= 100) {

            Random randomCoin = new Random();
            int random;
            random = randomCoin.nextInt(3);

            switch (random) {

                case 1:
                    int currentcoin = 1;
                    int xx = 1;
                    while (currentcoin <= 5) {

                        coin.add(new Coin(this, coinbmp, this.getWidth() + (32 * xx), 800));

                        currentcoin++;
                        xx++;
                    }
                    break;

                case 2:
                    coin.add(new Coin(this, coinbmp, 132, 932));
                    coin.add(new Coin(this, coinbmp, 564, 848));
                    coin.add(new Coin(this, coinbmp, 396, 732));
                    coin.add(new Coin(this, coinbmp, 828, 848));
                    coin.add(new Coin(this, coinbmp, 660, 932));

            }
            timerCoins = 0;
        }
    }
        public void addground(){

        while(xx < this.getWidth()+ Background.width)
        {
            bg.add(new Background(this,backgroundbmp,xx,0));

            xx += backgroundbmp.getWidth();
        }

    }
    public void deleteground(){

        for (int i = bg.size()-1;i >= 0; i--)
        {
            int groundx = bg.get(i).returnX();

            if (groundx<=-Background.width){
                bg.remove(i);
                bg.add(new Background(this,backgroundbmp,groundx+this.getWidth()+Background.width,0));
            }
        }

    }



    @Override
    protected void onDraw(Canvas canvas){
        update();
        canvas.drawColor(Color.CYAN);
        addground();

        Paint textpaint = new Paint();

        textpaint.setTextSize(60);
        canvas.drawText("Coins"+String.valueOf(Coinscollected), 0, 50, textpaint);

//        for(Background bground: bg){
//            bground.onDraw(canvas);
//        }
        for(Flappy f: flappy)
        {
            f.onDraw(canvas);
        }
        for(int i = 0; i < coin.size(); i++)
        {
            coin.get(i).onDraw(canvas);
            Rect player = flappy.get(0).getBounds();
            Rect coinr = coin.get(i).getBounds();

            if (coin.get(i).returnX() < 0-32){
                coin.remove(i);
                break;
            }

            if (coin.get(i).checkCollision(player, coinr)){
                coin.remove(i);
                Coinscollected+=1;
                break;
            }





        }

    }


}

