package com.example.gustavogusmao.pong; /**
 * Created by gustavo.gusmao on 30/05/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements Runnable
{
    public static int screenW, screenH, score, HighScore;
    public static boolean isDead, isPaused, isUpdating;
    public String Scorepoints, HighPoints;
    public String Restart = "Touch to restart";

    private Handler handler;
    private Context c;

    private Paint white;

    private Ball ball;
    private Player player;
    private Player2 player2;

    public GameView(Context context)
    {
        super(context);
        Start(context);
    }

    public GameView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        Start(context);
    }

    private void Start(Context context)
    {
        score = 0;
        c = context;

        setBackgroundColor(Color.BLACK);

        screenW = c.getResources().getDisplayMetrics().widthPixels;
        screenH = c.getResources().getDisplayMetrics().heightPixels;

        isDead = isPaused = false;
        isUpdating = true;

        white = new Paint();
        white.setARGB(255, 255, 255, 255);

        ball = new Ball();
        player = Player.getInstance();
        player2 = Player2.getInstance();

        handler = new Handler();
        handler.post(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(!isDead && !isPaused)
        {
            player.preUpdate(event);
            player2.preUpdate(event);
        }
        else if(isDead) RestartGame();
        else if(isPaused) isPaused = false;

        return true;
    }

    void OutOfScreen(){
        if(ball.GetBallY() > screenH || ball.GetBallY() < 0){
            isDead = true;
        }
        if(ball.GetBallX() > screenW || ball.GetBallX() < 0){
            isDead = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        Paint p = new Paint();
        p.setTextSize(50f);
        p.setColor(Color.WHITE);

        if(!isDead && !isPaused) {
            ball.draw(canvas);
            player.draw(canvas);
            player2.draw(canvas);
            canvas.drawText(Scorepoints, 100f, 100f, p);
            canvas.drawText(HighPoints, 100f, 200f, p);
        }
        else canvas.drawText(Restart, screenW/ 2 - (Restart.length()/2), screenH / 2, p);
    }

    private void Update()
    {
        if(!isDead && !isPaused)
        {
            ball.update();
            player.update();
            player2.update();
            OutOfScreen();
            Scorepoints = "Score: " + score;
            HighPoints = "High Score: " + HighScore;
        }
       /* if(score >= 10)
        {
            Intent i = new Intent(c, YouWin.class);
            c.startActivity(i);
        }*/
        else if(score >= 10 && isDead){
            Intent i = new Intent(c, YouWin.class);
            c.startActivity(i);
            GameOver();
        }
        else if(isDead && score < 10){
            GameOver();
        }
    }

    private void GameOver()
    {
        if(score > HighScore)
        {
            HighScore = score;
        }
    }

    private void RestartGame()
    {
        ball = new Ball();
        score = 0;
        isDead = false;
    }

    @Override
    public void run()
    {
        if(isUpdating)
        {
            //Add a Runnable in the main thread message queue to be executed after a delayed time (ms).
            handler.postDelayed(this, 30);

            Update();
            invalidate();
        }
    }
}
