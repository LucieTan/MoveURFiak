package com.example.moveurfiak;

public class User {
    public String email,pseudo,timerClicker,timerMemor,alarm;
    public int scoreCalcul;
    public User(){

    }
    public User (String email, String pseudo, String timerClicker, int scoreCalcul, String timerMemor, String alarm) {
        this.alarm = alarm;
        this.email = email;
        this.pseudo = pseudo;
        this.timerClicker = timerClicker;
        this.scoreCalcul = scoreCalcul;
        this.timerMemor= timerMemor;
    }

    public User(String email, String pseudo) {
        this.alarm = "0";
        this.email = email;
        this.pseudo = pseudo;
        this.timerClicker = "0";
        this.scoreCalcul = 0;
        this.timerMemor= "0";
    }
}
