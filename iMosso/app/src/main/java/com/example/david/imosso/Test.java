package com.example.david.imosso;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import static java.lang.Math.round;

public class Test extends AppCompatActivity {
    int increment = 0;
    String respuestaCorrecta = "";
    Boolean boton = false;
    int aciertos = 0;
    int fallos = 0;
    int Totalpreguntas = 0;
    Button BTN_next;
    Button BTN_finish;
    int longitudTest=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        BTN_next=(Button)findViewById(R.id.BTN_next);
        final Button BTN_respuesta1 = (Button) findViewById(R.id.BTN_respuesta1);
        final Button BTN_respuesta2 = (Button) findViewById(R.id.BTN_respuesta2);
        final Button BTN_respuesta3 = (Button) findViewById(R.id.BTN_respuesta3);
        final Button BTN_respuesta4 = (Button) findViewById(R.id.BTN_respuesta4);
        showJSONFromAsset(0);
        BTN_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJSONFromAsset(++increment);
                BTN_respuesta1.setBackgroundColor(getResources().getColor(R.color.btn_default));
                BTN_respuesta2.setBackgroundColor(getResources().getColor(R.color.btn_default));
                BTN_respuesta3.setBackgroundColor(getResources().getColor(R.color.btn_default));
                BTN_respuesta4.setBackgroundColor(getResources().getColor(R.color.btn_default));
                BTN_respuesta1.setEnabled(true);
                BTN_respuesta1.setClickable(true);
                BTN_respuesta2.setEnabled(true);
                BTN_respuesta2.setClickable(true);
                BTN_respuesta3.setEnabled(true);
                BTN_respuesta3.setClickable(true);
                BTN_respuesta4.setEnabled(true);
                BTN_respuesta4.setClickable(true);
                boton = false;
            }
        });
        puntos();
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            SharedPreferences prefs =
                    getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
            String ruta = prefs.getString("test", null);
            InputStream is = getAssets().open(ruta);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void showJSONFromAsset(int increment){
        Button BTN_respuesta1 = (Button) findViewById(R.id.BTN_respuesta1);
        Button BTN_respuesta2 = (Button) findViewById(R.id.BTN_respuesta2);
        Button BTN_respuesta3 = (Button) findViewById(R.id.BTN_respuesta3);
        Button BTN_respuesta4 = (Button) findViewById(R.id.BTN_respuesta4);
        TextView TV_pregunta = (TextView) findViewById(R.id.TV_pregunta);
        TextView TV_num = (TextView) findViewById(R.id.TV_num);
        int i=0;
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray test = obj.getJSONArray("test" +1);
            longitudTest = test.length();
            for (i = 0; i < test.length(); i++) {
                JSONObject jsonObject = test.getJSONObject(increment);//i
                String id_pregunta = jsonObject.getString("id_pregunta");
                String pregunta = jsonObject.getString("pregunta");
                String respuesta1 = jsonObject.getString("respuesta1");
                String respuesta2 = jsonObject.getString("respuesta2");
                String respuesta3 = jsonObject.getString("respuesta3");
                String respuesta4 = jsonObject.getString("respuesta4");
                respuestaCorrecta = jsonObject.getString("respuestaCorrecta");
                TV_pregunta.setText(pregunta);
                TV_num.setText("Pregunta: " + id_pregunta + "/" + test.length());
                BTN_respuesta1.setText(respuesta1.toString());
                BTN_respuesta2.setText(respuesta2.toString());
                BTN_respuesta3.setText(respuesta3.toString());
                BTN_respuesta4.setText(respuesta4.toString());
            }
        }catch( JSONException e) {
            e.printStackTrace();
        }
        mostrarResultadoTest(i);
    }


    public void mostrarResultadoTest(int i){
        if (i!=longitudTest){
            SharedPreferences prefs =
                    getSharedPreferences("Nota",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("aciertos", aciertos);
            editor.putInt("fallos", fallos);
            editor.putInt("longitudTest", longitudTest);
            editor.commit();

            Intent myIntent = new Intent(Test.this,
                    notaTest.class);
            startActivity(myIntent);
            finish(); //no deja echar atras así como cuando acabamos el test le damos volvemos al menu inicial
        }
    }

    public void puntos(){
        final Button BTN_respuesta1 = (Button) findViewById(R.id.BTN_respuesta1);
        final Button BTN_respuesta2 = (Button) findViewById(R.id.BTN_respuesta2);
        final Button BTN_respuesta3 = (Button) findViewById(R.id.BTN_respuesta3);
        final Button BTN_respuesta4 = (Button) findViewById(R.id.BTN_respuesta4);
        BTN_respuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BTN_respuesta1.getText().equals(respuestaCorrecta) && boton == false){
                    aciertos++;
                    BTN_respuesta1.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                } else if (BTN_respuesta2.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta1.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta2.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                } else if (BTN_respuesta3.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta1.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta3.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                } else if (BTN_respuesta4.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta1.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta4.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                }
                BTN_respuesta1.setEnabled(false);
                BTN_respuesta1.setClickable(false);
                BTN_respuesta2.setEnabled(false);
                BTN_respuesta2.setClickable(false);
                BTN_respuesta3.setEnabled(false);
                BTN_respuesta3.setClickable(false);
                BTN_respuesta4.setEnabled(false);
                BTN_respuesta4.setClickable(false);
            }
        });

        BTN_respuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BTN_respuesta2.getText().equals(respuestaCorrecta) && boton == false){
                    aciertos++;
                    BTN_respuesta2.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                } else if (BTN_respuesta1.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta2.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta1.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                } else if (BTN_respuesta3.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta2.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta3.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                } else if (BTN_respuesta4.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta2.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta4.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                }
                BTN_respuesta1.setEnabled(false);
                BTN_respuesta1.setClickable(false);
                BTN_respuesta2.setEnabled(false);
                BTN_respuesta2.setClickable(false);
                BTN_respuesta3.setEnabled(false);
                BTN_respuesta3.setClickable(false);
                BTN_respuesta4.setEnabled(false);
                BTN_respuesta4.setClickable(false);
            }
        });

        BTN_respuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BTN_respuesta3.getText().equals(respuestaCorrecta) && boton == false){
                    aciertos++;
                    BTN_respuesta3.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                } else if (BTN_respuesta2.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta3.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta2.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                } else if (BTN_respuesta1.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta3.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta1.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                } else if (BTN_respuesta4.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta3.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta4.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                }
                BTN_respuesta1.setEnabled(false);
                BTN_respuesta1.setClickable(false);
                BTN_respuesta2.setEnabled(false);
                BTN_respuesta2.setClickable(false);
                BTN_respuesta3.setEnabled(false);
                BTN_respuesta3.setClickable(false);
                BTN_respuesta4.setEnabled(false);
                BTN_respuesta4.setClickable(false);
            }
        });

        BTN_respuesta4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BTN_respuesta4.getText().equals(respuestaCorrecta) && boton == false){
                    aciertos++;
                    BTN_respuesta4.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    //Toast.makeText(Test.this, "+1 punto. Resultado: " + puntuacion, Toast.LENGTH_SHORT).show();
                    boton = true;
                } else if (BTN_respuesta2.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta4.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta2.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    //Toast.makeText(Test.this, "-0.33 punto." + respuestaCorrecta + " Resultado: " + puntuacion, Toast.LENGTH_SHORT).show();
                    boton = true;
                } else if (BTN_respuesta3.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta4.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta3.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                } else if (BTN_respuesta1.getText().equals(respuestaCorrecta) && boton == false){
                    fallos++;
                    BTN_respuesta4.setBackgroundColor(getResources().getColor(R.color.rojoFallo));
                    BTN_respuesta1.setBackgroundColor(getResources().getColor(R.color.verdeAcierto));
                    boton = true;
                }
                BTN_respuesta1.setEnabled(false);
                BTN_respuesta1.setClickable(false);
                BTN_respuesta2.setEnabled(false);
                BTN_respuesta2.setClickable(false);
                BTN_respuesta3.setEnabled(false);
                BTN_respuesta3.setClickable(false);
                BTN_respuesta4.setEnabled(false);
                BTN_respuesta4.setClickable(false);
            }
        });

    }
}


