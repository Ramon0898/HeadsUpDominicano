package com.example.ramon.headsupdominicano;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class Questions extends AppCompatActivity  {



    TextView contador;//variable para darle valor de el xml id
    CountDownTimer ConteoAtras; // con esto llamo al metodo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);


        contador = findViewById(R.id.Counter);//lA Variable con el id de el xml




            ConteoAtras = new CountDownTimer(6000,1000)// Los parametros dice los segundos y cuanto se le va a restar
        {



            @Override
            public void onTick(long l)//este metodo se va a correr cuando empieze el conteo
            {

                contador.setText(l / 1000+ "");
            }

            @Override
            public void onFinish()//este se va a correr cuando se acabe el conteo
            {
                contador.setText("Go!!");

            }


        }.start();//con esto empieza el coteo al entrar al activity




    }


}













