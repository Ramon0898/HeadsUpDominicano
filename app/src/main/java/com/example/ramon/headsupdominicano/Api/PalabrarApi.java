package com.example.ramon.headsupdominicano.Api;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

import com.example.ramon.headsupdominicano.Modelo.palabras;

public interface PalabrarApi {

    @GET("deportes")//aqui le pones la ruta para que te muestre el json que quieres ver, ej: si http://localhost:3004/deportes
    Call<List<palabras>>getPosts();


}
