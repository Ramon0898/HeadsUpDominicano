package com.example.ramon.headsupdominicano;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
/* AQUI TRATABA DE HACER EL TEMPORIZADOR DE LOS 5 SEG DESPUES DE SELECCIONAR LA CATEGORIA, AUN NO ME FUNCIONA
public class Questions extends AppCompatActivity {

    TextView Counter;
    Intent in;

    private CountDownTimer countDownTimer;
    private long seconds = 5000;
    private boolean TimeRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        Counter = findViewById(R.id.Counter);

        starStop();
        updateTimer();
    }

    public void starStop(){
        if(TimeRunning){
            stopTimer();
        }else {
            starTimer();
        }
    }

    public void starTimer(){
        countDownTimer = new CountDownTimer(seconds, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                seconds = 5;
                updateTimer();
                if (seconds == 5){
                    in= new Intent(Questions.this, MainActivity.class);
                    startActivity(in);
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();
        TimeRunning = true;
    }

    public void stopTimer(){
        countDownTimer.cancel();
        TimeRunning = false;
    }

    public void updateTimer(){
        int minutes = (int)seconds / 5000;
        int second = (int)seconds % 5000 / 100;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (second <5 )timeLeftText += "0";
        timeLeftText += second;


    }
}
*/