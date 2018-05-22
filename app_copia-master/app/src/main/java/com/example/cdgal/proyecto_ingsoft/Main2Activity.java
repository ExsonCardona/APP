package com.example.cdgal.proyecto_ingsoft;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Button consultar;
    Button consultarporid;
    Button insertar;
    Button actualizar;
    Button borrar;
    EditText identificador;
    EditText nombre;
    EditText direccion;
    TextView resultado;

    //IP de mi Url:
    String IP = "https://andproyect123.000webhostapp.com";
    //Ruta de los Web Services:
    String GET = IP+"/obtener_alumnos.php";
    String GET_BY_ID = IP+"/obtener_alumno_por_id.php";
    String UPDATE = IP+"/actualizar_alumno.php";
    String DELETE = IP+"/borrar_alumno.php";
    String INSERT = IP+"/insertar_alumno.php";

    obtenerWebService hiloconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Enlaces con elementos visuales del XML

        consultar = (Button)findViewById(R.id.consultar);
        consultarporid = (Button)findViewById(R.id.consultarid);
        insertar = (Button)findViewById(R.id.insertar);
        actualizar = (Button)findViewById(R.id.actualizar);
        borrar = (Button)findViewById(R.id.borrar);
        identificador = (EditText)findViewById(R.id.eid);
        nombre = (EditText)findViewById(R.id.enombre);
        direccion = (EditText)findViewById(R.id.edireccion);
        resultado = (TextView)findViewById(R.id.resultado);
        //imageView = (ImageView)

        //Listener de los botones

        consultar.setOnClickListener(this);
        consultarporid.setOnClickListener(this);
        insertar.setOnClickListener(this);
        actualizar.setOnClickListener(this);
        borrar.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.consultar:
                hiloconexion = new obtenerWebService();
                hiloconexion.execute(GET,"1"); //Parametro que recibe
                break;
            case R.id.consultarid:
                hiloconexion = new obtenerWebService();
                String cadenallamada = GET_BY_ID+"?idalumno="+identificador.getText().toString();
                hiloconexion.execute(cadenallamada,"2"); //Parametro que recibe
                break;
            case R.id.insertar:
                hiloconexion = new obtenerWebService();
                hiloconexion.execute(INSERT,"3",nombre.getText().toString(),direccion.getText().toString()); //Parametro que recibe
                break;
            case R.id.actualizar:
                hiloconexion = new obtenerWebService();
                hiloconexion.execute(UPDATE,"4",identificador.getText().toString(),nombre.getText().toString(),direccion.getText().toString()); //Parametro que recibe
                break;
            case R.id.borrar:
                hiloconexion = new obtenerWebService();
                hiloconexion.execute(DELETE,"5",identificador.getText().toString()); //Parametro que recibe
                break;
            default:
                break;
        }

    }

    public class obtenerWebService extends AsyncTask<String,Void,String>{
        Bitmap bitmap;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            resultado.setText(s);
            //imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected String doInBackground(String... strings) {
            String cadena = strings[0];
            URL url = null; //URL donde se obtiene información
            String devuelve = "";

            if (strings[1]=="1"){ //Consulta de todos los alumnos...
                try{
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux: Android 1.5; es-ES) Ejemplo HTTP");
                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(connection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String line;
                        while ((line = reader.readLine()) != null){
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());
                        String resultJSON = respuestaJSON.getString("estado");

                        if (resultJSON=="1"){
                            JSONArray alumnosJSON = respuestaJSON.getJSONArray("alumnos");
                            for (int i=0; i<alumnosJSON.length();i++){
                                devuelve = devuelve + alumnosJSON.getJSONObject(i).getString("idalumno")+" "+
                                        alumnosJSON.getJSONObject(i).getString("nombre")+" " +
                                        alumnosJSON.getJSONObject(i).getString("direccion")+"\n";
                            }
                        }else if (resultJSON=="2"){
                            devuelve = "No hay alumnos";
                        }
                    }
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } catch (JSONException e){
                    e.printStackTrace();
                }
                return devuelve;
            }else if (strings[1]=="2"){ //Consulta por id...

                try{
                    url = new URL(cadena);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                            " (Linux: Android 1.5; es-ES) Ejemplo HTTP");
                    int respuesta = connection.getResponseCode();
                    StringBuilder result = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK){
                        InputStream in = new BufferedInputStream(connection.getInputStream());
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                        String line;
                        while ((line = reader.readLine()) != null){
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());
                        String resultJSON = respuestaJSON.getString("estado");

                        if (resultJSON=="1"){
                            devuelve = devuelve + respuestaJSON.getJSONObject("alumno").getString("idAlumno")+" "+
                                    respuestaJSON.getJSONObject("alumno").getString("nombre")+" " +
                                    respuestaJSON.getJSONObject("alumno").getString("direccion")+"\n";

                            /*String nombreimagen = "";

                            if (respuestaJSON.getJSONObject("alumno").getString("rutaimagen").equals("noimagen")){
                                bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.icon_fallo);
                            }else{
                                URL urlimagen = new URL(IMAGENES+respuestaJSON.getJSONObject("alumno").getString("rutaimagen"));
                                HttpURLConnection conimagen = (HttpURLConnection) urlimagen.openConnection();
                                bitmap = BitmapFactory.decodeStream(conimagen.getInputStream());
                            }*/
                        }else if (resultJSON=="2"){
                            devuelve = "No hay alumnos";
                        }
                    }
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } catch (JSONException e){
                    e.printStackTrace();
                }
                return devuelve;
            }else if (strings[1]=="3"){ //Insertar alumno...
                try{
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    url = new URL(cadena);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("nombre", strings[2]);
                    jsonParam.put("direccion", strings[3]);

                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    int respuesta = urlConn.getResponseCode();

                    StringBuilder result = new StringBuilder();

                    if (respuesta==HttpURLConnection.HTTP_OK){
                        String line;
                        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line=br.readLine()) != null){
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());

                        String resultJson = respuestaJSON.getString("estado");
                        if (resultJson=="1"){
                            devuelve = "Alumno insertado correctamente";
                        }else if(resultJson=="2"){
                            devuelve = "El alumno no pudo insertarse";
                        }
                    }

                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } catch (JSONException e){
                    e.printStackTrace();
                }
                return devuelve;
            }else if (strings[1]=="4"){ //Actualizar alumno...
                try{
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    url = new URL(cadena);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("idalumno", strings[2]);
                    jsonParam.put("nombre", strings[3]);
                    jsonParam.put("direccion", strings[4]);

                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    int respuesta = urlConn.getResponseCode();

                    StringBuilder result = new StringBuilder();

                    if (respuesta==HttpURLConnection.HTTP_OK){
                        String line;
                        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line=br.readLine()) != null){
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());

                        String resultJson = respuestaJSON.getString("estado");
                        if (resultJson=="1"){
                            devuelve = "Alumno actualizado correctamente";
                        }else if(resultJson=="2"){
                            devuelve = "El alumno no pudo actualizarse";
                        }
                    }

                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } catch (JSONException e){
                    e.printStackTrace();
                }
                return devuelve;
            }else if (strings[1]=="5"){ //Borrar alumno...
                try{
                    HttpURLConnection urlConn;

                    DataOutputStream printout;
                    DataInputStream input;
                    url = new URL(cadena);
                    urlConn = (HttpURLConnection) url.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(true);
                    urlConn.setUseCaches(false);
                    urlConn.setRequestProperty("Content-Type", "application/json");
                    urlConn.setRequestProperty("Accept", "application/json");
                    urlConn.connect();

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("idalumno", strings[2]);

                    OutputStream os = urlConn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(jsonParam.toString());
                    writer.flush();
                    writer.close();

                    int respuesta = urlConn.getResponseCode();

                    StringBuilder result = new StringBuilder();

                    if (respuesta==HttpURLConnection.HTTP_OK){
                        String line;
                        BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
                        while ((line=br.readLine()) != null){
                            result.append(line);
                        }

                        JSONObject respuestaJSON = new JSONObject(result.toString());

                        String resultJson = respuestaJSON.getString("estado");
                        if (resultJson=="1"){
                            devuelve = "Alumno borrado correctamente";
                        }else if(resultJson=="2"){
                            devuelve = "No hay alumnos";
                        }
                    }

                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                } catch (JSONException e){
                    e.printStackTrace();
                }
                return devuelve;
            }
            return null;
        }
    }
}
