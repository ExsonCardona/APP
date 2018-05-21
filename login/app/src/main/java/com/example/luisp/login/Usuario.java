package com.example.luisp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Usuario extends AppCompatActivity {

    TextView tvNombre, tvUsuario, tvPassword, tvEdad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        tvNombre = findViewById(R.id.TextV_nombre);
        tvUsuario = findViewById(R.id.TextV_usuario);
        tvPassword = findViewById(R.id.TextV_password);
        tvEdad = findViewById(R.id.TextV_edad);

        Intent intent = getIntent();
        String nombre=intent.getStringExtra("nombre");
        String usuario=intent.getStringExtra("usuario");
        String password=intent.getStringExtra("password");
        int edad=intent.getIntExtra("edad",-1);

        tvNombre.setText(nombre);
        tvUsuario.setText(usuario);
        tvPassword.setText(password);
        tvEdad.setText(edad+"");




    }
}
