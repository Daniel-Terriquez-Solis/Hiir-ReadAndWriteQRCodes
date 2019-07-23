package com.varvet.barcodereadersample;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpPOSTRequestServicio {
    private String URLPost;
    private HashMap<String, String> parametros;

    /**
     *
     * @param URLPost La url del método del servicio, las urls se encuentran en ServiceUtils
     * @param parametros un mapa con el nombre del campo como 'key' y el valor como 'value'
     */
    public HttpPOSTRequestServicio(String URLPost, HashMap<String, String> parametros) {
        this.URLPost = URLPost;
        this.parametros = parametros;
    }

    /**
     * NO EJECUTARSE EN HILO PRIMARIO, SOLO ASINCRONO
     * @return JSONArray con la respuesta de la consulta.
     */
    public JSONObject makeRequest() {
        JSONObject resultado;
        String respuesta = "";

        try {
            URL url = new URL(URLPost);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //Escribiendo
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(toPostDataString(parametros));

            writer.flush();
            writer.close();
            os.close();

            //Leyendo respuesta
            int responseCode = conn.getResponseCode();

            if(responseCode == HttpsURLConnection.HTTP_OK){
                String linea;

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                while ((linea = br.readLine()) != null) {
                    respuesta+=linea;
                }

            } else {
                respuesta = "";
            }



        } catch (Exception e){
            e.printStackTrace();
        }

        //Conviertiendo a JSONArray
        if(!respuesta.equals("")){
            try {
                resultado = new JSONObject(respuesta);
                return resultado;
            } catch (JSONException je){
                je.printStackTrace();

                return null;
            }
        } else {
            return null;
        }


    }

    /**
     * Convierte un juego de parametros en un HashMap<K,V> donde <parametro, valor>
     * @param params HashMap con los parametros para la consulta
     * @return String con los parametros en formato post
     */
    private String toPostDataString(HashMap<String, String> params) {
        StringBuilder result = new StringBuilder();
        boolean primero = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (primero)
                primero = false;
            else
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        return result.toString();
    }

}