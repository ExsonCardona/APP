package com.emalug.apparqueos.Activitis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.emalug.apparqueos.Constantes.Constantes;
import com.emalug.apptaxisv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button auntenticar , registrar ,  cancelar ;
    RequestQueue requestQueue;
    EditText Usuario , Clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auntenticar = (Button) findViewById(R.id.btn_login);
        registrar = (Button) findViewById(R.id.btn_registrar);
        cancelar = (Button) findViewById(R.id.btn_cancelar);
        Usuario  = (EditText) findViewById(R.id.Usuario);
        Clave  = (EditText) findViewById(R.id.Clave);

        //ESCUCHAS PARA  LOS  EVENTOS  DE  LOS  BOTONES  DE LA  ACTIVITY
        auntenticar.setOnClickListener(this);
        registrar.setOnClickListener(this);
        cancelar.setOnClickListener(this);


        //ANDROID VOLLEY
        requestQueue = Volley.newRequestQueue(this);
    }


    public void Login_User() {
        String user = Usuario.getText().toString().trim();
        String Pass = Clave.getText().toString().trim();

        if(user.equals("") || Pass.equals("") ){
            Toast.makeText(this, "Ha dejado campos vacios",
                    Toast.LENGTH_LONG).show();
        }else{

                Toast.makeText(this, Constantes.Url_base_get_final + "?Username="+user+"&Password="+Pass+"&op=ValidarUsuario",
                        Toast.LENGTH_LONG).show();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constantes.Url_base_get_final + "?Username="+user+"&Password="+Pass+"&op=ValidarUsuario",
                    new Response.Listener<JSONObject>(){

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray Usuario = response.getJSONArray("User");
                                JSONObject datosUer = Usuario.getJSONObject(0);
                                String strError = datosUer.getString("strError");

                                if(strError.equals("-1")){
                                    Toast.makeText(LoginActivity.this, "No se encontro el usuario" , Toast.LENGTH_LONG).show();
                                }else{
                                    String user = datosUer.getString("Username");
                                    String id   = datosUer.getString("Id");
                                    Toast.makeText(LoginActivity.this, "Bienvenido " + user , Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener(){

                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(LoginActivity.this, (CharSequence) error, Toast.LENGTH_LONG).show();
                        }
                    }

            );
            requestQueue.add(jsonObjectRequest);
        }


    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_login:

                Login_User();
            break;
            case R.id.btn_registrar:

                Intent intent = new Intent(LoginActivity.this, RegistroUsuariosActivity.class);
                startActivity(intent);
                finish();

                break;
            case R.id.btn_cancelar:

                finish();

                break;



        }

    }
}
