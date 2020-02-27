package com.example.ramon.headsupdominicano;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ramon.headsupdominicano.Api.PalabrarApi;
import com.example.ramon.headsupdominicano.Modelo.palabras;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Deportes extends AppCompatActivity {

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    TextView correcto, incorrecto;
    private TextView tex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deportes);

        correcto = findViewById(R.id.correcto);
        incorrecto = findViewById(R.id.incorrecto);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

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

                if(orientations[1] > 30 ) {
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                } else if(orientations[1] < -30) {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                } else if(orientations[2] < 10) {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }

            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {
            }
        };

        // Register it
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);


        getPalabras();
    }

    private void getPalabras()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:3000")//Esta es la url de la Api
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PalabrarApi palabrarApi = retrofit.create(PalabrarApi.class);

        Call<List<palabras>> call = palabrarApi.getPosts();

        call.enqueue(new Callback<List<palabras>>() {
            @Override
            public void onResponse(Call<List<palabras>> call, Response<List<palabras>> response) {
                if(!response.isSuccessful())
                {
                    tex.setText("codigo" + response.code());
                    return;
                }

                List<palabras> postList = response.body();

                for (palabras palabras: postList)
                {
                    String content="";
                    content += "id" + palabras.getId() + "\n" ;
                    content += "palabra" + palabras.getPalabra();

                    tex.append(content);


                }
            }

            @Override
            public void onFailure(Call<List<palabras>> call, Throwable t) {

                //tex.setText(t.getMessage());
            }
        });
    }
}
