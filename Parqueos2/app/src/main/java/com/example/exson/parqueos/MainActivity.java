package com.example.exson.parqueos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button sig;
    Button sig2;
    Button sig3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        sig =(Button)findViewById(R.id.btn1);
        sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sig = new Intent(MainActivity.this,Parking.class);
                startActivity(sig);
            }
        });
        sig2=(Button)findViewById(R.id.btn2);
        sig2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sig2 = new Intent(MainActivity.this,Administrador.class);
                startActivity(sig2);
            }
        });
        sig3=(Button)findViewById(R.id.btn3);
        sig3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sig3 = new Intent(MainActivity.this,Registro.class);
                startActivity(sig3);
            }
        });

    }
}
