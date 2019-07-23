package com.varvet.barcodereadersample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpGETRequestServicio {
    private String URLGet;

    /**
     *
     * @param URLGet URL para la método de consulta tipo GET, si este requiere parametros
     *               deben ir ya concatendos con la URL
     */
    public HttpGETRequestServicio(String URLGet) {
        this.URLGet = URLGet;
    }

    /**
     * Hace una consulta tipo GET y devuelve el resultado en un JSONObject
     * @return JSONObject con el contenido resultado de la consulta
     */
    public JSONObject makeRequestForObject() {
        JSONObject resultado;
        String respuesta = "";
        BufferedReader reader = null;

        try {

            URL url = new URL(URLGet);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);


            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String linea;

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((linea = br.readLine()) != null) {
                    respuesta += linea;
                }
            } else {
                respuesta = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!respuesta.equals("")) {
            try {
                resultado = new JSONObject(respuesta);
                return resultado;
            } catch (JSONException je) {
                je.printStackTrace();

                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Hace una consulta tipo GET y lo devuelve en un JSONArray
     * (Solo usar si sabe que el resultado será un arreglo en json)
     * @return JSONArray con la lista de resultados
     */
    public JSONArray makeRequestForArray() {
        JSONArray resultado;
        String respuesta = "";
        BufferedReader reader = null;

        try {

            URL url = new URL(URLGet);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);


            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String linea;

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((linea = br.readLine()) != null) {
                    respuesta += linea;
                }
            } else {
                respuesta = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!respuesta.equals("")) {
            try {
                resultado = new JSONArray(respuesta);
                return resultado;
            } catch (JSONException je) {
                je.printStackTrace();

                return null;
            }
        } else {
            return null;
        }
    }

}