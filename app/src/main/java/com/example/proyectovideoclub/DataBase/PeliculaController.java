package com.example.proyectovideoclub.DataBase;

import android.util.Log;

import androidx.annotation.NonNull;
import com.example.proyectovideoclub.Clases.Pelicula;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculaController {
    private final PeliculaService peliculaService = new PeliculaService();

    public void createPelis(Pelicula peli) {
        peliculaService.createPelicula(peli);
    }

    public void getPeliculas() {
        peliculaService.getPeliculas().enqueue(
                new Callback<List<Pelicula>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Pelicula>> call, @NonNull Response<List<Pelicula>> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            for (Pelicula peli: response.body())
                                Log.d("TAG", peli.toString());
                        } else {
                            Log.d("TAG", "Error");
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Pelicula>> call, @NonNull Throwable t) {
                        Log.d("Error", Objects.requireNonNull(t.getMessage()));
                    }
                }
        );
    }

    public void getPelicula(int id) {
        peliculaService.getPelicula(id).enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(@NonNull Call<Pelicula> call, @NonNull Response<Pelicula> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d("TAG", response.body().toString());
                } else {
                    Log.d("TAG", "Error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Pelicula> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
