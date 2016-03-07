/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.entidades;

import java.util.Date;

/**
 *
 * @author William
 */
public class CTransaccion {

    private int ttransaccionid;
    private String ttransaccioncedula;
    private Date ttransaccionfecha;
    private String ttransacciontipo;
    private String ttransaccionobjeto;

    public CTransaccion() {
    }

    public CTransaccion(String ttransaccioncedula, String ttransacciontipo, String ttransaccionobjeto) {
        this.ttransaccioncedula = ttransaccioncedula;
        this.ttransacciontipo = ttransacciontipo;
        this.ttransaccionobjeto = ttransaccionobjeto;
    }

    public int getTtransaccionid() {
        return ttransaccionid;
    }

    public void setTtransaccionid(int ttransaccionid) {
        this.ttransaccionid = ttransaccionid;
    }

    public String getTtransaccioncedula() {
        return ttransaccioncedula;
    }

    public void setTtransaccioncedula(String ttransaccioncedula) {
        this.ttransaccioncedula = ttransaccioncedula;
    }

    public Date getTtransaccionfecha() {
        return ttransaccionfecha;
    }

    public void setTtransaccionfecha(Date ttransaccionfecha) {
        this.ttransaccionfecha = ttransaccionfecha;
    }

    public String getTtransacciontipo() {
        return ttransacciontipo;
    }

    public void setTtransacciontipo(String ttransacciontipo) {
        this.ttransacciontipo = ttransacciontipo;
    }

    public String getTtransaccionobjeto() {
        return ttransaccionobjeto;
    }

    public void setTtransaccionobjeto(String ttransaccionobjeto) {
        this.ttransaccionobjeto = ttransaccionobjeto;
    }

}
