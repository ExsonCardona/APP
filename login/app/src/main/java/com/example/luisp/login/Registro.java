package com.example.luisp.login;


import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Registro extends StringRequest {

    private static  final  String REGISTRO_URL="https://lpgarcia.000webhostapp.com/Registro.php";

    private Map<String,String> params;
    public Registro (String nombre,String usuario,  String password, int edad, Response.Listener<String> listener) {
        super (Method.POST, REGISTRO_URL,listener, null );
        params= new HashMap<>();
        params.put ("nombre",nombre);
        params.put ("usuario",usuario);
        params.put ("password", password);
        params.put ("edad",edad+"");

    }
@Override
    public Map<String,String> getParams() {
        return params;
    }

}
