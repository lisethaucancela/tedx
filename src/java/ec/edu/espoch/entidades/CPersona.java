/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.entidades;

/**
 *
 * @author William
 */
public class CPersona {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String titulo;
    private String email;
    private CSexo  objSexo;

    public CPersona() {
    }

    public CPersona(int id, String cedula, String nombre, String apellido, String titulo, String email, CSexo objSexo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.email = email;
        this.objSexo = objSexo;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CSexo getObjSexo() {
        return objSexo;
    }

    public void setObjSexo(CSexo objSexo) {
        this.objSexo = objSexo;
    }
    
    
}
