package com.example.david.imosso;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuResumenesPdfActivity extends AppCompatActivity {
    String tituloA1="", tituloA2="", tituloA3="", tituloA4="", tituloA5="", tituloA6="", tituloA7="";
    String tituloB1="", tituloB2="", tituloB3="", tituloB4="", tituloB5="", tituloB6="", tituloB7="", tituloB8="";
    String tituloC1="", tituloC2="", tituloC3="", tituloC4="", tituloC5="";
    ExpandableListView listView;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String,List<String>> listHash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_resumenes_pdf);
        //SIDEBAR TRANSPARENT
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        listView = (ExpandableListView)findViewById(R.id.lvExp);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader, listHash);
        listView.setAdapter(listAdapter);
        Click_group();
        Click_child();
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Ámbito A: \n" +
                "Conocimientos del entorno");
        listDataHeader.add("Ámbito B: \n" +
                "Institucional");
        listDataHeader.add("Ámbito C: \n" +
                "Seguridad y policía");

        List<String> categoriaA = new ArrayList<>();
        tituloA1 = "1)Historia de Cataluña (parte I)";
        categoriaA.add(tituloA1);
        tituloA2 = "2)Historia de Cataluña (parte II)";
        categoriaA.add(tituloA2);
        tituloA3 = "3)Historia de la policía en Cataluña";
        categoriaA.add(tituloA3);
        tituloA4 = "4)Ámbito sociolingüístico";
        categoriaA.add(tituloA4);
        tituloA5 = "5)Marco geográfico de Cataluña";
        categoriaA.add(tituloA5);
        tituloA6 = "6)Entorno social en Cataluña";
        categoriaA.add(tituloA6);
        tituloA7 = "7)Tecnologías de la información en el s. XXI";
        categoriaA.add(tituloA7);

        List<String> categoriaB = new ArrayList<>();
        tituloB1 = "1)El Estatuto de autonomía de Cataluña (EAC)";
        categoriaB.add(tituloB1);
        tituloB2 = "2)Las instituciones políticas de Cataluña";
        categoriaB.add(tituloB2);
        tituloB3 = "3)El ordenamiento jurídico del Estado";
        categoriaB.add(tituloB3);
        tituloB4 = "4)Los derechos humanos y los derechos constitucionales";
        categoriaB.add(tituloB4);
        tituloB5 = "5)Las instituciones políticas del Estado";
        categoriaB.add(tituloB5);
        tituloB6 = "6)Los órganos jurisdiccionales. Poder judicial y Tribunal Constitucional";
        categoriaB.add(tituloB6);
        tituloB7 = "7)La organización territorial del Estado";
        categoriaB.add(tituloB7);
        tituloB8 = "8)La Unión Europea";
        categoriaB.add(tituloB8);

        List<String> categoriaC = new ArrayList<>();
        tituloC1 = "1)Las competencias de la Generalidad en materia de seguridad";
        categoriaC.add(tituloC1);
        tituloC2 = "2)El Departamento de Interior";
        categoriaC.add(tituloC2);
        tituloC3 = "3)La coordinación policial";
        categoriaC.add(tituloC3);
        tituloC4 = "4)El marco legal de la seguridad";
        categoriaC.add(tituloC4);
        tituloC5 = "5)El código deontológico policial";
        categoriaC.add(tituloC5);

        listHash.put(listDataHeader.get(0),categoriaA);
        listHash.put(listDataHeader.get(1),categoriaB);
        listHash.put(listDataHeader.get(2),categoriaC);

    }

    private void Click_group(){
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int groupPosition, long l) {
                //Toast.makeText(TestMenu.this, listDataHeader.get(groupPosition), Toast.LENGTH_LONG).show();
                return false;
            };
        });
    }

    private void Click_child(){
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                if(listHash.get(listDataHeader.get(groupPosition)).get(childPosition)==tituloA1){
                    existeArchivo("demo.pdf");
                } else if(listHash.get(listDataHeader.get(groupPosition)).get(childPosition)==tituloA2){
                    existeArchivo("demo1.pdf");
                } else if(listHash.get(listDataHeader.get(groupPosition)).get(childPosition)==tituloA3){
                    existeArchivo("demo2.pdf");
                }
                return false;
            }
        });
    }

    private void existeArchivo(String name){
        AssetManager mg = getResources().getAssets();
        InputStream is = null;
        try {
            try (InputStream inputStream = is = mg.open(name)) {
                //File exists so do something with it
                SharedPreferences prefs =
                        getSharedPreferences("Resumenes",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("resumen", name);
                editor.commit();
                Intent myIntent = new Intent(MenuResumenesPdfActivity.this,
                        pdf_resumen.class);
                startActivity(myIntent);
                finish(); //no deja echar atras cuando entremos en notaTest para que la nota no se pueda modificar
            }
        } catch (IOException ex) {
            //file does not exist
            Toast.makeText(MenuResumenesPdfActivity.this, "Resumen no disponible.", Toast.LENGTH_SHORT).show();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}