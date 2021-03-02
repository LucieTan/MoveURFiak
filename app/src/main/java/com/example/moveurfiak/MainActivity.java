package com.example.moveurfiak;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moveurfiak.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TimePicker alarmtime;
    TextClock current_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        alarmtime = findViewById(R.id.timepicker);
        alarmtime.setIs24HourView(true);
        current_time = findViewById(R.id.textclock);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(current_time.getText().toString().equals(AlarmTime())){
                    r.play();
                } else
                    r.stop();
            }
        },0, 1000);
    }

    private String AlarmTime() {
        Integer alarmHours = alarmtime.getCurrentHour();
        Integer alarmMinutes = alarmtime.getCurrentMinute();

        String stringAlarmTime = "";
        String stringAlarmMinutes = "";

        if(alarmMinutes < 10){
            stringAlarmMinutes = "0".concat(Integer.toString(alarmMinutes));
        }else stringAlarmMinutes = Integer.toString(alarmMinutes);

        stringAlarmTime = Integer.toString(alarmHours).concat(":").concat(stringAlarmMinutes);

        return stringAlarmTime;
    }
}