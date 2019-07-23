package com.varvet.barcodereadersample;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class AlumnoPersistence {

    /**
     * @return Una lista tipo Usuario con todos los registros de la base
     */
    public static ArrayList<Usuario> obtenerTodos() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        HttpGETRequestServicio getRequest = new HttpGETRequestServicio(ServiceUtils.alumnos);
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
        HttpGETRequestServicio getRequest = new HttpGETRequestServicio(ServiceUtils.alumnosID + id);
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

        HttpGETRequestServicio getRequest = new HttpGETRequestServicio(ServiceUtils.alumnosClases + id);
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

        HttpPOSTRequestServicio postRequest = new HttpPOSTRequestServicio(ServiceUtils.alumnoLogin, parametros);
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

            } catch (JSONException je) {
                je.printStackTrace();

                u = null;
            }

            return u;
        } else {
            return null;
        }

    }


    public static boolean comprobarAsistencia(int idAlumno, int idLista){
        boolean presente = false;

        HashMap<String, String> parametros = new HashMap<>();
        parametros.put("id_lista", String.valueOf(idLista));
        parametros.put("id_alumno", String.valueOf(idAlumno));

        HttpPOSTRequestServicio postRequest = new HttpPOSTRequestServicio(ServiceUtils.claseAsistenciaAlumno, parametros);
        JSONObject respuesta = postRequest.makeRequest();

        if(respuesta != null){
            try {

                presente = respuesta.getString("estado").equals("presente");

            } catch(JSONException je){
                je.printStackTrace();

                presente = false;
            }

            return presente;
        } else {
            return false;
        }

    }

}