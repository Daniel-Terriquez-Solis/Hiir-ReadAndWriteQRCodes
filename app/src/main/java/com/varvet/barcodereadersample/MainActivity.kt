package com.varvet.barcodereadersample

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import com.varvet.barcodereadersample.barcode.BarcodeCaptureActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mResultTextView: TextView
    private lateinit var mTextoResultado: TextView
    var alumno:Usuario = Usuario()
    var id_alumno:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alumno = intent.getSerializableExtra("alumno") as Usuario

        mResultTextView = findViewById(R.id.result_textview)
        mTextoResultado = findViewById(R.id.idtxtResultado)

        findViewById<Button>(R.id.scan_barcode_button).setOnClickListener {
            val intent = Intent(applicationContext, BarcodeCaptureActivity::class.java)
            startActivityForResult(intent, BARCODE_READER_REQUEST_CODE)
        }

        mResultTextView.setText(alumno.getNombre())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == BARCODE_READER_REQUEST_CODE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    val barcode = data.getParcelableExtra<Barcode>(BarcodeCaptureActivity.BarcodeObject)
                    val p = barcode.cornerPoints
                    mTextoResultado.setText(barcode.displayValue)
                    mResultTextView.text = "Bienvenido "+alumno.getNombre()+", se ha registrado con exito la asistencia"
                    val ba:RegistrarAsistencia = RegistrarAsistencia()
                    ba.execute(alumno.getId(), barcode.displayValue.toInt())
                } else
                    mResultTextView.setText(R.string.no_barcode_captured)
            } else
                Log.e(LOG_TAG, String.format(getString(R.string.barcode_error_format),
                        CommonStatusCodes.getStatusCodeString(resultCode)))
        } else
            super.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        private val LOG_TAG = MainActivity::class.java.simpleName
        private val BARCODE_READER_REQUEST_CODE = 1
    }

    class RegistrarAsistencia() : AsyncTask<Int, Void, Boolean>() {
        override fun doInBackground(vararg params: Int?): Boolean? {
            val persistencia:Persistencia = Persistencia()
            val id_user:Int = params[0].toString().toInt()
            val id_clase:Int = params[1].toString().toInt()
            return persistencia.registrarAsistencia(id_user, id_clase)
        }

        override fun onPreExecute() {
            super.onPreExecute()
            // ...
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            // ...
            if (result==false){

            }
        }
    }
}
