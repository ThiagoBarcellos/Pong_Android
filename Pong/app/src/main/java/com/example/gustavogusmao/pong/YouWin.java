package com.example.gustavogusmao.pong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

/**
 * Created by thiago.mattos on 13/06/2017.
 */

public class YouWin extends AppCompatActivity
{
    Button btnForca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.you_win);
        btnForca = (Button) findViewById(R.id.btnForca);
    }

    void StartForca()
    {
        Intent i = new Intent("ForcaGame");
        i.addCategory("Forca");
        i.putExtra("game", "Jogo4");
        startActivity(i);
    }
}
