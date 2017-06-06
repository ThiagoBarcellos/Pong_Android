package com.example.gustavogusmao.pong;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Pong extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
    }



}
