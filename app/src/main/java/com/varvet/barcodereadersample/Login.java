package com.varvet.barcodereadersample;

import android.app.usage.NetworkStatsManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Login extends AppCompatActivity {

    EditText editId, editContrasena;
    CheckBox checkMaestro;
    Button btnLogin;

    Boolean esMaestro = false;

    Persistencia persistencia = new Persistencia();

    static Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editId = (EditText) findViewById(R.id.idID);
        editContrasena = (EditText) findViewById(R.id.idContrasena);
        checkMaestro = (CheckBox) findViewById(R.id.idEsMaestro);
        btnLogin = (Button) findViewById(R.id.idBtnLogin);

        //revisar si es maestro o alumno y redireccionar
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                esMaestro = checkMaestro.isChecked();
                if(esMaestro){
                    String a = editId.getText().toString();
                    String b = editContrasena.getText().toString();
                    LoginMaestro lm = new LoginMaestro();
                    lm.execute(a, b);

                }else{
                    LoginAlumno la = new LoginAlumno();
                    la.execute(editId.getText().toString(), editContrasena.getText().toString());
                }
            }
        });

    }

    private class LoginMaestro extends AsyncTask<String, Void, Usuario> {
        @Override
        protected Usuario doInBackground(String... datos_maestro) {
            return persistencia.loginMaestro(datos_maestro[0], datos_maestro[1]);
        }

        @Override
        protected void onPostExecute(Usuario user) {
            if(user == null){
                Toast.makeText(Login.this, "Error, el usuario no existe", Toast.LENGTH_SHORT);
            }else{
                Intent intent = new Intent(Login.this, ClasesListActivity.class);
                intent.putExtra("maestro", user);
                startActivity(intent);
            }
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private class LoginAlumno extends AsyncTask<String, Void, Usuario> {
        @Override
        protected Usuario doInBackground(String... datos_alumno) {
            return persistencia.loginAlumno(datos_alumno[0], datos_alumno[1]);
        }

        @Override
        protected void onPostExecute(Usuario user) {
            if(user == null){
                Toast.makeText(Login.this, "Error, el usuario no existe", Toast.LENGTH_SHORT);
            }else{
                Intent intent = new Intent(Login.this, MainActivity.class);
                intent.putExtra("alumno", user);
                startActivity(intent);
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
