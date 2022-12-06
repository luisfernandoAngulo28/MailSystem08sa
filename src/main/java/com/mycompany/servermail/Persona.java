package com.mycompany.servermail;

/**
 *
 * @author daniel
 */
public class Persona {

    // INSERT INTO PERSONA VALUES(4568555,'fernando','heredia angulo','auxiliar',3898823,76688541,'luis.fe671@gmail.com'); 
    private int ci;
    private String nombre;
    private String apellido;
    private String profesion;
    private int telefono;
    private int celular;
    private String correo;

    public Persona(int ci, String nombre, String apellido, String profesion, int telefono, int celular, String correo) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.profesion = profesion;
        this.telefono = telefono;
        this.celular = celular;
        this.correo = correo;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
