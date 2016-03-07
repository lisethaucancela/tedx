/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.controlador;

import ec.edu.espoch.entidades.CPersonaPeriodo;
import ec.edu.espoch.entidades.CTransaccion;
import ec.edu.espoch.entidades.CUsuario;
import ec.edu.espoch.modelo.MPeriodo;
import ec.edu.espoch.modelo.MPersonaPeriodo;
import ec.edu.espoch.modelo.MTransaccion;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;
import recursos.Util;

/**
 *
 * @author William
 */
@ManagedBean
@ViewScoped
public class PersonaPeriodoControlador {

    private CUsuario objUsuario;
    private CPersonaPeriodo objPersonaPeriodo;
    private ArrayList<CPersonaPeriodo> lstRegistrados;
    private ArrayList<CPersonaPeriodo> lstParticipantes;
    private int numeroParticipantes;

    public PersonaPeriodoControlador() {
        this.objUsuario = new CUsuario();
        this.objPersonaPeriodo = new CPersonaPeriodo();
        this.lstRegistrados = new ArrayList<CPersonaPeriodo>();
        this.lstParticipantes = new ArrayList<CPersonaPeriodo>();
    }

    //<editor-fold defaultstate="collapsed" desc="Métodos Get y Set">
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

    public ArrayList<CPersonaPeriodo> getLstRegistrados() {
        return lstRegistrados;
    }

    public void setLstRegistrados(ArrayList<CPersonaPeriodo> lstRegistrados) {
        this.lstRegistrados = lstRegistrados;
    }

    public ArrayList<CPersonaPeriodo> getLstParticipantes() {
        return lstParticipantes;
    }

    public void setLstParticipantes(ArrayList<CPersonaPeriodo> lstParticipantes) {
        this.lstParticipantes = lstParticipantes;
    }

    public int getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public void setNumeroParticipantes(int numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }
    //</editor-fold>

    @PostConstruct
    public void reinit() {
        cargarRegistrados();
        cargarParticipantes();
        cargarNumeroParticipantes();
    }

    public void cargarRegistrados() {
        try {
            lstRegistrados = new ArrayList<CPersonaPeriodo>();
            lstRegistrados = MPersonaPeriodo.obtenerRegistrados(1);
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
    }

    public void cargarParticipantes() {
        try {
            lstParticipantes = new ArrayList<CPersonaPeriodo>();
            lstParticipantes = MPersonaPeriodo.obtenerParticipantes(1);
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
    }

    public void cargarNumeroParticipantes() {
        try {
            numeroParticipantes = MPersonaPeriodo.numeroParticipantes(1);
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }
    }

    public void listaParticipantes() {
        lstParticipantes = new ArrayList<CPersonaPeriodo>();
        int maximo = 0;
        boolean band = false;
        try {
            maximo = MPeriodo.obtMaxParticipantes(1);
            int posible = maximo - numeroParticipantes;
            for (CPersonaPeriodo lst : lstRegistrados) {
                if (lst.isBlnElegido()) {
                    lstParticipantes.add(lst);
                    band = true;
                }
            }
            if (band) {
                if (numeroParticipantes < maximo) {
                    if (lstParticipantes.size() + numeroParticipantes <= maximo) {
                        RequestContext.getCurrentInstance().execute("PF('dialogo').show()");
                    } else {
                        Util.addWarnMessage("Supera el límite de participantes", "Puede elegir máximo " + posible);
                    }
                } else {
                    Util.addErrorMessage("Cupos Completados");
                }
            } else {
                Util.addWarnMessage("Advertencia", "Ningun registro seleccionado");
            }

        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
            Util.addErrorMessage("e " + e.getMessage());
        }
    }

    public void confirmarParticipantes() {
        try {
            MailClient mail = new MailClient();
            CTransaccion tran;
            String body;
            String mailAddresses = "";
            boolean band = false;
            for (CPersonaPeriodo item : lstParticipantes) {
                body = "  <span style=\"font-size: 14.0pt;\">\n"
                        + "<center><b><h2> ¡Enhorabuena¡</h2><h3><span style=\"color:red;\">TEDx</span>ESPOCHRiobamba espera por ti </h3></b></center> <br/>\n"
                        + "Hola<b> " + item.getObjPersona().getNombre()+" "+item.getObjPersona().getApellido()+ "</b>¡Tenemos buenas noticias! Eres una de las 200 personas que compartirá con nosotros este magnífico evento donde podremos valorar la fuerza que tienen las ideas.\n"
                        + "A continuación te detallamos algunas indicaciones relevantes: <br/><br/> \n"
                        + "<ul><li>Recuerda llevar tu documento de identificación el día del evento a partir de las 15:00 para registrarte y puedas ingresar.</li>\n"
                        + " <li>TEDxESPOCHRiobamba iniciará puntualmente, no se permitirá el ingreso una vez iniciado el evento.</li>\n"
                        + " <li>El evento durará aproximadamente 5 horas.</li>\n"
                        + "</ul> <b>Fecha y hora:</b> Lunes 19 de Octubre de 2015, 16:00 <br/>\n"
                        + "<b>Lugar: </b> Auditorio Romeo Rodriguez ESPOCH <br/> <br/>\n"
                        + "Te esperamos. <br/> <br/><b>Atentamente,</b><br/>EquipoTEDxESPOCHRiobamba </span>";
                mailAddresses = item.getObjPersona().getEmail();
                if (mail.sendMail(mailAddresses, "Evento Tedx", body)) {
                    if (MPersonaPeriodo.modificarEstadoPersonaPeriodo(1, item.getObjPersona().getId(), 2)) {
                        tran = new CTransaccion(item.getObjPersona().getCedula(), "Update", "tpersonaperiodo: estado=2");
                        MTransaccion.insertarTransccion(tran);
                    }
                    Util.addSuccessMessage("Bien", "E-mail Enviado a "+ mailAddresses);
                } else {
                    Util.addErrorMessage("Al enviar los E-mail");
                }
            }

        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
            Util.addErrorMessage("e" + e.getMessage());
        }
        cargarRegistrados();
        cargarNumeroParticipantes();
        lstParticipantes = new ArrayList<CPersonaPeriodo>();
    }
}
