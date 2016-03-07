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
public class CPersonaPeriodo {
    private int tpersonaperiodoid; 
   private int tpersonaid; 
   private int tperiodoid; 
   private String tpersonaperiodocomentario;
  private String tpersonaperiodofechainscripcion; 
  private String tpersonaperiodopensamiento; 
  private int tpersonaperiodoestado;
private boolean blnElegido;
    private CUsuario objPersona;
   

    public CPersonaPeriodo() {
    }

    public CPersonaPeriodo(int tpersonaid) {
        this.tpersonaid = tpersonaid;
        
    }

    public CPersonaPeriodo(int tpersonaperiodoid, int tpersonaid, int tperiodoid, String tpersonaperiodocomentario, String tpersonaperiodofechainscripcion, String tpersonaperiodopensamiento, int tpersonaperiodoestado) {
        this.tpersonaperiodoid = tpersonaperiodoid;
        this.tpersonaid = tpersonaid;
        this.tperiodoid = tperiodoid;
        this.tpersonaperiodocomentario = tpersonaperiodocomentario;
        this.tpersonaperiodofechainscripcion = tpersonaperiodofechainscripcion;
        this.tpersonaperiodopensamiento = tpersonaperiodopensamiento;
        this.tpersonaperiodoestado = tpersonaperiodoestado;
    }

     public CPersonaPeriodo(int id,CUsuario objPerson, String tpersonaperiodocomentario, String tpersonaperiodofechainscripcion, String tpersonaperiodopensamiento) {
        this.tpersonaperiodoid = id;
        this.objPersona = objPerson;
        this.tpersonaperiodocomentario = tpersonaperiodocomentario;
        this.tpersonaperiodofechainscripcion = tpersonaperiodofechainscripcion;
        this.tpersonaperiodopensamiento = tpersonaperiodopensamiento;
        this.blnElegido = false;
    }
    public String getTpersonaperiodopensamiento() {
        return tpersonaperiodopensamiento;
    }

    public void setTpersonaperiodopensamiento(String tpersonaperiodopensamiento) {
        this.tpersonaperiodopensamiento = tpersonaperiodopensamiento;
    }

    public boolean isBlnElegido() {
        return blnElegido;
    }

    public void setBlnElegido(boolean blnElegido) {
        this.blnElegido = blnElegido;
    }

    public CUsuario getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(CUsuario objPersona) {
        this.objPersona = objPersona;
    }

    
    
    
    

    public int getTpersonaperiodoid() {
        return tpersonaperiodoid;
    }

    public void setTpersonaperiodoid(int tpersonaperiodoid) {
        this.tpersonaperiodoid = tpersonaperiodoid;
    }

    public int getTpersonaid() {
        return tpersonaid;
    }

    public void setTpersonaid(int tpersonaid) {
        this.tpersonaid = tpersonaid;
    }

    public int getTperiodoid() {
        return tperiodoid;
    }

    public void setTperiodoid(int tperiodoid) {
        this.tperiodoid = tperiodoid;
    }

    public String getTpersonaperiodocomentario() {
        return tpersonaperiodocomentario;
    }

    public void setTpersonaperiodocomentario(String tpersonaperiodocomentario) {
        this.tpersonaperiodocomentario = tpersonaperiodocomentario;
    }

    public String getTpersonaperiodofechainscripcion() {
        return tpersonaperiodofechainscripcion;
    }

    public void setTpersonaperiodofechainscripcion(String tpersonaperiodofechainscripcion) {
        this.tpersonaperiodofechainscripcion = tpersonaperiodofechainscripcion;
    }

    public int getTpersonaperiodoestado() {
        return tpersonaperiodoestado;
    }

    public void setTpersonaperiodoestado(int tpersonaperiodoestado) {
        this.tpersonaperiodoestado = tpersonaperiodoestado;
    }
  
    
}
