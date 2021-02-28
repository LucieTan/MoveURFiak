package com.example.moveurfiak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_rep0, btn_rep1, btn_rep2, btn_rep3;
    TextView txt_timer, txt_calcul, txt_score, txt_msg_bas;
    ProgressBar bar_timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn_start = findViewById(R.id.btn_start);
        btn_rep0 = findViewById(R.id.btn_rep0);
        btn_rep1 = findViewById(R.id.btn_rep1);
        btn_rep2 = findViewById(R.id.btn_rep2);
        btn_rep3 = findViewById(R.id.btn_rep3);

        txt_timer = findViewById(R.id.txt_timer);
        txt_calcul = findViewById(R.id.txt_calcul);
        txt_score = findViewById(R.id.txt_score);
        txt_msg_bas = findViewById(R.id.txt_msg_bas);

        txt_timer.setText("0sec");
        txt_calcul.setText("");
        txt_msg_bas.setText("Press Go");
        txt_score.setText("0 points");


    }
}