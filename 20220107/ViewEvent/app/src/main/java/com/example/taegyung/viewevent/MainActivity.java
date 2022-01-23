package com.example.taegyung.viewevent;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyView myView = new MyView(this);

        setContentView(R.layout.activity_main);
    }

    class MyView extends View {
        MyView(Context content) {
            super(content);
            setBackgroundColor(Color.GREEN);
        }
    }
}
