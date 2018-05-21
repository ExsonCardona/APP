package com.example.luisp.login;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Login extends StringRequest {


    private static final String LOGIN_URL ="https://lpgarcia.000webhostapp.com/Login.php";

    private Map<String,String> params;
    public Login (String usuario,  String password, Response.Listener<String> listener) {
        super (Request.Method.POST, LOGIN_URL,listener, null );
        params= new HashMap<>();


        params.put ("usuario",usuario);
        params.put ("password", password);



    }
    @Override
    public Map<String,String> getParams() {return params;}



}
