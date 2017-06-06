

package com.example.gustavogusmao.pong;

/**
 * Created by gustavo.gusmao on 30/05/2017.
 */

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Player2
{
    private static Player2 instance;
    private Paint blue;
    private float x, y, width, height, ScreenWidth, speedX;
    private boolean isMoving, isMovingLeft;

    private Player2()
    {
        blue = new Paint();
        blue.setARGB(200, 0, 0, 255);

        width = GameView.screenW * 0.2f;
        height = GameView.screenH * 0.3f; //0.3
        x = (GameView.screenW / 2 ) - (width / 2);
        y = (GameView.screenH / 30);
        ScreenWidth = GameView.screenW/2;

        speedX = 15f;
        isMoving = isMovingLeft = false;
    }

    public static Player2 getInstance()
    {
        if(instance == null) instance = new Player2();

        return instance;
    }

    public float GetX() { return x; }
    public float GetY() { return y; }
    public float GetW() { return width; }
    public float GetH() { return height; }

    public void draw(Canvas canvas)
    {
        canvas.drawRect(x, y, x + width, y * 2, blue);
    }

    public void update()
    {
        if(isMoving)
        {
            if(isMovingLeft) x  += speedX;
            else x -= speedX;
        }

        CollisionWithScreen();
    }

    public void preUpdate(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN ||
                event.getAction() == MotionEvent.ACTION_MOVE)
        {
            isMoving = true;
            isMovingLeft = x + (width / 2) > event.getRawX(); // x = true || false
        }
        else if(event.getAction() == MotionEvent.ACTION_UP) isMoving = false;
    }

    private void CollisionWithScreen()
    {
        if(x < 0) x += speedX;
        else if(x + width > GameView.screenW) x -= speedX;
    }
}
