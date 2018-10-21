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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static com.example.david.imosso.TestMenu.MyPREFERENCES;

public class Test extends AppCompatActivity {
    int increment = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button BTN_next=(Button)findViewById(R.id.BTN_next);
        BTN_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJSONFromAsset(+1);
                increment++;
            }
        });
        showJSONFromAsset(0);
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
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray test = obj.getJSONArray("test" + 1);
            for (int i = 0; i < test.length(); i++) {
                JSONObject jsonObject = test.getJSONObject(increment);//i
                String pregunta = jsonObject.getString("pregunta");
                String respuesta1 = jsonObject.getString("respuesta1");
                String respuesta2 = jsonObject.getString("respuesta2");
                String respuesta3 = jsonObject.getString("respuesta3");
                String respuesta4 = jsonObject.getString("respuesta4");
                String respuestaCorrecta = jsonObject.getString("respuestaCorrecta");
                TextView TV_pregunta = (TextView) findViewById(R.id.TV_pregunta);
                TV_pregunta.setText(pregunta);
                TextView TV_num = (TextView) findViewById(R.id.TV_num);
                TV_num.setText("Pregunta: " + (increment + 1) + "/" + test.length());
                Button BTN_respuesta1 = (Button) findViewById(R.id.BTN_respuesta1);
                BTN_respuesta1.setText(respuesta1.toString());
                Button BTN_respuesta2 = (Button) findViewById(R.id.BTN_respuesta2);
                BTN_respuesta2.setText(respuesta2.toString());
                Button BTN_respuesta3 = (Button) findViewById(R.id.BTN_respuesta3);
                BTN_respuesta3.setText(respuesta3.toString());
                Button BTN_respuesta4 = (Button) findViewById(R.id.BTN_respuesta4);
                BTN_respuesta4.setText(respuesta4.toString());
            }
        }catch( JSONException e) {
            e.printStackTrace();
        }
    }
}


