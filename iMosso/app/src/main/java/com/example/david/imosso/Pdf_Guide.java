package com.example.david.imosso;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.String;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Pdf_Guide extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_guide);
        //OPEN pdf from url
        pdfView = (PDFView) findViewById(R.id.pdfView);
        String url = "https://mossos.gencat.cat/web/.content/home/01_els_mossos_desquadra/ingresCos/Escala-bAsica/docs/Guia-definitiva-sencera.pdf";
        new DownloadPdf().execute(url);
        //OPEN PDF local
        // This is method from read PDF from Assets (local)
        //pdfView = (PDFView) findViewById(R.id.pdfView);
        //pdfView.fromAsset("Guia.pdf").load();
    }//end of on Create() method

    private class DownloadPdf extends AsyncTask<String ,Void,InputStream> {
        @Override        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL uri = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) uri.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return inputStream;
        }
        @Override        protected void onPostExecute(InputStream inputStream) {
            pdfView.fromStream(inputStream).load();
        }
    }//end downloadpdf class}//end of main Activivty*/
}
