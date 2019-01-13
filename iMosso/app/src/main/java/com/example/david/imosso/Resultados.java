package com.example.david.imosso;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.david.imosso.entidades.ConexionSQLiteHelper;

import java.util.ArrayList;

public class Resultados extends AppCompatActivity {
    private int contentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        //SIDEBAR TRANSPARENT
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        //hacemos la conexion
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getApplicationContext(), "resultadosDB", null, 1);
        //referenciamos el elemento listviewresultados
        ListView listViewResultados = (ListView) findViewById(R.id.listViewResultados);
        //Contiene la información que se a mostrar
        ArrayList<String> listaResultados = new ArrayList<>();
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM resultadosDB", null);
        if(data.getCount() == 0){
            listaResultados.add("No existen resultados. Haz algún test!");
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaResultados);
            listViewResultados.setAdapter(listAdapter);
        } else{
            while(data.moveToNext()){
                String fecha = data.getString(1);
                String nombre = data.getString(2);
                String nota = data.getString(3);
                listaResultados.add("Fecha: " + fecha + "\t\t\tNota: " + nota + "\nTest:" + nombre);
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaResultados); //simple_list_item_1
                listViewResultados.setAdapter(listAdapter);
            }
        }
    }
}
