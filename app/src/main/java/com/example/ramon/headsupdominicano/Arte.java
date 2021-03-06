package com.example.ramon.headsupdominicano;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Arte extends AppCompatActivity {

    int Puntaje =0;
    int NumeroRespuesta=0;
    SensorManager sensorManager;
    Sensor sensor;
    TextView l;
    int aux =0;
    SensorEventListener sensorEventListener;
    CountDownTimer ConteoAtras;
    CountDownTimer ConteoAtras2;
    TextView conteo;
    TextView segundos;
    private TextView tex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arte);

        tex= findViewById(R.id.textoArt);


        conteo = findViewById(R.id.ConteoArte);
        segundos= findViewById(R.id.SegundosArt);

        tex.setEnabled(true);
        tex.setTextColor(Color.TRANSPARENT);

        ActualizarPreguntas();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);






        ConteoAtras = new CountDownTimer(61000,1000)// Los parametros dice los segundos y cuanto se le va a restar
        {



            @Override
            public void onTick(long l)//este metodo se va a correr cuando empieze el conteo
            {

                conteo.setText(l / 1000+ ":00");

            }

            @Override
            public void onFinish()//este se va a correr cuando se acabe el conteo
            {
                conteo.setText("Game Over");
                if(conteo.getText() == "Game Over") {
                    tex.setEnabled(true);
                    tex.setTextColor(Color.TRANSPARENT);
                    ShowAlertDialog();
                }

            }
        };//con esto empieza el coteo al entrar al activity

        ConteoAtras2 = new CountDownTimer(6000,1000)// Los parametros dice los segundos y cuanto se le va a restar
        {
            @Override
            public void onTick(long l)//este metodo se va a correr cuando empieze el conteo
            {

                segundos.setText(l / 1000+ "");

            }

            @Override
            public void onFinish()//este se va a correr cuando se acabe el conteo
            {
                segundos.setText("Let´s Go");

                if(segundos.getText() == "Let´s Go")
                {

                    segundos.setEnabled(true);
                    segundos.setTextColor(Color.TRANSPARENT);

                    tex.setEnabled(false);
                    tex.setTextColor(Color.BLACK);

                    ConteoAtras.start();
                }
            }
        }.start();//con esto empieza el coteo al entrar al activity

        Pass();
    }
    public void Pass(){ //METODO PARA PASS AND BYPASS //LO LLAMAS DONDE IMPLEMENTEMOS LAS CONDICIONES DE LAS PALABRAS
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent.values);

                //Reasignar sistema de coordenadas
                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_X,
                        SensorManager.AXIS_Z,
                        remappedRotationMatrix);
                // Convertir los ejes
                float[] orientations = new float[3];
                SensorManager.getOrientation(remappedRotationMatrix, orientations);

                for (int i = 0; i < 3; i++) {
                    orientations[i] = (float) (Math.toDegrees(orientations[i]));
                }

                if(orientations[1] >60   )
                {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);

                    float d = orientations[1];
                    int i = (int) d;

                    if(i>=50 && aux==0)
                    {
                        ActualizarPreguntas();
                    }

                } else if(orientations[1] < -30){

                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);

                    float d = orientations[1];
                    int i = (int) d;
                    //String dd = String.valueOf(i);


                    if(i<= -30)
                    {

                        ActualizarPreguntas();
                        Puntaje++;
                    }

                } else if(orientations[2] < 10) {
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);

                }

            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };




        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);



    }

    public String PreguntasDeporte[]=
            {
                    "Romeo Santos", "Kiko el Crazy", "Fefita la grande", "El Alfa", "El Torito", "El Mayimbre", "....440", "Alofoke", "Jochy Santos", "Robertico Salcedo", "Boca De Piano", "Raymond Y Miguel", "Monalisa","Romeo Santos", "Kiko el Crazy", "Fefita la grande", "El Alfa", "El Torito", "El Mayimbre", "....440", "Alofoke", "Jochy Santos", "Robertico Salcedo", "Boca De Piano", "Raymond Y Miguel", "Monalisa","Romeo Santos", "Kiko el Crazy", "Fefita la grande", "El Alfa", "El Torito", "El Mayimbre", "....440", "Alofoke", "Jochy Santos", "Robertico Salcedo", "Boca De Piano", "Raymond Y Miguel", "Monalisa","Romeo Santos", "Kiko el Crazy", "Fefita la grande", "El Alfa", "El Torito", "El Mayimbre", "....440", "Alofoke", "Jochy Santos", "Robertico Salcedo", "Boca De Piano", "Raymond Y Miguel", "Monalisa"


            };

    public String obtenerPreguntas(int a)
    {

        String Preguntas = PreguntasDeporte[a];
        return Preguntas;


    }

    public void ActualizarPreguntas()
    {

        tex.setText(obtenerPreguntas((NumeroRespuesta)));
        NumeroRespuesta++;

    }

    public void ShowAlertDialog()
    {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Quieres Jugar la categoria de Arte Otra vez? ");
        alert.setMessage("Puntos Obtenidos: " + Puntaje);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Arte.this, Arte.class);
                startActivity(i);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Arte.this, MainActivity.class);
                startActivity(i);
            }
        });
        alert.create().show();
    }
}
