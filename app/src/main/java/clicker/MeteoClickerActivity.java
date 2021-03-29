package clicker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moveurfiak.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

import Meteo.MeteoActivity;
import jeuCalcul.CalculActivity;
import reveil.AlarmService;

public class MeteoClickerActivity extends AppCompatActivity {
    TextView textscore;
    ImageView tornado, hotsun, smilingsun, thunderstorm, smilingmoon, twoclouds;
    ImageView[] IMGS = {tornado, hotsun, smilingsun, thunderstorm, smilingmoon, twoclouds};
    int score = 0;
    Chronometer timer;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteoclicker);
        ref = FirebaseDatabase.getInstance().getReference("Utilisateur");

        timer = (Chronometer) findViewById(R.id.chrono_clicker);
        timer.start();

        textscore = findViewById(R.id.textscore);
        tornado = findViewById(R.id.tornado);
        hotsun = findViewById(R.id.hotsun);
        smilingsun = findViewById(R.id.smilingsun);
        thunderstorm = findViewById(R.id.thunderstorm);
        smilingmoon = findViewById(R.id.smilingmoon);
        twoclouds = findViewById(R.id.twoclouds);

        IMGS[0] = tornado;
        IMGS[1] = hotsun;
        IMGS[2] = smilingsun;
        IMGS[3] = thunderstorm;
        IMGS[4] = smilingmoon;
        IMGS[5] = twoclouds;

        for(int i = 0; i <= 5; i++) {
            IMGS[i].setVisibility(View.INVISIBLE);
        }
        int i = random();
        IMGS[i].setVisibility(View.VISIBLE);
        IMGS[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMGS[i].setVisibility(View.INVISIBLE);
                score++;
                textscore.setText("Score : 0" + score);
                newImage(IMGS);
            }
        });
    }

    private int random() {
        Random r = new Random();
        int i = r.nextInt(5 - 0);
        return i;
    }

    private void newImage(ImageView[] t){
        if(score < 10){
            int j = random();
            t[j].setVisibility(View.VISIBLE);
            t[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    t[j].setVisibility(View.INVISIBLE);
                    score++;
                    textscore.setText("Score : " + score);
                    newImage(IMGS);
                }
            });
        } else {
            timer.stop();
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MeteoClickerActivity.this);
            alertDialogBuilder
                    .setMessage("Vous avez pris : " + timer.getText()  + " sec")
                    .setCancelable(false)
                    .setPositiveButton("QUITTER", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {finish();}
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            stopService(new Intent(this, AlarmService.class));
        }
        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("timerClicker").setValue(timer.getText());
    }
}