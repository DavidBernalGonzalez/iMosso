package com.example.david.imosso.entidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    final String CREAR_TABLA_RESULTADOSDB="CREATE TABLE resultadosDB (" +
            "idTest INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "fechaTest TEXT, nombreTest TEXT, " +
            "notaTest TEXT " +
            " )";


    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_RESULTADOSDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS resultadosDB");
        onCreate(db);
    }
}


