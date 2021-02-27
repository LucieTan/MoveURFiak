package com.example.moveurfiak;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MemoryActivity extends AppCompatActivity {
    TextView timer;

    ImageView iv_1,iv_2,iv_3,iv_4,iv_5,iv_6;
    Integer[] carteArray = {1,2,3,4,5,6};

    int img1,img10,img2,img20,img3,img30;

    int premiereCarte, secondeCarte;
    int clickedFirst, getClickedSecond;
    int carteNumber = 1, turn = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        timer = (TextView) findViewById(R.id.timer);

        iv_1 = (ImageView) findViewById(R.id.iv_1);
        iv_2 = (ImageView) findViewById(R.id.iv_2);
        iv_3 = (ImageView) findViewById(R.id.iv_3);
        iv_4 = (ImageView) findViewById(R.id.iv_4);
        iv_5 = (ImageView) findViewById(R.id.iv_5);
        iv_6 = (ImageView) findViewById(R.id.iv_6);


    }
}