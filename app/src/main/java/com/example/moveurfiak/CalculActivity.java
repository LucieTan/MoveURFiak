package com.example.moveurfiak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class CalculActivity extends AppCompatActivity {

    public CalculActivity() {}

        private int premierNB;
        private int secondNB;
        private int reponse;
        // tableau de 4 réponses possibles
        private int [] reponseArray;
        // la position de réponse du tableau précdédent aka 0 1 2 ou 3
        private int reponsePosition;

        private int chiffreMax;

        private String questionPhrase;

        // Génération d'un calcul random

        public CalculActivity(int chiffreMax) {
            this.chiffreMax = chiffreMax;
            Random randomNumberMaker = new Random();

            this.premierNB = randomNumberMaker.nextInt(chiffreMax);
            this.secondNB = randomNumberMaker.nextInt(chiffreMax);
            this.reponse = this.premierNB + this.secondNB;
            this.questionPhrase = premierNB + " + " + secondNB + " = ";

            this.reponsePosition = randomNumberMaker.nextInt(4);
            this.reponseArray = new int[] {0,1,2,3};

            this.reponseArray[0] = reponse +1;
            this.reponseArray[1] = reponse +2;
            this.reponseArray[2] = reponse -1;
            this.reponseArray[3] = reponse -2;

            this.reponseArray = shuffleArray(this.reponseArray);
            reponseArray[reponsePosition] = reponse;

        }
        private int [] shuffleArray(int[] array) {
            int index, temp;
            Random randomNumberGenerator = new Random();

            for (int i = array.length -1; i > 0; i--) {
                index = randomNumberGenerator.nextInt(i + 1);
                temp = array[index];
                array[index] = array[i];
                array[i] = temp;
            }
            return array;
        }

        public int getPremierNB() {
            return premierNB;
        }

        public void setPremierNB(int premierNB) {
            this.premierNB = premierNB;
        }

        public int getSecondNB() {
            return secondNB;
        }

        public void setSecondNB(int secondNB) {
            this.secondNB = secondNB;
        }

        public int getReponse() {
            return reponse;
        }

        public void setReponse(int reponse) {
            this.reponse = reponse;
        }

        public int[] getReponseArray() {
            return reponseArray;
        }

        public void setReponseArray(int[] reponseArray) {
            this.reponseArray = reponseArray;
        }

        public int getReponsePosition() {
            return reponsePosition;
        }

        public void setReponsePosition(int reponsePosition) {
            this.reponsePosition = reponsePosition;
        }

        public int getChiffreMax() {
            return chiffreMax;
        }

        public void setChiffreMax(int chiffreMax) {
            this.chiffreMax = chiffreMax;
        }

        public String getQuestionPhrase() {
            return questionPhrase;
        }

        public void setQuestionPhrase(String questionPhrase) {
            this.questionPhrase = questionPhrase;
        }


    Button btn_start, btn_rep0, btn_rep1, btn_rep2, btn_rep3;
    TextView txt_timer, txt_calcul, txt_score, txt_msg_bas;
    ProgressBar bar_timer;

    Jeu g = new Jeu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);

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
        bar_timer.setProgress(0);

        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;

                start_button.setVisibility(View.INVISIBLE);
                nextTurn();
            }
        };
        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;

                int reponseSelect = Integer.parseInt(buttonClicked.getText().toString());
            }
        };

        btn_start.setOnClickListener(startButtonClickListener);

        btn_rep0.setOnClickListener(answerButtonClickListener);
        btn_rep1.setOnClickListener(answerButtonClickListener);
        btn_rep2.setOnClickListener(answerButtonClickListener);
        btn_rep3.setOnClickListener(answerButtonClickListener);


    }
    private void nextTurn(){

        g.makeNewCalcul();
        int [] reponse = g.getCurrentCalcul().getReponseArray();

        btn_rep0.setText(Integer.toString(reponse[0]));
        btn_rep1.setText(Integer.toString(reponse[1]));
        btn_rep2.setText(Integer.toString(reponse[2]));
        btn_rep3.setText(Integer.toString(reponse[3]));

        btn_rep0.setEnabled(true);
        btn_rep1.setEnabled(true);
        btn_rep2.setEnabled(true);
        btn_rep3.setEnabled(true);


        txt_calcul.setText(g.getCurrentCalcul().getQuestionPhrase());

    }

}