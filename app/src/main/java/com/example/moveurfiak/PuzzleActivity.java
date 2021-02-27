package com.example.moveurfiak;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class PuzzleActivity extends AppCompatActivity {
    private static final int COLUMNS = 3;
    private static final int DIMENSIONS = COLUMNS*COLUMNS;

    private String[] pieceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);


        init();

        scramble();
     //   display();
    }

  /*  private void display(){
        ArrayList<Button> boutons= new ArrayList<>();
        Button boutons;

        for(int i = 0; i < pieceList.length; i++){
            boutons = new Button(this);
        }
    }*/

    private void scramble() {
        int index;
        String tmp;
        Random random = new Random();
        for (int i = pieceList.length-1; i<0;i-- ){
            index = random.nextInt(i +1);
            tmp = pieceList[index];
            pieceList[index] = pieceList[i];
            pieceList[i] = tmp;
        }
    }

    private void init() {
        pieceList = new String[DIMENSIONS];
        for(int i = 0; i < DIMENSIONS; i++){
            pieceList[i] = String.valueOf(i);
        }
    }
}
