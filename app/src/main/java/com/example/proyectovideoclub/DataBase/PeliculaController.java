package com.example.proyectovideoclub.DataBase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.proyectovideoclub.Callbacks.AccionCallback;
import com.example.proyectovideoclub.Callbacks.AlquileresCallback;
import com.example.proyectovideoclub.Callbacks.DirectoresCallback;
import com.example.proyectovideoclub.Callbacks.PeliculasCallback;
import com.example.proyectovideoclub.Callbacks.UsuarioCallback;
import com.example.proyectovideoclub.Callbacks.UsuariosCallback;
import com.example.proyectovideoclub.Clases.Alquiler;
import com.example.proyectovideoclub.Clases.Director;
import com.example.proyectovideoclub.Clases.Pelicula;
import com.example.proyectovideoclub.Clases.Respuesta;
import com.example.proyectovideoclub.Clases.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculaController {


    private final PeliculaService peliculaService = new PeliculaService();

    public void createUsuario(Usuario usuario) {
        peliculaService.createUsuario(usuario);
    }
    public void getUsuario(String nombre, final UsuarioCallback callback) {
        peliculaService.getUsuario(nombre).enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d("TAG", response.body().toString());
                    Usuario usuario = response.body();
                    if (usuario != null) {
                        callback.onUsuarioReceived(usuario);
                    } else {
                        callback.onFailure("Response body is null");
                    }
                } else {
                    callback.onFailure("Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
    public void getUsuarios(final UsuariosCallback callback) {
        peliculaService.getUsuarios().enqueue(
                new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        ArrayList<Usuario> usuarios = new ArrayList<>();
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            for (Usuario usuario: response.body()) {
                                Log.d("TAG", usuario.toString());
                                usuarios.add(usuario);
                            }
                        } else {
                            Log.d("TAG", "Error");
                            callback.onFailure("Request failed with code: " + response.code());
                        }
                        callback.onUsuariosReceived(usuarios);
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Usuario>> call, @NonNull Throwable t) {
                        Log.d("Error", Objects.requireNonNull(t.getMessage()));
                    }
                }
        );
    }
    public void createPelicula(Pelicula peli, final AccionCallback callback) {
        peliculaService.createPelicula(peli).enqueue(
                new Callback<Respuesta>() {
                    @Override
                    public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                        callback.onActionCompleted();
                    }

                    @Override
                    public void onFailure(@NonNull Call<Respuesta> call, @NonNull Throwable t) {
                        Log.d("Error", Objects.requireNonNull(t.getMessage()));
                    }
                }
        );
    }

    public void getPeliculas(String dni, final PeliculasCallback callback) {
        peliculaService.getPeliculas(dni).enqueue(
                new Callback<List<Pelicula>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Pelicula>> call, @NonNull Response<List<Pelicula>> response) {
                        ArrayList<Pelicula> peliculas = new ArrayList<>();
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            for (Pelicula peli: response.body()) {
                                Log.d("TAG", peli.toString());
                                peliculas.add(peli);
                            }
                        } else {
                            Log.d("TAG", "Error");
                            callback.onFailure("Request failed with code: " + response.code());
                        }
                        callback.onPeliculasReceived(peliculas);
                    }
                    @Override
                    public void onFailure(@NonNull Call<List<Pelicula>> call, @NonNull Throwable t) {
                        Log.d("Error", Objects.requireNonNull(t.getMessage()));
                    }
                }
        );
    }
    //Obtener pelicula
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
    //borrar pelicula
    public void borrarPelicula(int id) {
        peliculaService.borrarPelicula(id).enqueue(new Callback<Pelicula>() {
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

    //DIRECTORES
    //Obtener los directores
  public void getDirectores(final DirectoresCallback callback) {
        peliculaService.getDirectores().enqueue(
                new Callback<List<Director>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Director>> call, @NonNull Response<List<Director>> response) {
                        ArrayList<Director> directores = new ArrayList<>();
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            for (Director dir: response.body()) {
                                Log.d("TAG", dir.toString());
                                directores.add(dir);
                            }
                        } else {
                            Log.d("TAG", "Error");
                            callback.onFailure("Request failed with code: " + response.code());
                        }
                        callback.onDirectoresReceived(directores);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Director>> call, @NonNull Throwable t) {
                        Log.d("Error", Objects.requireNonNull(t.getMessage()));
                    }
                }
        );
    }

    //ALQUILERES
    //Obtener los alquileres
    public void getAlquileres(final AlquileresCallback callback) {
        peliculaService.getAlquileres().enqueue(
                new Callback<List<Alquiler>>() {
                    @Override
                    public void onResponse(Call<List<Alquiler>> call, Response<List<Alquiler>> response) {
                        ArrayList<Alquiler> alquileres = new ArrayList<>();
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            for (Alquiler al: response.body()) {
                                Log.d("TAG", al.toString());
                                alquileres.add(al);
                            }
                        } else {
                            Log.d("TAG", "Error");
                            callback.onFailure("Request failed with code: " + response.code());
                        }
                        callback.onAlquileresReceived(alquileres);
                    }
                    @Override
                    public void onFailure(Call<List<Alquiler>> call, Throwable t) {

                    }
                }
        );
    }
    public void extenderDevolucion(int idAlquiler) {
        peliculaService.extenderDevolucion(idAlquiler).enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(@NonNull Call<Respuesta> call, @NonNull Response<Respuesta> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    Log.d("TAG", response.body().toString());
                } else {
                    Log.d("TAG", "Error");
                }
            }
            @Override
            public void onFailure(@NonNull Call<Respuesta> call, @NonNull Throwable t) {
                Log.d("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
