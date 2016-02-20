package la.hackspace.reto2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import la.hackspace.reto2.entity.Pokemon;
import la.hackspace.reto2.service.APIImplementation;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view) {
        APIImplementation.getService().getPokemons(new Callback<ArrayList<Pokemon>>() {
            @Override
            public void success(ArrayList<Pokemon> pokemons, Response response) {
                EditText user =  (EditText) findViewById(R.id.txtUser);
                EditText pass =  (EditText) findViewById(R.id.txtPass);
                boolean logged = false;
                for (Pokemon p : pokemons) {
                    if(user.getText().toString().equals(p.getNombre())) {
                        if(pass.getText().toString().equals(p.getTipo())) {
                            logged = true;
                            Intent i = new Intent(getApplicationContext(), PokemonList.class);
                            startActivity(i);
                        }
                    }
                }
                if(!logged) {
                    Toast.makeText(getApplicationContext(), "Error al iniciar sesión", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.e("Respuesta", retrofitError.getLocalizedMessage());
                Toast.makeText(getApplicationContext(), "Algo salió mal...", Toast.LENGTH_SHORT);
            }
        });
    }
}
