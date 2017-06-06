package com.example.gustavogusmao.pong;

/**
 * Created by gustavo.gusmao on 30/05/2017.
 */

import android.graphics.Canvas;
import android.graphics.Paint;

import static com.example.gustavogusmao.pong.R.attr.height;

public class Ball
{
    private Paint red;
    private float x, y, radius, speedX, speedY;
    private boolean couldCollide;
    private Player player;
    private Player2 player2;

    public Ball()
    {
        red = new Paint();
        red.setARGB(255, 255, 0, 0);

        x = GameView.screenW / 2;
        y = GameView.screenH / 2;
        radius = GameView.screenW * 0.04f;
        speedX = 12;
        speedY = -12f;
        couldCollide = true;

        player = Player.getInstance();
        player2 = player2.getInstance();
    }

    public float GetBallY() { return y; }
    public float GetBallX() { return x; }

    public void draw(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, red);
    }

    public void update()
    {
        x += speedX;
        y += speedY;

        CollisionWithScreen();
        CollisionWithPlayer();
    }

    private void ChangeBallState(boolean width)
    {
        if(couldCollide)
        {
            if(width) speedX *= -1;
            else speedY *= -1;

            couldCollide = false;
        }
    }

    private void CollisionWithScreen()
    {
        if(x + radius > GameView.screenW || x - radius < 0) ChangeBallState(true);
        //else if(y - radius < 0) ChangeBallState(false);
        else if(x + radius > GameView.screenW) GameView.isDead = true;
        else couldCollide = true;
    }

    private void CollisionWithPlayer()
    {
        if (x - radius < player.GetX() + player.GetX() && x + radius > player.GetX() &&
                y - radius < player.GetY() + player.GetY() && y + radius > player.GetY())
        {
            speedY *= -1;
            couldCollide = false;
            GameView.score +=1;
        }
        if (x - radius < player2.GetX() + player2.GetX() && x + radius > player2.GetX() &&
                y - radius < player2.GetY() + player2.GetY() && y + radius > player2.GetY())
        {
            speedY *= -1;
            couldCollide = false;
            GameView.score +=1;
        }
    }
}
