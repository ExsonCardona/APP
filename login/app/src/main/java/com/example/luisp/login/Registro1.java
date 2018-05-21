package com.example.luisp.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro1 extends AppCompatActivity implements View.OnClickListener {

    EditText etnombre,etusuario,etpassword, etedad;
    Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro1);

        etnombre= (EditText) findViewById(R.id.EditT_nombre);
        etusuario= (EditText) findViewById(R.id.EditT_usuario);
        etpassword= (EditText) findViewById(R.id.EditT_password);
        etedad= (EditText) findViewById(R.id.EditT_edad);

        btn_registrar = (Button) findViewById(R.id.Btn_registrar);

        btn_registrar.setOnClickListener(this);



    }

    @Override
    public  void  onClick(View view) {

        final String nombre=etnombre.getText().toString();
        final String usuario=etusuario.getText().toString();
        final String password=etpassword.getText().toString();
        final int edad= Integer.parseInt(etedad.getText().toString());

        Response.Listener<String> respoListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success= jsonResponse.getBoolean("sucess");

                    if(success){
                        Intent intent = new Intent(Registro1.this,MainActivity.class);
                        Registro1.this.startActivity(intent);

                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Registro1.this);
                        builder.setMessage("error registro")
                                .setNegativeButton("Retry",null)
                                .create().show();
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        };

        Registro registro=new Registro(nombre,usuario,password,edad, respoListener);
        RequestQueue queue = Volley.newRequestQueue(Registro1.this);
            queue.add(registro);

    }


}
