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
public class CSession {
    private String cedula;
    private String nombre;
    private String apellido;
    private String email;
    private String tipoUsuarioDescripcion;
    private String password;
    private String cuentaUsuario;
    private int id;
        
    public CSession() {
    }

    public CSession(String cedula, String nombre, String apellido, String email, String tipoUsuarioDescripcion, String password, String cuentaUsuario, int id) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.tipoUsuarioDescripcion = tipoUsuarioDescripcion;
        this.password = password;
        this.cuentaUsuario = cuentaUsuario;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuarioDescripcion() {
        return tipoUsuarioDescripcion;
    }

    public void setTipoUsuarioDescripcion(String tipoUsuarioDescripcion) {
        this.tipoUsuarioDescripcion = tipoUsuarioDescripcion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCuentaUsuario() {
        return cuentaUsuario;
    }

    public void setCuentaUsuario(String cuentaUsuario) {
        this.cuentaUsuario = cuentaUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
}
