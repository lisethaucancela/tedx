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
public class CCuenta {
    private int idcuenta;
    private String nombreUsuario;
    private String contrasenia;
    private int tipousuarioId;
    private int personaId;
    private String fechacuenta;

    public CCuenta() {
    }

    public CCuenta(int idcuenta, String nombreUsuario, String contrasenia, int tipousuarioId, int personaId, String fechacuenta) {
        this.idcuenta = idcuenta;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.tipousuarioId = tipousuarioId;
        this.personaId = personaId;
        this.fechacuenta = fechacuenta;
    }

    public int getIdcuenta() {
        return idcuenta;
    }

    public void setIdcuenta(int idcuenta) {
        this.idcuenta = idcuenta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getTipousuarioId() {
        return tipousuarioId;
    }

    public void setTipousuarioId(int tipousuarioId) {
        this.tipousuarioId = tipousuarioId;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public String getFechacuenta() {
        return fechacuenta;
    }

    public void setFechacuenta(String fechacuenta) {
        this.fechacuenta = fechacuenta;
    }
    
    
}
