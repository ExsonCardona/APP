package com.emalug.apparqueos.Activitis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.emalug.apparqueos.Constantes.Constantes;
import com.emalug.apptaxisv2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegistroUsuariosActivity extends AppCompatActivity implements View.OnClickListener {
    AutoCompleteTextView Autocomplete;
    Button Guardar , Cancelar;
    ArrayList<String> Lista;
    ArrayAdapter adaptador;
    private String [] tipos = new String[] {"Usuario" , "Dueño De Parqueo" };
    EditText usuarionuevo , password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);
        Autocomplete = (AutoCompleteTextView)  findViewById(R.id.Autocomplete);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tipos );
        Autocomplete.setAdapter(adapter);

        Guardar = (Button) findViewById(R.id.btn_Guardar);
        Cancelar = (Button) findViewById (R.id.btn_cancel);
        Guardar.setOnClickListener(this);
        Cancelar.setOnClickListener(this);

        usuarionuevo  = (EditText) findViewById(R.id.txtuser);
        password  = (EditText) findViewById(R.id.txtpass);


    }

    public void CrearUsuario(){
        final String user = usuarionuevo.getText().toString().trim();
        final String Pass = password.getText().toString().trim();
        String tipoUsuario = Autocomplete.getText().toString();
        String tipo = "";


        if( tipoUsuario.equals("Usuario") ||  tipoUsuario.equals("Dueño De Parqueo")  ){
            if( tipoUsuario.equals("Usuario")){
                tipo = "1";
            }else{
                tipo = "2";
            }



            final String TipoUserFinal = tipo;

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.Url_base_post_final, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals(0)){
                        Toast.makeText(getApplicationContext(), "Problemas con el api no se pudo crear el usuario",
                                Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Usuario Creado Satisfactoriamente", Toast.LENGTH_SHORT).show();
                        if( TipoUserFinal.equals("1")){

                            Intent intent = new Intent(RegistroUsuariosActivity.this, MenuActivity.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Intent intent = new Intent(RegistroUsuariosActivity.this , RegistroParqueosActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }




                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametro = new HashMap<String, String>();
                    parametro.put(Constantes.Username , user);
                    parametro.put(Constantes.Password , Pass);
                    parametro.put(Constantes.Tipo , TipoUserFinal);
                    parametro.put(Constantes.op , "RegistrarUsuario");


                    return parametro;
                }
            };

            //para crear las peticiones
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            //agregar al volley el obeto stringRequest
            requestQueue.add(stringRequest);

        }else{
            Toast.makeText(getApplicationContext(), "Tipo de usuario no valido ingrese Usuario o Dueño De Parqueo ",
                    Toast.LENGTH_SHORT).show();
        }



    }


    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_Guardar:

                CrearUsuario();
                break;
            case R.id.btn_cancel:
                Intent intent = new Intent(RegistroUsuariosActivity.this , LoginActivity.class);
                startActivity(intent);
                finish();

                break;



        }

    }
}
