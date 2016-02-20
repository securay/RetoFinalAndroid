package la.hackspace.reto2.service;

import java.util.ArrayList;


import la.hackspace.reto2.entity.Pokemon;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by SCURAY on 20/02/2016.
 */
public interface APIService {
    @GET("/lista_pokemons.php")
    void getPokemons(Callback<ArrayList<Pokemon>> callback);
}
