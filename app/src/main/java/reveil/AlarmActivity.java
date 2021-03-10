package reveil;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;

import com.example.moveurfiak.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import Meteo.MeteoActivity;
import application.ProfilActivity;
import clicker.MeteoClickerActivity;
import jeuCalcul.CalculActivity;
import memory.Memory;


public class AlarmActivity extends Activity {
    private TimePicker timePicker;
    private TextClock currentTime;
    private TextView alarmTime;
    private Class<?>[] listGames = {MeteoClickerActivity.class, Memory.class, CalculActivity.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveil);

        //nav
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setSelectedItemId(R.id.reveil);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.reveil: return true;
                    case R.id.profil:
                        startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.meteo:
                        startActivity(new Intent(getApplicationContext(), MeteoActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        currentTime = findViewById(R.id.currentTime);
        alarmTime = findViewById(R.id.alarmTime);

        //initialisation du timepicker : format 24h (FR), 00:00
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        setDefaultTime(0, 0);

        //initialisation de la liste des jeux
        listGames[0] = MeteoClickerActivity.class;
        listGames[1] = Memory.class;
        listGames[2] = CalculActivity.class;

        //permet a l'app de se lancer malgré l'ecran verrouillé
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true);
            setTurnScreenOn(true);
        }

        //met à jour le texte qui indique l'heure a laquelle le reveil sonnera
        updateAlarmTime();

        //permet le lancement du réveil et du jeu à l'heure initialisée
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(currentTime.getText().toString().equals(AlarmTime())){
                    launchGame();
                    try {
                        //pause de 60s pour que launchGame() ne se lance qu'une fois
                        TimeUnit.SECONDS.sleep(60);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 1000);

    }

    private void updateAlarmTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                alarmTime.setText(AlarmTime());
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }

    private void setDefaultTime(int defaultHour, int defaultMinute) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            timePicker.setHour(defaultHour);
            timePicker.setMinute(defaultMinute);
        }
    }

    public void launchGame() {
        //lance le service d'alarme sonore
        startService(new Intent(this, AlarmService.class));
        //lance l'activité de jeu
        Intent intent = new Intent(this, listGames[random()]);
        startActivity(intent);
    }

    //retourne un entier aléatoire parmi 0, 1 et 2
    private int random() {
        Random r = new Random();
        int i = r.nextInt(2 - 0) + 0;
        return i;
    }

    //retourne une chaine de caractere qui correspond à l'heure du timepicker
    private String AlarmTime() {
        Integer alarmHour = timePicker.getCurrentHour();
        Integer alarmMinute = timePicker.getCurrentMinute();
        String stringAlarmTime = alarmHour.toString().concat(":").concat(alarmMinute.toString());

        //ajouts de "0" nécessaires à la comparaison de chaînes de caractères
        if(alarmMinute < 10) {
            stringAlarmTime = alarmHour.toString().concat(":0").concat(alarmMinute.toString());
        }
        if(alarmHour < 10) {
            stringAlarmTime = ("0").concat(alarmHour.toString()).concat(":").concat(alarmMinute.toString());
        }
        if(alarmHour < 10 && alarmMinute < 10) {
            stringAlarmTime = ("0").concat(alarmHour.toString()).concat(":0").concat(alarmMinute.toString());
        }

        return stringAlarmTime;


    }

}