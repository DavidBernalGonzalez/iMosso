package com.example.david.imosso;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.*;

public class TestMenu extends AppCompatActivity {
    String titulo1="";
    ExpandableListView listView;
    ExpandableListAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String,List<String>> listHash;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_menu);

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
        titulo1 = "1)Historia de Cataluña (parte I)";
        categoriaA.add(titulo1);
        categoriaA.add("2)Historia de Cataluña (parte II)");
        categoriaA.add("3)Historia de la policía en Cataluña");
        categoriaA.add("4)Ámbito sociolingüístico");
        categoriaA.add("5)Marco geográfico de Cataluña");
        categoriaA.add("6)Entorno social en Cataluña");
        categoriaA.add("7)Tecnologías de la información en el s. XXI");

        List<String> categoriaB = new ArrayList<>();
        categoriaB.add("Expandable ListView");
        categoriaB.add("Google Map");
        categoriaB.add("Chat Application");
        categoriaB.add("Firebase ");

        List<String> categoriaC = new ArrayList<>();
        categoriaC.add("Xamarin Expandable ListView");
        categoriaC.add("Xamarin Google Map");
        categoriaC.add("Xamarin Chat Application");
        categoriaC.add("Xamarin Firebase ");

        List<String> uwp = new ArrayList<>();
        uwp.add("UWP Expandable ListView");
        uwp.add("UWP Google Map");
        uwp.add("UWP Chat Application");
        uwp.add("UWP Firebase ");

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
                //Toast.makeText(TestMenu.this, listHash.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                //Toast.makeText(TestMenu.this, listHash.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                //if the position its = titulo 1 go to...
                if(listHash.get(listDataHeader.get(groupPosition)).get(childPosition)==titulo1){
                    Intent myIntent = new Intent(TestMenu.this,
                            Test.class);
                    startActivity(myIntent);
                } else if(listHash.get(listDataHeader.get(groupPosition)).get(childPosition)!=titulo1){
                    Intent myIntent = new Intent(TestMenu.this,
                            Test.class);
                    startActivity(myIntent);
                }
                return false;
            }
        });
    }
}
