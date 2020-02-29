package com.example.ramon.headsupdominicano;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramon.headsupdominicano.Api.PalabrarApi;
import com.example.ramon.headsupdominicano.Modelo.palabras;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Deportes extends AppCompatActivity {

    MediaPlayer player;
    int NumeroRespuesta=0;
    Intent in;
    int aux =0;
    int Puntaje=0;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    TextView correcto, incorrecto;
    CountDownTimer ConteoAtras;
    CountDownTimer ConteoAtras2;
    TextView conteo, segundos, textFinalizado;
    ImageView imgFinalizado;
    Dialog epicDialog;
    Button reintentar, volver;

    private TextView tex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deportes);

        correcto = findViewById(R.id.correcto);
        incorrecto = findViewById(R.id.incorrecto);
        tex= findViewById(R.id.texto);

        conteo = findViewById(R.id.Time);
        segundos= findViewById(R.id.Segundos);

        tex.setEnabled(true);
        tex.setTextColor(Color.TRANSPARENT);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        ActualizarPreguntas();


        ConteoAtras = new CountDownTimer(61000,1000)// Los parametros dice los segundos y cuanto se le va a restar
        {



            @Override
            public void onTick(long l)//este metodo se va a correr cuando empieze el conteo
            {

                conteo.setText (l / 1000 + " Seg" );


            }

            @Override
            public void onFinish()//este se va a correr cuando se acabe el conteo
            {
                conteo.setText("Game Over");
                if(conteo.getText() == "Game Over")
                {
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

        epicDialog = new Dialog(this);



    }

    /**public void ResultadoFinal() {
        reintentar = findViewById(R.id.btnReintentar);
        volver = findViewById(R.id.btnvolver);
        AlertDialog.Builder builder = new AlertDialog.Builder(Deportes.this);

        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.resultadofinal, null);

        builder.setView(view);

        //TODO BOTONES POR DEFECTO
        /**
         builder.setView(inflater.inflate(R.layout.dialog_personalizado,null))
         .setPositiveButton("Reintentar", new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {
        }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override public void onClick(DialogInterface dialog, int which) {
        Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT).show();
        }
        });



        final AlertDialog dialog = builder.create();
        dialog.show();

        TextView txt = view.findViewById(R.id.text_dialog);
        txt.setText("AQUI PONES EL RESULTADO");

        reintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }**/




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

                if(orientations[1] >50 )
                {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);

                    float d = orientations[1];
                    int i = (int) d;
                    String dd = String.valueOf(i);


                    if(i>=50 && aux ==0 )
                    {
                        ActualizarPreguntas();
                    }

                } else if(orientations[1] < -30){

                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);

                    float d = orientations[1];
                    int i = (int) d;
                    // String dd = String.valueOf(i);


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
                    "Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines",
                    "Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines",
                    "Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines",
                    "Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines",
                    "Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines",
                    "Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines","Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines",
                    "Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines",
                    "Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines","Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines",
                    "Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines",
                    "Big Papi",
                    "Pelota",
                    "Jose Reyes",
                    "Guantes",
                    "Tennis Deportivos",
                    "Cancha de Basquetball",
                    "Estadio de Futball",
                    "Guantes de Boxeo",
                    "Pesas",
                    "Patines"


            };

    public String obtenerPreguntas(int a)
    {
        String Preguntas = PreguntasDeporte[a];
        return Preguntas;

    }

    public void ActualizarPreguntas()
    {
        NumeroRespuesta++;
        tex.setText(obtenerPreguntas((NumeroRespuesta)));



    }


    public void ShowAlertDialog()
    {

        android.app.AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Quieres Jugar la categoria de Arte Otra vez? ");
        alert.setMessage("Puntos Obtenidos: " + Puntaje);
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Deportes.this, Deportes.class);
                startActivity(i);
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Deportes.this, MainActivity.class);
                startActivity(i);
            }
        });
        alert.create().show();
    }

}
