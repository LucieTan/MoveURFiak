package com.example.moveurfiak;

import java.util.List;

public class Jeu {

    private List<CalculActivity> calculs;

    public List<CalculActivity> getCalculs() {
        return calculs;
    }

    public void setCalculs(List<CalculActivity> calculs) {
        this.calculs = calculs;
    }

    public int getNbCorrect() {
        return nbCorrect;
    }

    public void setNbCorrect(int nbCorrect) {
        this.nbCorrect = nbCorrect;
    }

    public int getNbIncorrect() {
        return nbIncorrect;
    }

    public void setNbIncorrect(int nbIncorrect) {
        this.nbIncorrect = nbIncorrect;
    }

    public int getTotalDeCalculs() {
        return totalDeCalculs;
    }

    public void setTotalDeCalculs(int totalDeCalculs) {
        this.totalDeCalculs = totalDeCalculs;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public CalculActivity getCurrentCalcul() {
        return currentCalcul;
    }

    public void setCurrentCalcul(CalculActivity currentCalcul) {
        this.currentCalcul = currentCalcul;
    }

    private int nbCorrect;
    private int nbIncorrect;
    private int totalDeCalculs;
    private int score;
    private CalculActivity currentCalcul;

    public Jeu() {
        nbCorrect = 0;
        nbIncorrect = 0;
        totalDeCalculs = 0;
        score = 0;
        currentCalcul = new CalculActivity(10);
    }

    public void makeNewCalcul() {
        currentCalcul = new CalculActivity(totalDeCalculs * 2 + 5);
        totalDeCalculs++;
        calculs.add(currentCalcul);
    }

    public boolean verifReponse (int reponseSoumise) {
         boolean estCorrect;
         if (currentCalcul.getReponse() == reponseSoumise ) {
             nbCorrect++;
             estCorrect = true;
         } else {
             nbIncorrect++;
             estCorrect=false;
         }
         score = nbCorrect * 100 - nbIncorrect * 300;
         return estCorrect;
    }

}
