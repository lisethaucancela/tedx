/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.controlador;

import ec.edu.espoch.entidades.CPeriodo;
import ec.edu.espoch.modelo.MPeriodo;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import recursos.Util;

/**
 *
 * @author William
 */
@ManagedBean
@ViewScoped
public class PeriodoControlador {

    private CPeriodo objPeriodo;
    private CPeriodo selPeriodo;
    private ArrayList<CPeriodo> lstPeriodo;

    public PeriodoControlador() {
        this.objPeriodo = new CPeriodo();
        this.selPeriodo = new CPeriodo();
        this.lstPeriodo = new ArrayList<CPeriodo>();
    }

//<editor-fold defaultstate="collapsed" desc="Métodos Get y Set">
    public CPeriodo getObjPeriodo() {
        return objPeriodo;
    }

    public void setObjPeriodo(CPeriodo objPeriodo) {
        this.objPeriodo = objPeriodo;
    }

    public CPeriodo getSelPeriodo() {
        return selPeriodo;
    }

    public void setSelPeriodo(CPeriodo selPeriodo) {
        this.selPeriodo = selPeriodo;
    }

    public ArrayList<CPeriodo> getLstPeriodo() {
        return lstPeriodo;
    }

    public void setLstPeriodo(ArrayList<CPeriodo> lstPeriodo) {
        this.lstPeriodo = lstPeriodo;
    }
    //</editor-fold>

    @PostConstruct
    public void reinit() {
        cargarPeriodo();
    }

    public void cargarPeriodo() {
        try {
            lstPeriodo = MPeriodo.obtenerPeriodo();
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
    }

    public void insertarPeriodo() {
        try {
            if (MPeriodo.insertarPeriodo(objPeriodo)) {
                Util.addSuccessMessage("Successful", "Datos Insertados");
                cargarPeriodo();
            } else {
                Util.addErrorMessage("Verifique que los datos sean correctos");
            }
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

    public void editarPeriodo() {
        try {
            if (MPeriodo.editarPeriodo(selPeriodo)) {
                Util.addSuccessMessage("Successful", "Datos Modificados");
                cargarPeriodo();
            } else {
                Util.mostrarMensaje("Verifique que los datos sean correctos");
            }
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }
 
    public void eliminarPeriodo() {
        try {
            if (MPeriodo.eliminarPeriodo(selPeriodo)) {
                Util.addSuccessMessage("Successful","Datos Eliminados");
                cargarPeriodo();
            } else {
                Util.mostrarMensaje("Verifique que los datos sean correctos");
            }
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
            Util.addErrorMessage(e.getMessage());
        }
    }

}
