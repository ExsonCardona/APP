package com.example.cdgal.proyecto_ingsoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MiCuentaFragment extends Fragment {

    Button b1;
    EditText nom, cor, num, con;
    private Button log_out;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_micuenta, container, false);


        nom = (EditText)v.findViewById(R.id.nombre);
        cor = (EditText)v.findViewById(R.id.correo);
        num = (EditText)v.findViewById(R.id.numero);
        con = (EditText)v.findViewById(R.id.password);

        nom.setEnabled(false);
        cor.setEnabled(false);
        num.setEnabled(false);
        con.setEnabled(false);

        b1=(Button)v.findViewById(R.id.editarCuenta);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Toast.makeText(MiCuentaFragment.this.getActivity(),"YOUR MESSAGE",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MiCuentaFragment.this.getActivity(), EditarMiCuenta.class);
                startActivity(intent);
            }
        });

        log_out = (Button) v.findViewById(R.id.cerrarsesion);
        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity().getApplicationContext(),IniciarSesion.class));
            }
        });

        return v;
    }

    public void llamarEditarAgente(View view){

    }
}