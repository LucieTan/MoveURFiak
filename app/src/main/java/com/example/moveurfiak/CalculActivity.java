package com.example.moveurfiak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.Random;

public class CalculActivity extends AppCompatActivity {

    private class Calcul {
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

        public Calcul(int chiffreMax) {
            this.chiffreMax = chiffreMax;
            Random randomNumberMaker = new Random();

            this.premierNB = randomNumberMaker.nextInt(chiffreMax);
            this.secondNB = randomNumberMaker.nextInt(chiffreMax);
            this.reponse = this.premierNB + this.secondNB;

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
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);


    }
}