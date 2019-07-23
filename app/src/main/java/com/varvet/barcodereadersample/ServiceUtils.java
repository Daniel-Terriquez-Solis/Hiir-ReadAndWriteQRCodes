package com.varvet.barcodereadersample;

public class ServiceUtils {
    public static final String serviceURL = "http://asistenciapp.pitalla.mx";

    //GET
    public static final String maestros = serviceURL + "/maestros";
    public static final String maestrosID = serviceURL + "/maestros/";
    public static final String alumnos = serviceURL + "/alumnos";
    public static final String alumnosID = serviceURL + "/alumnos/";
    public static final String clases = serviceURL + "/clases";
    public static final String clasesID = serviceURL + "/clases/";
    public static final String alumnosClases = serviceURL + "/alumnos/clases/";
    public static final String maestrosClases = serviceURL + "/maestros/clases/";
    public static final String listaAlumnos = serviceURL + "/lista/alumnos/";

    //POST
    public static final String alumnoLogin = serviceURL + "/alumno/login";
    public static final String maestroLogin = serviceURL + "/maestro/login";
    public static final String claseAsistenciaMaestro = serviceURL + "/clase/asistencia/maestro";
    public static final String claseAsistenciaAlumno = serviceURL + "/clase/asistencia/alumno";


/*

[GET] /maestros -lista de maestros
[GET] /maestros/($id) - maestro por id
[GET] /clases -- lista de clases
[GET] /clases/($id) -- clase por id
[GET] /alumnos -- lista de alumnos
[GET] //alumnos/($id) --alumno por id
[GET] /alumnos /clases/($id_alumno) --clases del alumno por id de alumno
[GET] /maestros/clases/($id_maestro) --clases del maestro por id de maestro
[GET] /lista/alumnos/{id_lista} –> devuelve la lista de los alumnos que asistieron

[POST] /alumno/login/
args: (telefono, contrasena) –> json alumno -telefono tambien puede ser igual a id_itson

[POST] /maestro/login/
args: (telefono, contrasena) –> json alumno -telefono tambien puede ser igual a id_itson

[POST] /clase/asistencia/maestro
args: (id_maestro,id_clase) -> json resultado (id_lista)

[POST] /clase/asistencia/alumno
args: (id_lista, id_alumno) -> json resultado (true, false)


*/

}