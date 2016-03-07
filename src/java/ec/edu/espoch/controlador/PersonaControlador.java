/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espoch.controlador;

import BeanSession.MbSLogin;
import ec.edu.espoch.entidades.CComentario;
import ec.edu.espoch.entidades.CCuenta;
import ec.edu.espoch.entidades.CPersona;
import ec.edu.espoch.entidades.CSexo;
import ec.edu.espoch.modelo.MPersona;
import ec.edu.espoch.modelo.Msexo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.context.DefaultRequestContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;
import recursos.Util;

/**
 *
 * @author William
 */
@ManagedBean
@ViewScoped
public class PersonaControlador {

    /**
     * Creates a new instance of FacultadControlador
     */
    private CPersona objPersona;
    private CComentario objComentario;
    private CCuenta objCuenta;
    private CPersona selObjPersona;
    private ArrayList<CPersona> lstCPersona;
    private int idSexo;
    private ArrayList<CSexo> lstSexo;
    private String usuario;
    
    private int prueba=14;

    public PersonaControlador() {
        this.objPersona = new CPersona();
        this.objComentario = new CComentario();
        this.objCuenta = new CCuenta();
        this.selObjPersona = new CPersona();
        this.lstCPersona = new ArrayList<CPersona>();
        this.lstSexo = new ArrayList<CSexo>();
    }

    public int getPrueba() {
        return prueba;
    }

    public void setPrueba(int prueba) {
        this.prueba = prueba;
    }

    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public CPersona getObjPersona() {
        return objPersona;
    }

    public void setObjPersona(CPersona objPersona) {
        this.objPersona = objPersona;
    }

    public CComentario getObjComentario() {
        return objComentario;
    }

    public void setObjComentario(CComentario objComentario) {
        this.objComentario = objComentario;
    }

    public CCuenta getObjCuenta() {
        return objCuenta;
    }

    public void setObjCuenta(CCuenta objCuenta) {
        this.objCuenta = objCuenta;
    }

    public CPersona getSelObjPersona() {
        return selObjPersona;
    }

    public void setSelObjPersona(CPersona selObjPersona) {
        this.selObjPersona = selObjPersona;
    }

    public ArrayList<CPersona> getLstCPersona() {
        return lstCPersona;
    }

    public void setLstCPersona(ArrayList<CPersona> lstCPersona) {
        this.lstCPersona = lstCPersona;
    }

    public int getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(int idSexo) {
        this.idSexo = idSexo;
    }

    public ArrayList<CSexo> getLstSexo() {
        return lstSexo;
    }

    public void setLstSexo(ArrayList<CSexo> lstSexo) {
        this.lstSexo = lstSexo;
    }

    /*
     postonstructor se ejecuta luego del constructor
     */
    @PostConstruct
    public void reinit() {
        //cargarFacultad();
        cargarSexo();
    }

    public void cargarSexo() {
        try {
            lstSexo = Msexo.obtenerSexo();
        } catch (Exception e) {
            System.err.println("e" + e.getMessage());
        }

    }
    /*
     Metodo que permite cargar todos los registros de la base de datos
     

     public void cargarFacultad() {
     try {
     this.lstCFacultad = Mfacultad.obtenerFacultad();

     } catch (Exception e) {
     System.err.println("e" + e.getMessage());
     }
     }*/
    /*
     Metodo para insertar en la tabla persona
     */

    public void cerrarForm() {
        RequestContext.getCurrentInstance().closeDialog("dlgEditarPersona");
        //DefaultRequestContext.getCurrentInstance().execute("dlgPersonaInsertar.hide()");
    }

    public void frmEditar() {
        RequestContext.getCurrentInstance().openDialog("dlgEditarPersona");
    }

    public void insertarPersona() throws IOException {
        try {
            objPersona.setObjSexo(Msexo.obtSexo(idSexo));
            String nombre = objPersona.getNombre();
            String apellido = objPersona.getApellido();
            usuario = String.valueOf(nombre.charAt(0) + "" + nombre.charAt(1) + "" + apellido.charAt(0) + "" + apellido.charAt(1));
            if (MPersona.insertarPersona(objPersona) && MPersona.insertarComentario(objComentario, objPersona) && MPersona.insertarCuenta(usuario, objPersona)) {
                //DefaultRequestContext.getCurrentInstance().execute("dlgPersonaInsertar.hide()");
                //Mensajes de confirmacion de la cuenta

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Su cuenta se ha creado satisfactoriamente", "Su cuenta se a creado con el usuario =>   " + usuario + "y con la comstraseña tedx123");
                RequestContext.getCurrentInstance().showMessageInDialog(message);

                //Util.addSuccessMessage("Datos Insertados");
                //cargarFacultad();
            } else {
                Util.mostrarMensaje("Datos no insertados Insertados");
            }

            objPersona = null;
        } catch (Exception e) {
            Util.addErrorMessage(e.getMessage());
        }
        //FacesContext.getCurrentInstance().getExternalContext().redirect("./index.xhtml");
    }

    public void redireccion() {
        String aux = "./paginas/login.xhtml";
    }

    
    
    
    /*
     public void eliminarPersona() {
     try {
     if (MPersona.eliminarPersona(selObjPersona)) {
     DefaultRequestContext.getCurrentInstance().execute("dlgEliminarPersona.hide()");
     Util.addSuccessMessage("Datos eliminados");
     cargarPersona();

     } else {
     Util.mostrarMensaje("Datos no eliminados");
     }

     objPersona = null;
     } catch (Exception e) {
     Util.addErrorMessage(e.getMessage());
     }

     }
    
    
    
    public void actualizarAvatar1() throws IOException {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            if (this.avatar.getSize() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "Ud. debe seleccionar un archivo de imagen \".png\""));
                return;
            }

            if (!this.avatar.getFileName().endsWith(".png")) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo debe ser con extensión \".png\""));
                return;
            }

            if (this.avatar.getSize() > 2097152) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", "El archivo no puede ser más de 2mb"));
                return;
            }

            ServletContext servletContext=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
            String carpetaAvatar=(String)servletContext.getRealPath("/avatar");
            
            outputStream=new FileOutputStream(new File(carpetaAvatar+"/"+prueba+".png"));
            inputStream=this.avatar.getInputstream();

//String carpetaAvatar = "../../Documentos";
            //outputStream = new FileOutputStream(new File(carpetaAvatar + "/" + selEvidencia.getIntTevidenciaCodigo() + ".pdf"));
            //outputStream = new FileOutputStream(new File("C://documentos" + objPersona.getCedula() + ".png"));
            //inputStream = this.avatar.getInputstream();
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto:", "Foto ingresado correctamente"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error fatal:", "Por favor contacte con su administrador " + ex.getMessage()));
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (outputStream != null) {
                outputStream.close();
            }
        }
    }*/

  public void editarPersona() {
     try {
     if (MPersona.actualizarPersona(objPersona)&& MPersona.actualizarPassword(objCuenta)) {
     DefaultRequestContext.getCurrentInstance().execute("dlgEditarPersona.hide()");
     //Util.addSuccessMessage("Datos Modificados");
     //cargarFacultad();

     } else {
     Util.mostrarMensaje("Datos no modificados");
     }

     objPersona = null;
     } catch (Exception e) {
     Util.addErrorMessage(e.getMessage());
     }

     }

}
