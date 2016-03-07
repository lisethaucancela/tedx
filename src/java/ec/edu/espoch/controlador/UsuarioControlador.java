/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.controlador;

import com.sun.faces.facelets.util.Path;
import static com.sun.faces.facelets.util.Path.context;
import ec.edu.espoch.entidades.CPersonaPeriodo;
import ec.edu.espoch.entidades.CUsuario;
import ec.edu.espoch.modelo.MAsistencia;
import ec.edu.espoch.modelo.MUsuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import recursos.Util;
import recursos.Validaciones;

/**
 *
 * @author William
 */
@ManagedBean
@ViewScoped
public class UsuarioControlador {

    /**
     * Creates a new instance of UsuarioControlador
     */
    private CUsuario objUsuario;
    private CUsuario selObjtUsuario;
    private CPersonaPeriodo objPersonaPeriodo;
    private ArrayList<CUsuario> lstCUsuario;
    private String nombre;

    public UsuarioControlador() {
        this.objUsuario = new CUsuario();
        this.selObjtUsuario = new CUsuario();
        this.objPersonaPeriodo = new CPersonaPeriodo();
        this.lstCUsuario = new ArrayList<CUsuario>();
    }

    //<editor-fold defaultstate="collapsed" desc="Métodos Get y Set">
    public CUsuario getSelObjtUsuario() {
        return selObjtUsuario;
    }

    public void setSelObjtUsuario(CUsuario selObjtUsuario) {
        this.selObjtUsuario = selObjtUsuario;
    }

    public ArrayList<CUsuario> getLstCUsuario() {
        return lstCUsuario;
    }

    public void setLstCUsuario(ArrayList<CUsuario> lstCUsuario) {
        this.lstCUsuario = lstCUsuario;
    }

    public CPersonaPeriodo getObjPersonaPeriodo() {
        return objPersonaPeriodo;
    }

    public void setObjPersonaPeriodo(CPersonaPeriodo objPersonaPeriodo) {
        this.objPersonaPeriodo = objPersonaPeriodo;
    }

    public CUsuario getObjUsuario() {
        return objUsuario;
    }

    public void setObjUsuario(CUsuario objUsuario) {
        this.objUsuario = objUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Método Insertar Usuario">
    public void insertarUsuario() throws IOException {
        try {
            if (!MUsuario.existeCedula(objUsuario.getCedula())) {
                if (MUsuario.insertarUsuario(objUsuario) && MUsuario.insertarPersonaPeriodo(objPersonaPeriodo, objUsuario)) {
                    //DefaultRequestContext.getCurrentInstance().execute("dlgPersonaInsertar.hide()");               
                    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Gracias por Inscribirte", "En los próximos días nos comunicaremos a través de tu e-mail");
                    RequestContext.getCurrentInstance().showMessageInDialog(message);
                    //Util.addSuccessMessage("En los proximos días nos comunicaremos a traves de tu email");
                    objUsuario = new CUsuario();
                    objPersonaPeriodo = new CPersonaPeriodo();
                } else {
                    Util.mostrarMensaje("Verifique que los datos sean correctos");
                }
            } else {
                Util.addErrorMessage("Cédula Registrada");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        //FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Insertar Asistencia">
    public void insertarAsistencia() throws IOException {
        try {
            String aux = objUsuario.getCedula();
            {
                if (MAsistencia.verificaAceptado(aux)) {
                    if (MAsistencia.insertarAsistencia(1, aux)) {
                        lstCUsuario = MAsistencia.obtenerUsuario(aux);
                        for (CUsuario lis : lstCUsuario) {
                            this.nombre = "" + lis.getNombre();
                            selObjtUsuario.setId(lis.getId());
                            selObjtUsuario.setNombre(lis.getNombre());
                            selObjtUsuario.setApellido(lis.getApellido());
                            selObjtUsuario.setCedula(lis.getCedula());
                        }
                        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Gracias por Asistir", ""+selObjtUsuario.getNombre()+selObjtUsuario.getApellido()+selObjtUsuario.getId());
                        //RequestContext.getCurrentInstance().showMessageInDialog(message);
                        RequestContext.getCurrentInstance().execute("PF('wgAsistencia').show()");
                        objUsuario = null;
                        objUsuario = new CUsuario();
                    } else {
                        Util.mostrarMensaje("Verifique que el dato sea correcto");
                    }
                } else {
                    Util.addErrorMessage("Usted no a sido seleccionado");
                }
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Método Actualizar Usuario">
    public void updateUsuario() throws IOException {
        try {
            if (MUsuario.updateUsuario(objUsuario)) {
                Util.addSuccessMessage("Succesful", "Datos Modificados");
                objUsuario = new CUsuario();
            } else {
                Util.mostrarMensaje("Verifique que los datos sean correctos");
            }
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
    }
    //</editor-fold>
}
