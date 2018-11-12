package com.example.david.imosso;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class pdf_resumen extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_resumen);
        SharedPreferences prefs =
                getSharedPreferences("Resumenes",Context.MODE_PRIVATE);
        String resumen = prefs.getString("resumen", null);
        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset(resumen).load();
    }
}
