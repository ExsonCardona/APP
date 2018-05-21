package com.example.luisp.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv_registrar;
    EditText et_usuario, et_password;
    Button Btn_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_registrar= (TextView) findViewById(R.id.tv_registrar);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent (MainActivity.this, Registro1.class);
                MainActivity.this.startActivity(intentReg);
        }
    } );

        Btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String usuario= et_usuario.getText().toString();
                final String password= et_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                String nombre= jsonResponse.getString("nombre");
                                int edad= jsonResponse.getInt("edad");

                                Intent intent = new Intent(MainActivity.this, Usuario.class);
                                intent.putExtra("nombre",nombre);
                                intent.putExtra("usuario",usuario);
                                intent.putExtra("password",password);
                                intent.putExtra("edad",edad);

                                MainActivity.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("error de logeo")
                                        .setNegativeButton("Retry",null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                Login login = new Login(usuario, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(login);


            }
        });


    }

}
