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
public class CComentario {
    private int idcomentario;
    private String descripcioncomentario;
    private Integer idpersonacomentario;

    public CComentario() {
    }

    public CComentario(int idcomentario, String descripcioncomentario, Integer idpersonacomentario) {
        this.idcomentario = idcomentario;
        this.descripcioncomentario = descripcioncomentario;
        this.idpersonacomentario = idpersonacomentario;
    }

    public CComentario(Integer idpersonacomentario) {
        this.idpersonacomentario = idpersonacomentario;
    }

    public int getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }

    public String getDescripcioncomentario() {
        return descripcioncomentario;
    }

    public void setDescripcioncomentario(String descripcioncomentario) {
        this.descripcioncomentario = descripcioncomentario;
    }

    public Integer getIdpersonacomentario() {
        return idpersonacomentario;
    }

    public void setIdpersonacomentario(Integer idpersonacomentario) {
        this.idpersonacomentario = idpersonacomentario;
    }
    
}
