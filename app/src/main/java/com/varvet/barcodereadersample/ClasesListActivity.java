package com.varvet.barcodereadersample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClasesListActivity extends AppCompatActivity {

    ListView lv;
    TextView txtNombreMaestro, txtClaseActual;
    Button btnQR;
    ImageView imgQR;

    Usuario maestro;
    Intent intent;
    Persistencia persistencia;
    List<Clase> clases;
    Clase claseActual;
    int currentHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clases_list);

        lv = (ListView) findViewById(R.id.idListView);
        txtNombreMaestro = (TextView) findViewById(R.id.idNombreMaestro);
        txtClaseActual = (TextView) findViewById(R.id.idClaseActual);
        imgQR = (ImageView) findViewById(R.id.idImageQR);

        persistencia = new Persistencia();
        intent = getIntent();
        maestro = (Usuario) intent.getSerializableExtra("maestro");
        ClasesMaestro cm = new ClasesMaestro();
        cm.execute(maestro.getId());

    }

    private class ClasesMaestro extends AsyncTask<Integer, Void, List<Clase>> {
        @Override
        protected List<Clase> doInBackground(Integer... id_maestro) {
            int id = Integer.parseInt(id_maestro[0].toString());
            return persistencia.obtenerClasesDeMaestro(id);
        }

        @Override
        protected void onPostExecute(List<Clase> clasesObtenidas) {
            txtNombreMaestro.setText(maestro.getNombre());
            Calendar calendar = Calendar.getInstance();
            currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            for(int i = 0; i<clasesObtenidas.size();i++){
                if (currentHour==clasesObtenidas.get(i).getHourStart()){
                    claseActual = clasesObtenidas.get(i);
                }
            }
            try {
                txtClaseActual.setText(claseActual.getName() + " " + claseActual.getHourStart() + ":00 - " + claseActual.getHourEnd() + ":00");
            }catch(NullPointerException npe){
                txtClaseActual.setText("No tiene clase a esta hora, se mostrara un codigo QR de su ultima clase");
                claseActual = clasesObtenidas.get(clasesObtenidas.size()-1);
            }

            BaseAdapter adapter = new AdapterClase(clasesObtenidas, ClasesListActivity.this);

            lv.setAdapter(adapter);

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(String.valueOf(claseActual.getId()), BarcodeFormat.QR_CODE,200,200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imgQR.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}