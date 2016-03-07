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
public class CAsistencia {
    private CPersona tpersonaCodigo;
    private boolean tasistencia;

    public CAsistencia() {
    }

    public CAsistencia(CPersona tpersonaCodigo, boolean tasistencia) {
        this.tpersonaCodigo = tpersonaCodigo;
        this.tasistencia = tasistencia;
    }

    public CPersona getTpersonaCodigo() {
        return tpersonaCodigo;
    }

    public void setTpersonaCodigo(CPersona tpersonaCodigo) {
        this.tpersonaCodigo = tpersonaCodigo;
    }

    public boolean isTasistencia() {
        return tasistencia;
    }

    public void setTasistencia(boolean tasistencia) {
        this.tasistencia = tasistencia;
    }
  
}
