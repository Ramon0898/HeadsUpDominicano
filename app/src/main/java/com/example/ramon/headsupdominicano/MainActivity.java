package com.example.ramon.headsupdominicano;

import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
/*
 Nota: Por ahora solo le puse la funcio al Boton deporte,
 ya cuando sepamos como vaomos a pasar las preguntas, seguiremos implementando en los
 otros Botones
*/
    TextView deportes;
    TextView entretenimiento;
    TextView geografia;
    TextView historia;
    TextView arte;
    TextView quimica;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quimica=findViewById(R.id.Chemistry);
        quimica.setOnClickListener(this);

        arte = findViewById(R.id.Art);
        arte.setOnClickListener(this);

        historia = findViewById(R.id.History);
        historia.setOnClickListener(this);

        geografia=findViewById(R.id.Geografy);
        geografia.setOnClickListener(this);

        entretenimiento = findViewById(R.id.entertainment);
        entretenimiento.setOnClickListener(this);

        deportes= findViewById(R.id.Sports);
        deportes.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {//Asi es mas facil para no crear tantos metodos Onclik, solo creamos el swith y llamamos el id
        switch (v.getId())
        {
            case R.id.Sports:
                i = new Intent(MainActivity.this, Deportes.class);
                startActivity(i);
                break;
            case R.id.entertainment:
                i = new Intent(MainActivity.this, Entretenimiento.class);
                startActivity(i);
                break;
            case R.id.Geografy:
                i = new Intent(MainActivity.this, Geografia.class);
                startActivity(i);
                break;
            case R.id.History:
                i = new Intent(MainActivity.this, Historia.class);
                startActivity(i);
                break;
            case R.id.Art:
                i = new Intent(MainActivity.this, Arte.class);
                startActivity(i);
                break;
            case R.id.Chemistry:
                i = new Intent(MainActivity.this, Quimica.class);
                startActivity(i);
                break;

        }
    }
}
