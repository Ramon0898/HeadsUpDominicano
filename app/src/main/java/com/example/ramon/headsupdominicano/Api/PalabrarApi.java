package com.example.ramon.headsupdominicano.Api;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

import com.example.ramon.headsupdominicano.Modelo.palabras;

public interface PalabrarApi {

    @GET("posts")
    Call<List<palabras>>getPosts();


}
