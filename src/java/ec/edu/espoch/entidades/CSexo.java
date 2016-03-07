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
public class CSexo {
    private int tsexoId;
    private String tsexoDescripcion;

    public CSexo() {
    }

    public CSexo(int tsexoId, String tsexoDescripcion) {
        this.tsexoId = tsexoId;
        this.tsexoDescripcion = tsexoDescripcion;
    }

    public int getTsexoId() {
        return tsexoId;
    }

    public void setTsexoId(int tsexoId) {
        this.tsexoId = tsexoId;
    }

    public String getTsexoDescripcion() {
        return tsexoDescripcion;
    }

    public void setTsexoDescripcion(String tsexoDescripcion) {
        this.tsexoDescripcion = tsexoDescripcion;
    }
    
    
    
}
