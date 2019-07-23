package com.varvet.barcodereadersample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import com.varvet.barcodereadersample.ServiceUtils;

public class MaestroPersistence {

    /**
     * @return Una lista tipo Usuario con todos los registros de la base
     */
    public static ArrayList<Usuario> obtenerTodos() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        HttpGETRequestServicio getRequest = new HttpGETRequestServicio(ServiceUtils.maestros);
        JSONArray respuesta = getRequest.makeRequestForArray();

        if (respuesta != null) {
            for (int i = 0; i < respuesta.length(); i++) {
                try {
                    JSONObject object = (JSONObject) respuesta.get(i);
                    Usuario u = new Usuario();

                    u.setId(Integer.parseInt(object.getString("id")));
                    u.setNombre(object.getString("nombre") + " "
                            + object.getString("primer_apellido") + " "
                            + object.getString("segundo_apellido"));
                    u.setCorreo(object.getString("correo"));
                    u.setTelefono(object.getString("telefono"));
                    u.setContrasena(object.getString("contrasena"));

                    usuarios.add(u);
                } catch (JSONException je) {
                    je.printStackTrace();
                    usuarios = null;
                }
            }

            return usuarios;
        } else {
            return null;
        }

    }

    /**
     * @param id El id del maestro a buscar
     * @return Un objeto usuario correspondiente al maestro
     */
    public static Usuario obtenerPorId(int id) {
        HttpGETRequestServicio getRequest = new HttpGETRequestServicio(ServiceUtils.maestrosID + id);
        JSONObject respuesta = getRequest.makeRequestForObject();

        Usuario u = new Usuario();

        if (respuesta != null) {
            try {

                u.setId(Integer.parseInt(respuesta.getString("id")));
                u.setNombre(respuesta.getString("nombre") + " "
                        + respuesta.getString("primer_apellido") + " "
                        + respuesta.getString("segundo_apellido"));
                u.setCorreo(respuesta.getString("correo"));
                u.setTelefono(respuesta.getString("telefono"));
                u.setContrasena(respuesta.getString("contrasena"));

            } catch (JSONException je) {
                je.printStackTrace();

                u = null;
            }

            return u;
        } else {
            return null;
        }
    }

    /**
     * @param id Id del maestro
     * @return Clases del maestro en una lista tipo Clase
     */
    public static ArrayList<Clase> obtenerClases(int id) {
        ArrayList<Clase> clases = new ArrayList<>();

        HttpGETRequestServicio getRequest = new HttpGETRequestServicio(ServiceUtils.maestrosClases + id);
        JSONArray respuesta = getRequest.makeRequestForArray();

        if (respuesta != null) {
            for (int i = 0; i < respuesta.length(); i++) {
                try {
                    JSONObject object = (JSONObject) respuesta.get(i);
                    Clase c = new Clase();

                    c.setId(Integer.parseInt(object.getString("id")));
                    c.setName(object.getString("nombre"));
                    c.setAula(object.getString("aula"));
                    c.setCarrera(object.getString("carrera"));
                    c.setDias(object.getString("dias"));
                    String hora = object.getString("hora_inicio").split(":")[0];
                    int start = Integer.parseInt(hora);
                    c.setHourStart(start);
                    int end = start + 1;
                    c.setHourEnd(end);

                    clases.add(c);
                } catch (JSONException je) {
                    je.printStackTrace();
                    clases = null;
                }
            }

            return clases;
        } else {
            return null;
        }

    }

    /**
     * @param telefono   El telefono del maestro
     * @param contrasena La contraseña del maestro
     * @return Un objeto usuario correspondiente al maestro
     */
    public static Usuario login(String telefono, String contrasena) {
        HashMap<String, String> parametros = new HashMap<>();
        parametros.put("telefono", telefono.trim());
        parametros.put("contrasena", contrasena.trim());

        HttpPOSTRequestServicio postRequest = new HttpPOSTRequestServicio(ServiceUtils.maestroLogin, parametros);
        JSONObject respuesta = postRequest.makeRequest();

        Usuario u = new Usuario();

        if (respuesta != null) {
            try {

                u.setId(Integer.parseInt(respuesta.getString("id")));
                u.setNombre(respuesta.getString("nombre") + " "
                        + respuesta.getString("primer_apellido") + " "
                        + respuesta.getString("segundo_apellido"));
                u.setCorreo(respuesta.getString("correo"));
                u.setTelefono(respuesta.getString("telefono"));
                u.setContrasena(respuesta.getString("contrasena"));

                return u;
            } catch (JSONException je) {
                je.printStackTrace();

                return null;
            }
        } else {
            return null;
        }

    }

    /**
     *
     * @param idMaestro El maestro que imparte
     * @param idClase La clase impartida
     * @return codigo de la lista de asistencia
     */
    public static int generarLista(int idMaestro, int idClase){
        int idLista = 0;

        HashMap<String, String> parametros = new HashMap<>();
        parametros.put("id_maestro", String.valueOf(idMaestro));
        parametros.put("id_clase", String.valueOf(idClase));

        HttpPOSTRequestServicio postRequest = new HttpPOSTRequestServicio(ServiceUtils.claseAsistenciaMaestro, parametros);
        JSONObject respuesta = postRequest.makeRequest();

        if(respuesta != null){
            try {

                idLista = Integer.parseInt(respuesta.getString("id"));

            } catch(JSONException je){
                je.printStackTrace();

                idLista = -1;
            }

            return idLista;
        } else {
            return -1;
        }

    }

}
