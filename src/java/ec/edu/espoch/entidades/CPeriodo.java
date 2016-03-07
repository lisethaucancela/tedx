/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.entidades;

import java.util.Date;

public class CPeriodo {

    private int tperiodoid;
    private String tperiododescripcion;
    private Date tperiodofechainicio;
    private Date tperiodofechafin;
    private int tperiodomaxparticipantes;
    private boolean tperiodoestado;

    public CPeriodo() {
    }

   
    public CPeriodo(int tperiodoid, String tperiododescripcion, Date tperiodofechainicio, Date tperiodofechafin, int tperiodomaxparticipantes, boolean tperiodoestado) {
        this.tperiodoid = tperiodoid;
        this.tperiododescripcion = tperiododescripcion;
        this.tperiodofechainicio = tperiodofechainicio;
        this.tperiodofechafin = tperiodofechafin;
        this.tperiodomaxparticipantes = tperiodomaxparticipantes;
        this.tperiodoestado = tperiodoestado;
    }

   
    public int getTperiodoid() {
        return tperiodoid;
    }

    public void setTperiodoid(int tperiodoid) {
        this.tperiodoid = tperiodoid;
    }

    public String getTperiododescripcion() {
        return tperiododescripcion;
    }

    public void setTperiododescripcion(String tperiododescripcion) {
        this.tperiododescripcion = tperiododescripcion;
    }

    public Date getTperiodofechainicio() {
        return tperiodofechainicio;
    }

    public void setTperiodofechainicio(Date tperiodofechainicio) {
        this.tperiodofechainicio = tperiodofechainicio;
    }

    public Date getTperiodofechafin() {
        return tperiodofechafin;
    }

    public void setTperiodofechafin(Date tperiodofechafin) {
        this.tperiodofechafin = tperiodofechafin;
    }

    public boolean isTperiodoestado() {
        return tperiodoestado;
    }

    public void setTperiodoestado(boolean tperiodoestado) {
        this.tperiodoestado = tperiodoestado;
    }

    public int getTperiodomaxparticipantes() {
        return tperiodomaxparticipantes;
    }

    public void setTperiodomaxparticipantes(int tperiodomaxparticipantes) {
        this.tperiodomaxparticipantes = tperiodomaxparticipantes;
    }

}
