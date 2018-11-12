package com.example.david.imosso;
import android.content.SharedPreferences;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Locale;

import static java.lang.Math.round;

public class notaTest extends AppCompatActivity {
    String notaTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_test);
        //SIDEBAR TRANSPARENT
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        TextView TV_notaTest = (TextView) findViewById(R.id.TV_notaTest);
        String valoracion = null;
        TextView TV_resultadosPreguntas = (TextView) findViewById(R.id.TV_resultadosPreguntas);
        TextView TV_notaEscrita = (TextView) findViewById(R.id.TV_notaEscrita);
        try {
            SharedPreferences prefsnota =
                    getSharedPreferences("Nota", Context.MODE_PRIVATE);
            int aciertos = prefsnota.getInt("aciertos", 0);
            int fallos = prefsnota.getInt("fallos", 0);
            int longitudTest = prefsnota.getInt("longitudTest", 0);
            double nota = ((((aciertos * 1) - (fallos * 0.33))*10)/longitudTest);
            String formattedValue;
            if(nota<=1 || nota<=-9.999){
                DecimalFormat f = new DecimalFormat("0.00");
                formattedValue = f.format(nota);
            } else{
                DecimalFormat f = new DecimalFormat("##.0");
                formattedValue = f.format(nota);
            }
            if(nota<5){
                valoracion = "No apto.\nInsuficiente";
            }else if(nota>=5 && nota<6){
                valoracion = "Apto.\nSuficiente";
            }else if(nota>=6 && nota<7){
                valoracion = "Apto.\nBien";
            }else if(nota>=7 && nota<=8){
                valoracion = "Apto.\nNotable";
            }else if(nota>8){
                valoracion = "Apto.\nExcelente";
            }
            String notaTxt = String.valueOf(formattedValue);
            TV_notaTest.setText(notaTxt);
            TV_notaEscrita.setText(valoracion);
            String resultMensajes = "Realizando " + aciertos + " aciertos, " + fallos + " fallos\ny " + (longitudTest-(aciertos+fallos)) + " no contestadas, sobre " + longitudTest + " preguntas";
            TV_resultadosPreguntas.setText(resultMensajes);
        } catch (Exception e){
            TV_notaTest.setText("Error");
        }
    }


}
