package com.emalug.apparqueos.Activitis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.emalug.apparqueos.Constantes.Constantes;
import com.emalug.apptaxisv2.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegistroParqueosActivity extends AppCompatActivity {
    Spinner spinner;
    RequestQueue requestQueue;

    ArrayList<String> Paises;

    //spinner_pais
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_parqueos);

        spinner = (Spinner) findViewById(R.id.spinner_pais) ;
        java.util.ArrayList<String> strings = new java.util.ArrayList<>();
        GetPaises();

    }

    public void GetPaises(){
        StringRequest stringRequest = new StringRequest (Request.Method.GET, Constantes.Url_base_get_final + "?op=GetPais",
                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String  response) {
                        try {
                            Toast.makeText(RegistroParqueosActivity.this,Constantes.Url_base_get_final + "?op=GetPais", Toast.LENGTH_LONG).show();
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("paises");
                            Toast.makeText(RegistroParqueosActivity.this,response, Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(RegistroParqueosActivity.this, (CharSequence) error, Toast.LENGTH_LONG).show();
                    }
                }

        );
        requestQueue.add(stringRequest);
    }
}
