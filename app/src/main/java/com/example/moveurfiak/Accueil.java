package com.example.moveurfiak;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import Meteo.MeteoActivity;
import clicker.MeteoClickerActivity;
import jeuCalcul.CalculActivity;
import memory.Memory;
import reveil.AlarmActivity;

public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
    }

    public void memory(View view){
        startActivity(new Intent(getApplicationContext(), Memory.class));
        finish();
    }
    public void calcul(View view){
        startActivity(new Intent(getApplicationContext(), CalculActivity.class));
        finish();
    }
    public void meteo(View view){
        startActivity(new Intent(getApplicationContext(), MeteoActivity.class));
        finish();
    }

    public void clicker(View view){
        startActivity(new Intent(getApplicationContext(), MeteoClickerActivity.class));
        finish();
    }

    public void reveil(View view){
        startActivity(new Intent(getApplicationContext(), AlarmActivity.class));
        finish();
    }
}
