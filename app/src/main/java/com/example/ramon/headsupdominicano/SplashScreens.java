package com.example.ramon.headsupdominicano;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashScreens extends AppCompatActivity {

    ProgressBar pb;
    TextView tv;
    Handler h = new Handler();
    int counter = 0;
    boolean isActivo = false;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screens);

        Progreso();
    }


    //Metodo Para hacer progressBar
    public void Progreso(){
        pb = findViewById(R.id.pb);
        tv = findViewById(R.id.tv);
        if(!isActivo) {
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (counter <= 99) {
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(counter + " %");
                                pb.setProgress(counter);
                            }
                        });
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (counter == 99) {
                            i = new Intent(SplashScreens.this, MainActivity.class);
                            startActivity(i);
                        }
                        counter++;
                        isActivo = true;
                    }
                }

            });
            hilo.start();
        }

    }
}
