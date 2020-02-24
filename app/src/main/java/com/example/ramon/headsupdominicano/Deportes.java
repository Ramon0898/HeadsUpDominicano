package com.example.ramon.headsupdominicano;

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

    private TextView tex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deportes);

        tex= findViewById(R.id.texto);

        getPalabras();
    }


    private void getPalabras()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:3004/")//Esta es la url de la Api
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

                tex.setText(t.getMessage());
            }
        });
    }
}
